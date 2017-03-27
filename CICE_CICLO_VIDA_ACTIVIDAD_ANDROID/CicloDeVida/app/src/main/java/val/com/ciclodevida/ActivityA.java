
package val.com.ciclodevida;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


/**
 * Ejempo que demuestra cómo funciona el ciclo de vida de las actividades
 * Y entender además el concepto de callback, herencia y polimorfismo
 * */
public class ActivityA extends Activity {

    private String mActivityName;
    private TextView mStatusView;
    private TextView mStatusAllView;
    private PilaMensajes mPilaMensajes = PilaMensajes.getInstance();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);
        mActivityName = getString(R.string.activity_a);
        mStatusView = (TextView)findViewById(R.id.status_view_a);
        mStatusAllView = (TextView)findViewById(R.id.status_view_all_a);
        mPilaMensajes.setStatus(mActivityName, getString(R.string.on_create));
        Utils.Mostrar(mStatusView, mStatusAllView);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPilaMensajes.setStatus(mActivityName, getString(R.string.on_start));
        Utils.Mostrar(mStatusView, mStatusAllView);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mPilaMensajes.setStatus(mActivityName, getString(R.string.on_restart));
        Utils.Mostrar(mStatusView, mStatusAllView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPilaMensajes.setStatus(mActivityName, getString(R.string.on_resume));
        Utils.Mostrar(mStatusView, mStatusAllView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPilaMensajes.setStatus(mActivityName, getString(R.string.on_pause));
        Utils.Mostrar(mStatusView, mStatusAllView);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPilaMensajes.setStatus(mActivityName, getString(R.string.on_stop));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPilaMensajes.setStatus(mActivityName, getString(R.string.on_destroy));
        mPilaMensajes.clear();
    }

    public void startDialog(View v) {
        Intent intent = new Intent(ActivityA.this, DialogActivity.class);
        startActivity(intent);
    }

    public void startActivityB(View v) {
        Intent intent = new Intent(ActivityA.this, ActivityB.class);
        startActivity(intent);
    }

    public void startActivityC(View v) {
        Intent intent = new Intent(ActivityA.this, ActivityC.class);
        startActivity(intent);
    }

    public void finishActivityA(View v) {
        ActivityA.this.finish();
    }

}
