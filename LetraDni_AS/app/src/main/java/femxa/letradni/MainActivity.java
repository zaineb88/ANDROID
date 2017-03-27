package femxa.letradni;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void calcularDni(View v)
    {
        EditText caja_dni=(EditText) findViewById(R.id.cajadni);
            String dni = caja_dni.getText().toString();

        ObtenerLetraDni obtenerLetraDni = new ObtenerLetraDni(this);
        obtenerLetraDni.execute(dni);
        Log.d("MENSAJE","ObtenerLetraDni llamado");

    }

    public void  mostrarToat (String letra)
    {
        Toast toast=Toast.makeText(this, "Su letra es : " + letra, Toast.LENGTH_LONG);
        toast.show();
    }
}
