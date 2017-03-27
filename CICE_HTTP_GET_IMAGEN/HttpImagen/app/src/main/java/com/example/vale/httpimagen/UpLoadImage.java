package com.example.vale.httpimagen;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by vale on 13/07/16.
 */
public class UpLoadImage extends AsyncTask<Bitmap, Void, String> {
    @Override
    protected String doInBackground(Bitmap... params) {

        //String str_dev = REGISTRO_KO;
        URL serverUrl = null;
        HttpURLConnection httpCon = null;
        //ObjectOutputStream oos = null;

        FileOutputStream fos = null;

        try

        {
            //str_dev = REGISTRO_KO; //Inicialilizo pesimista

            serverUrl = new URL("http://192.168.1.204:8080/CICERemote/SubirImagen");
            ///RegistrarUsuario
            httpCon = (HttpURLConnection) serverUrl.openConnection();
            httpCon.setRequestMethod("POST");

            Bitmap bm = params[0];

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] imageBytes = baos.toByteArray();
            String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);

            OutputStream os = httpCon.getOutputStream();

            OutputStreamWriter osw = new OutputStreamWriter(os);

            osw.write(encodedImage);

            osw.close();


            int resp_code = httpCon.getResponseCode();

            httpCon.disconnect();

        }
        catch (Exception e)
        {
            Log.e("Error ",  e.getMessage());
        }

        return "OK";
    }
}
