package antonio.femxa.ahorcadoproject;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class CreditosActivity extends AppCompatActivity {
    ListView lv;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creditos);
        View v = findViewById(R.id.boton1);//castin
        Button boton = (Button) v;

        mediaPlayer = MediaPlayer.create(this, R.raw.soni);
        mediaPlayer.setLooping(false);
        mediaPlayer.setVolume(100, 100);

        boton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mediaPlayer.start();
                return true;
            }
        });
    }
}
