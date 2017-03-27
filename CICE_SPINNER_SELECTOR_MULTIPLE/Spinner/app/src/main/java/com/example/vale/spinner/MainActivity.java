package com.example.vale.spinner;

import android.app.Activity;
import android.content.res.TypedArray;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * 2º ejemplo: un spinner más completo con TypedArray y Recycler
 */
/*
public class MainActivity extends Activity implements AdapterView.OnItemSelectedListener {

    private Spinner spProvincias, spLocalidades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        this.spProvincias = (Spinner) findViewById(R.id.sp_provincia);
        this.spLocalidades = (Spinner) findViewById(R.id.sp_localidad);

        loadSpinnerProvincias();

    }


    private void loadSpinnerProvincias() {

        // Create an ArrayAdapter using the string array and a default spinner
        // layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.provincias, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        this.spProvincias.setAdapter(adapter);

        // This activity implements the AdapterView.OnItemSelectedListener
        this.spProvincias.setOnItemSelectedListener(this);
        this.spLocalidades.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

        switch (parent.getId()) {
            case R.id.sp_provincia:

                // Retrieves an array
                TypedArray array_provincias = getResources().obtainTypedArray(R.array.array_provincia_a_localidades);
                CharSequence[] array_localidades = array_provincias.getTextArray(pos);
                array_provincias.recycle();

                // Create an ArrayAdapter using the string array and a default
                // spinner layout
                ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(
                        this, android.R.layout.simple_spinner_item, array_localidades);

                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_gallery_item);

                // Apply the adapter to the spinner
                this.spLocalidades.setAdapter(adapter);

                break;

            case R.id.sp_localidad:

                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Callback method to be invoked when the selection disappears from this
        // view. The selection can disappear for instance when touch is
        // activated or when the adapter becomes empty.
    }


    public void showLocalidadSelected(View v) {
        Toast.makeText(
                getApplicationContext(),
                getString(R.string.message, spLocalidades.getSelectedItem()
                        .toString(), spProvincias.getSelectedItem().toString()),
                Toast.LENGTH_LONG).show();
    }


}

*/

/**
 * 1 er ejemplo: un spinner sencillo
 */
public class MainActivity extends AppCompatActivity {

    private TextView lblMensaje;
    private Spinner adaptador;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lblMensaje = (TextView) findViewById(R.id.LblMensaje);
        adaptador = (Spinner) findViewById(R.id.CmbOpciones);

        final String[] datos =
                new String[]{"Elem1", "Elem2", "Elem3", "Elem4", "Elem5"};

        //Alternativa 1: Array java
        ArrayAdapter<String> adaptador =
                new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, datos);

        //Alternativa 2: Recurso XML de tipo string-array
        //ArrayAdapter<CharSequence> adaptador =
        //	    ArrayAdapter.createFromResource(this,
        //	        R.array.valores_array, android.R.layout.simple_spinner_item);

        adaptador.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);


        spinner.setAdapter(adaptador);

        spinner.setOnItemSelectedListener(  new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent,
                                               View v, int position, long id) {
                        lblMensaje.setText("Seleccionado: " + parent.getItemAtPosition(position));
                    }

                    public void onNothingSelected(AdapterView<?> parent) {
                        lblMensaje.setText("");
                    }
                });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
