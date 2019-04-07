package com.br.ifpr.coffeenator.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.br.ifpr.coffeenator.R;
import com.br.ifpr.coffeenator.myclasses.MyMediaPlayer;

public class InstrucoesActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_instrucoes);
    }

    public void onBtnVoltarInstrucoesClick(View v){
        MyMediaPlayer.playButtonSound(this);
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        // não chame o super desse método
    }
}
