package com.example.joseandres.e_mail;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText txv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Obtenemos la referencia del EditText.
        txv=(EditText)findViewById(R.id.edt);
        //Obtenemos una referencia de un objeto de la clase SharedPreferences
        // a través del método getSharedPreferences. El primer parámetro es el
        // nombre del archivo de preferencias y el segundo la forma de creación
        // del archivo MODE_PRIVATE queindica que solo esta aplicación puede
        // consultar el archivo XML que se crea.
        SharedPreferences prefe=getSharedPreferences("datos", Context.MODE_PRIVATE);
        //Para extraer los datos del archivo de preferencias debemos indicar el nombre a
        // extraer y un valor de retorno si dicho nombre no existe en el archivo de preferencias'
        txv.setText(prefe.getString("mail",""));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    //Al presionar el botón "ingresar" lo que hacemos es grabar en el archivo
    // de preferencias el contenido del EditText en una variable llamada "mail".
    public void ingresar(View v) {
        SharedPreferences preferencias=getSharedPreferences("datos",Context.MODE_PRIVATE);
        //Se crea un objeto de la clase Editor y obtener la referencia del objeto de la
        // clase SharedPreferences que acabamos de crear.
        SharedPreferences.Editor editor=preferencias.edit();
        //Mediante el método putString almacenamos en mail el valor del String
        // cargado en el EditText.
        editor.putString("mail", txv.getText().toString());
        //Luego debemos llamar al método commit de la clase Editor para que el dato quede
        // almacenado en forma permanente en el archivo de preferencias. Esto hace que
        // cuando volvamos a arrancar la aplicación se recupere el último mail ingresado.
        editor.commit();
        //El método finish de la clase Activity finaliza la actividad actual.
        finish();
    }
}