package femxa.intentando;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
int con=0;
    String stringcon= String.valueOf(con);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // para ir a otra activity
       /* Intent intent =new Intent(this, Main2Activity.class);
        startActivity(intent);*/

       /* Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "hola zizi");
        sendIntent.setType("text/plain");
        sendIntent.setPackage("com.whatsapp");
        startActivity(sendIntent);*/

        View v = findViewById(R.id.button);
        Button boton = (Button) v;

        getLayoutInflater();

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                con++;
                if (R.id.button == v.getId()) {

                    EditText edit = (EditText) findViewById(R.id.EditText);
                    String nombre = edit.getText().toString();
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT, nombre);
                    sendIntent.setType("text/plain");
                    sendIntent.setPackage("com.whatsapp");
                    startActivity(sendIntent);
                    Log.d("MENSAJE", "mensaje enviado");

                    SharedPreferences sp = getSharedPreferences("prueba", Context.MODE_PRIVATE);
                    String mensaje = sp.getString(stringcon,nombre);
                    SharedPreferences.Editor ed = sp.edit();
                    ed.putString("MENSAJE "+con +" ES ", mensaje);
                    ed.commit();

                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menumain,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d("MENSAJE","item id "+item.getItemId()) ;
        if(item.getItemId()==R.id.boton_salir)
        { // salir
            this.finish();
            Log.d("MENSAJE","El usario le ha dado salir");
        }else {
            Log.d("MENSAJE","El usario quiere ir a la lista de mensajes");
            Intent intent =new Intent(this, Main2Activity.class);

            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }
}