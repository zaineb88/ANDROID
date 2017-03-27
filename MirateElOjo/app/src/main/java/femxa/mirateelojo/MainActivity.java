package femxa.mirateelojo;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebView webView =(WebView) findViewById(R.id.cajaweb);
        webView.setWebViewClient(new WebViewClient());
       webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.loadUrl("http://femxa-ebtm.rhcloud.com/html/patologias.html");


////////// codigo para añadir musica


            MediaPlayer mediaPlayer;
            mediaPlayer = MediaPlayer.create(this,R.raw.sonido);
            mediaPlayer.setLooping(true);
            mediaPlayer.setVolume(100,100);
            mediaPlayer.start();

        }
    }

