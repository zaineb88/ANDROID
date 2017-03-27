package femxa.dencidad;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       String d= obtenerDensidad(this);
        Log.d("Mensaje","densidad es "+d);
    }

    public String obtenerDensidad (Context context)
    { String dens = "";
        switch (context.getResources().getDisplayMetrics().densityDpi) {
            case DisplayMetrics.DENSITY_LOW: dens = "ldpi"; break;
            case DisplayMetrics.DENSITY_MEDIUM: dens = "mdpi"; break;
            case DisplayMetrics.DENSITY_HIGH: dens = "hdpi"; break;
            case DisplayMetrics.DENSITY_XHIGH: dens = "xhdpi"; break;
            case DisplayMetrics.DENSITY_XXHIGH: dens = "xxhdpi"; break;
            case DisplayMetrics.DENSITY_XXXHIGH: dens = "xxxhdpi"; }

        return dens; }
}


