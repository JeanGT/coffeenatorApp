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
import com.br.ifpr.coffeenator.myclasses.MyAlertDialogConstructor;
import com.br.ifpr.coffeenator.myclasses.MyMediaPlayer;
import com.br.ifpr.coffeenator.myclasses.PS;

public class Mapa03Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa_03);
        if(PS.getSanidade() < 2){
            ImageView bg = (ImageView) findViewById(R.id.imgBg3);
            bg.setImageResource(R.drawable.mapa_insano_3);
        }
    }

    public void onBtnStatusMapa03Click(View view){
        MyMediaPlayer.playButtonSound(this);
        Intent intent = new Intent(this, StatusActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("Old Activity ID", DicionarioDeActivitys.getIdByClass(this.getClass()));
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void onBtnOpcoesMapa03Click(View v){
        MyMediaPlayer.playButtonSound(this);
        Intent intent = new Intent(this, OpcoesActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("Old Activity ID", DicionarioDeActivitys.getIdByClass(this.getClass()));
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void onBtnTrabalhoMapaClick(View view){
        MyMediaPlayer.playButtonSound(this);
        if(PS.getSanidade() < 2){
            MyAlertDialogConstructor.showMessage("Sanidade muito baixa...", "Não é permitida a entrada de gente maluca.", this);
        } else {
            Intent intent = new Intent(this, TrabalhoActivity.class);
            startActivity(intent);
        }
    }

    public void onBtnSetaEsquerdaMapa03Click(View view){
        MyMediaPlayer.playButtonSound(this);
        Intent intent = new Intent(this, Mapa02Activity.class);
        startActivity(intent);
    }

    public void onBtnLojaMapaClick(View view){
        MyMediaPlayer.playButtonSound(this);
        Intent intent = new Intent(this, LojaActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        // não chame o super desse método
    }
}
