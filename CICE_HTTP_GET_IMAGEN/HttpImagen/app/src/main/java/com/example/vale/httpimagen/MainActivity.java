package com.example.vale.httpimagen;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;




public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    private Bitmap bitmap;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            // para utilisar musica

        mediaPlayer = MediaPlayer.create(this, R.raw.sonido);
        mediaPlayer.setLooping(true);
        mediaPlayer.setVolume(100, 100);
        mediaPlayer.start();


        if (null != savedInstanceState) {
            bitmap = savedInstanceState.getParcelable("img");
        } else {

            try {
                bitmap = new DescargaImagenes().execute("http://1.bp.blogspot.com/-hxOxt3L3Djs/U9u-EhPYaNI/AAAAAAAAuNQ/0V3vzmgdoqQ/s1600/fotos-para-perfil.jpg").get();
            } catch (Throwable t) {
                Log.d(getClass().getCanonicalName(), "Error descargando la imagen", t);
            }
        }

        ImageView img = (ImageView) findViewById(R.id.imageView);
        img.setImageBitmap(bitmap);


//        Bitmap bitmap1 = null;
//
//        bitmap1 = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
//
//        new UpLoadImage().execute(bitmap1);


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelable("img", bitmap);
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

        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());



        //  button para controral la musica
        View v2 = findViewById(R.id.button);
        final Button boton = (Button) v2;
        boton.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v2) {
                if (boton.getText() == "no musica") {
                    mediaPlayer.pause();
                    boton.setText("musica");
                } else {
                    boton.setText("no musica");
                    mediaPlayer.start();

                }
            }
        });



        // imageButton para controral musica

        View v = findViewById(R.id.BtnImagen);
        final ImageButton ib = (ImageButton) v;

        ib.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying())
                {
                    mediaPlayer.pause();
                    ib.setImageResource(R.drawable.ic_volume_up);
                }else {

                    ib.setImageResource(R.drawable.ic_volume_off);
                    mediaPlayer.start();


                }
            }
        });
    }


    @Override
    public void onStop () {
        super.onStop();

        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

}