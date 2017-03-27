package antonio.femxa.ahorcadoproject;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class VictoriaActivity extends AppCompatActivity {

    private String palabra;
    private static boolean musicaOnOff;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_victoria);

        ImageView imageView = (ImageView) findViewById(R.id.imagenvictoria);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1){
            imageView.setBackgroundResource(R.drawable.progress_animation_gameover2);
            AnimationDrawable progressAnimation = (AnimationDrawable) imageView.getBackground();
            progressAnimation.start();
        }else {
            imageView.setBackgroundResource(R.drawable.pantallavictoria);
        }

        palabra = getIntent().getStringExtra("palabra_clave");

        Button button = (Button) findViewById(R.id.boton_victoria_inicio);

        TextView textView = (TextView) findViewById(R.id.text_palabra_oculta_victoria);

        textView.setText(palabra);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(VictoriaActivity.this, CategoriaActivity.class);

                intent.putExtra("SonidoOn-Off",musicaOnOff);

                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        musicaOnOff = getIntent().getBooleanExtra("SonidoOn-Off",true);

        mediaPlayer = MediaPlayer.create(this, R.raw.sonido_ganador);
        mediaPlayer.setLooping(false);
        mediaPlayer.setVolume(100, 100);

        if(musicaOnOff)
        {
            mediaPlayer.start();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.stop();
    }
}

