package com.br.ifpr.coffeenator.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.br.ifpr.coffeenator.R;

public class DojoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_igreja);
    }



    @Override
    public void onBackPressed() {
        // não chame o super desse método
    }
}
