package val.com.botones;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView lblMensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*1ER EJEMPLO */

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lblMensaje = (TextView)findViewById(R.id.LblMensaje);

        /*2º EJEMPLO */
        /*super.onCreate(savedInstanceState);
        setContentView(R.layout.imgview);

        ImageView img= (ImageView)findViewById(R.id.ImgFoto);
        img.setImageResource(R.drawable.ic_launcher);*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return true;
    }

    public void botonTocado (View v)
    {
        lblMensaje.setText(v.getClass().getCanonicalName());

    }
}
