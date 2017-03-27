package femxa.miprimeraapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

public class ActividadPrincipal extends AppCompatActivity {

    private String leDoyLaVuelta(String nombre)
    {
        String nombreAlreves="";
        for(int i=nombre.length()-1;i>=0;i--)
        {
            nombreAlreves=nombreAlreves+nombre.charAt(i);
        }

        return nombreAlreves;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_principal);
        Gson gson =new Gson();

        Log.d("MIMENSAJE","ID BOTON = "+R.id.button);
        View v=findViewById(R.id.button);
        Button boton =(Button) v;

        getLayoutInflater();

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(R.id.button==v.getId()) {
                    Log.d("MIMENSAJE", "Ha tocado el boton  ....esta bien");
                }else{
                    Log.d("MIMENSAJE", "Ha tocado el boton...no esta bien");}

                //coger el contenido del edittext le doy la vuelta y lo pongo en el textview
             EditText edit= (EditText) findViewById(R.id.editText);
              String nombre=  edit.getText().toString();
                Log.d("MIMENSAJE","El nombre introducido es  = "+nombre);
             String nombreAlreves=   leDoyLaVuelta (nombre);

                Log.d("MIMENSAJE","El nombre al reves es  = "+nombreAlreves);
                TextView textView=(TextView) findViewById(R.id.textView);
                textView.setText(nombreAlreves);
                Log.d("MIMENSAJE","Text view actualizado");
            }
        });
    }
}
