package com.val.inflando;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Button boton = (Button)findViewById(R.id.button);

        EscuchaBoton escuchaBoton = new EscuchaBoton(this); // this seghnifica esta clase que es MainActivity
        // es decir guarda la ventana de MainActivity en el metodo de escuchaBoton

        boton.setOnClickListener(escuchaBoton);



    }
}
