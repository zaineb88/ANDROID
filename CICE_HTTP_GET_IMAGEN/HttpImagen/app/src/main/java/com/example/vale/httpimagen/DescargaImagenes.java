package com.example.vale.httpimagen;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by vale on 7/07/16.
 */
public class DescargaImagenes extends AsyncTask<String, Void, Bitmap> {

    @Override
    protected Bitmap doInBackground(String... params) {
        Bitmap bitmap = null;
        InputStream in = null;
        int respuesta = -1;
        URL url = null;
        HttpURLConnection httpConn = null;

            try
            {
                url = new URL(params[0]);
                httpConn = (HttpURLConnection) url.openConnection();

                respuesta = httpConn.getResponseCode();
                if (respuesta == HttpURLConnection.HTTP_OK)
                {
                    in = httpConn.getInputStream();
                    bitmap = BitmapFactory.decodeStream(in);
                    in.close();
                }

            } catch (Exception e) {
                // TODO Auto-generated catch block
                Log.d(getClass().getCanonicalName(), "ERROR descargando la imagen", e);
            }

        return bitmap;

    }
}
