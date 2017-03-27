package femxa.letradni;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrador on 22/03/2017.
 */


public class ObtenerLetraDni extends AsyncTask<String, Void, String>
{
    private static final String DIR_SERVLET_LETRA_DNI="http://femxa-ebtm.rhcloud.com/CalcularLetraDni?dni="; // con el direccion de servidor
   // private static final String DIR_SERVLET_LETRA_DNI="http://localhost:8091/DniAndroid/CalcularLetraDni?dni="; // no funciona

      private MainActivity mainActivity ;
    public ObtenerLetraDni(MainActivity mainActivity)
    {
        this.mainActivity=mainActivity;
    }

    @Override
    protected String doInBackground(String... params)  //es este metodo comunicamos con servidor
    {
        String letra_dni=null;
        String dni = params[0];
        HttpURLConnection http=null;

  try
  {
      String servicio=DIR_SERVLET_LETRA_DNI+dni;
      URL url =new URL(servicio);
     http=(HttpURLConnection)url.openConnection();

      if( http.getResponseCode()==HttpURLConnection.HTTP_OK)
      {
          InputStream is =http.getInputStream();
          InputStreamReader isr = new InputStreamReader(is);
          BufferedReader br=new BufferedReader(isr);
          letra_dni=br.readLine();

      }
  }catch(Throwable t){}
        finally {
      http.disconnect();
  }

        return letra_dni;
    }

    @Override
    protected void onPostExecute(String letra) {
       // super.onPostExecute(s);
        Log.d("MENSAJE","lA INVOCACION AL SERVLET ACABO");
        Log.d("MENSAJE","lA LETRA RECIVIDA = "+ letra);
       mainActivity.mostrarToat(letra);

    }
}
