package com.br.ifpr.coffeenator.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.br.ifpr.coffeenator.R;
import com.br.ifpr.coffeenator.myclasses.DicionarioDeActivitys;
import com.br.ifpr.coffeenator.myclasses.MyMediaPlayer;
import com.br.ifpr.coffeenator.myclasses.PS;

public class Mapa02Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa_02);
        if(PS.getSanidade() < 2){
            ImageView bg = (ImageView) findViewById(R.id.imgBg2);
            bg.setImageResource(R.drawable.mapa_insano_2);
        }
    }

    public void onBtnStatusMapa02Click(View view){
        MyMediaPlayer.playButtonSound(this);
        Intent intent = new Intent(this, StatusActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("Old Activity ID", DicionarioDeActivitys.getIdByClass(this.getClass()));
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void onBtnOpcoesMapa02Click(View v){
        MyMediaPlayer.playButtonSound(this);
        Intent intent = new Intent(this, OpcoesActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("Old Activity ID", DicionarioDeActivitys.getIdByClass(this.getClass()));
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void onBtnCasaMapa02Click(View view){
        MyMediaPlayer.playButtonSound(this);
        Intent intent = new Intent(this, CasaActivity.class);
        startActivity(intent);
    }

    public void onBtnSetaDireitaMapa02Click(View view){
        MyMediaPlayer.playButtonSound(this);
        Intent intent = new Intent(this, Mapa03Activity.class);
        startActivity(intent);
    }

    public void onBtnSetaEsquerdaMapa02Click(View view){
        MyMediaPlayer.playButtonSound(this);
        Intent intent = new Intent(this, Mapa01Activity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        // não chame o super desse método
    }
}
