package com.br.ifpr.coffeenator.activitys;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.br.ifpr.coffeenator.R;
import com.br.ifpr.coffeenator.myclasses.MyMediaPlayer;

public class CreditosActivity extends AppCompatActivity {
    private TextView preto;
    private boolean terminou = true;
    private int opacidade = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //Fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_creditos);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        preto = (TextView) findViewById(R.id.preto);
        preto.setBackgroundColor(Color.parseColor("#00000000"));
        opacidade = 1;
        if(bundle != null){
            terminou = false;
            preto.setBackgroundColor(Color.parseColor("#ff000000"));
            opacidade = Integer.parseInt("ff", 16);
            timerHandler.postDelayed(timerRunnable, 10);
            MyMediaPlayer.changeMusicVolume(bundle.getFloat("volume musica"));
            MyMediaPlayer.setMusicaDeFundoPlayer(R.raw.rodolfo, this);
        }
    }

    public void onBtnVoltarCreditosClick(View v){
        MyMediaPlayer.setMusicaDeFundoPlayer(R.raw.menu, this);
        MyMediaPlayer.playButtonSound(this);
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        // não chame o super desse método
    }

    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {

        @Override
        public void run() {
            if(!terminou) {
                if (opacidade < 20) {
                    terminou = true;
                }
                opacidade--;
                String newColor = "#" + Integer.toHexString(opacidade) + "000000";
                preto.setBackgroundColor(Color.parseColor(newColor));
                timerHandler.postDelayed(this, 100);
            }
        }
    };

    @Override
    public void onPause(){
        timerHandler.removeCallbacks(timerRunnable);
        super.onPause();
    }
}
