package antonio.femxa.appfinal;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class DerrotaActivity extends AppCompatActivity {

    private String palabra;
    private MediaPlayer mediaPlayer;
    private static Intent intent;
    private static boolean musicaOnOff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_derrota);
        ImageView imageView = (ImageView) findViewById(R.id.imagenDerrota);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1)
        {
            imageView.setBackgroundResource(R.drawable.progress_animation_gameover);
            AnimationDrawable progressAnimation = (AnimationDrawable) imageView.getBackground();
            progressAnimation.start();
        }
        else
        {
            imageView.setBackgroundResource(R.drawable.pantallagameover);
        }
        palabra = getIntent().getStringExtra("palabra_clave");

        Button button = (Button) findViewById(R.id.boton_derrota_inicio);

        TextView textView = (TextView) findViewById(R.id.text_palabra_oculta);

        textView.setText(palabra);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(DerrotaActivity.this, CategoriaActivity.class);

                intent.putExtra("SonidoOn-Off",musicaOnOff);

                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        musicaOnOff = getIntent().getBooleanExtra("SonidoOn-Off",true);

        mediaPlayer = MediaPlayer.create(this, R.raw.sonido_perdedor);
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

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(DerrotaActivity.this, CategoriaActivity.class);

        startActivity(intent);
        finish();
    }
}
