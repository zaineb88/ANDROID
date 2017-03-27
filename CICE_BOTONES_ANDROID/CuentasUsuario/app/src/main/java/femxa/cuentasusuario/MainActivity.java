package femxa.cuentasusuario;

import android.Manifest;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    // esto es el metodo que el usario toca el button de atras del movil
    @Override
    public void onBackPressed() {
       // super.onBackPressed();
        Log.d("MENSAJE","el usario le ha dado el botton de atras");
        //finish();
        AlertDialog alertDialog=new AlertDialog.Builder(this).create();
        alertDialog.setTitle("salir");
        alertDialog.setMessage("de verdad quiere salir??");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d("MENSAJE","el usario quiere salir");
                finish();
            }
        });

        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d("MENSAJE","el usario no quiere salir");
                dialog.cancel();
            }
        });
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "No se", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d("MENSAJE","el usario no sabe");
                dialog.cancel();
            }
        });

        alertDialog.show();
    }

    public void obtenerCuentas()
    {
       AccountManager accountManager= (AccountManager)getSystemService(ACCOUNT_SERVICE);
        Account[] array_cuentas= accountManager.getAccounts();
        for (Account cuenta: array_cuentas)
        {
            Log.d("MENSAJE","tipo"+cuenta.type);
            Log.d("MENSAJE","cuenta"+cuenta.name);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)

    {
        if(grantResults[0]==PackageManager.PERMISSION_GRANTED)
        {   Log.d("MENSAJE","EL USARIO ME DA PERMISO");
            obtenerCuentas();
        }
        else
        {
            Log.d("MENSAJE","EL USARIO NO ME DA PERMISO");
            finish();
        }

    }
       private void pedirpermisos()
         {
//             int permiso=ContextCompat.checkSelfPermission(this,Manifest.permission.GET_ACCOUNTS);
//                     if(permiso== PackageManager.PERMISSION_GRANTED)
//                     { Log.d("MENSAJE","PERMISO YA CONCIDEDO");}

             ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.GET_ACCOUNTS},150);
         }
    @Override
    protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    pedirpermisos();
    }

        //CREAR MENU


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menumain,menu);
//        menu.add(menu.NONE,1,1,"salir");
//        menu.add(menu.NONE,2,2,"Femxa web");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
     Log.d("MENSAJE","item id "+item.getItemId()) ;
        if(item.getItemId()==R.id.boton_salir)
        { // salir
          this.finish();
            Log.d("MENSAJE","El usario le ha dado salir");
        }else {
            Log.d("MENSAJE","El usario quiere ir a femxa web");
            Intent intent=new Intent( Intent.ACTION_VIEW);
            intent.setData(Uri.parse("http://femxa-ebtm.rhcloud.com"));
            startActivity(intent);
           // item.getItemId()==2
        }
        return super.onOptionsItemSelected(item);
    }
}
