package com.example.vale.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sp = getSharedPreferences("mispfs", Context.MODE_PRIVATE);
        int i = sp.getInt("Contador", 0);
        Log.d(getClass().getCanonicalName(), "Conta = " + i);
        i = i + 1;
        SharedPreferences.Editor ed = sp.edit();
        ed.putInt("Contador" , i);
        ed.commit();


    }
}
