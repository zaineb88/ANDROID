/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package val.com.ciclodevida;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class ActivityB extends Activity {

    private String mActivityName;
    private TextView mStatusView;
    private TextView mStatusAllView;
    private PilaMensajes mPilaMensajes = PilaMensajes.getInstance();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
        mActivityName = getString(R.string.activity_b_label);
        mStatusView = (TextView)findViewById(R.id.status_view_b);
        mStatusAllView = (TextView)findViewById(R.id.status_view_all_b);
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
    }

    public void startDialog(View v) {
        Intent intent = new Intent(ActivityB.this, DialogActivity.class);
        startActivity(intent);
    }

    public void startActivityA(View v) {
        Intent intent = new Intent(ActivityB.this, ActivityA.class);
        startActivity(intent);
    }

    public void startActivityC(View v) {
        Intent intent = new Intent(ActivityB.this, ActivityC.class);
        startActivity(intent);
    }

    public void finishActivityB(View v) {
        ActivityB.this.finish();
    }
}
