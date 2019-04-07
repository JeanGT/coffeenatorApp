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

public class Mapa01Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa_01);
        if(PS.getSanidade() < 2){
            ImageView bg = (ImageView) findViewById(R.id.imgBg1);
            bg.setImageResource(R.drawable.mapa_insano_1);
        }
    }

    public void onBtnStatusMapa01Click(View view){
        MyMediaPlayer.playButtonSound(this);
        Intent intent = new Intent(this, StatusActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("Old Activity ID", DicionarioDeActivitys.getIdByClass(this.getClass()));
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void onBtnOpcoesMapa01Click(View v){
        MyMediaPlayer.playButtonSound(this);
        Intent intent = new Intent(this, OpcoesActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("Old Activity ID", DicionarioDeActivitys.getIdByClass(this.getClass()));
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void onBtnSetaDireitaMapa01Click(View view){
        MyMediaPlayer.playButtonSound(this);
        Intent intent = new Intent(this, Mapa02Activity.class);
        startActivity(intent);
    }

    public void onBtnIgrejaMapaClick(View view){
        MyMediaPlayer.playButtonSound(this);
        if(PS.getSanidade() < 2){
            MyAlertDialogConstructor.showMessage("Sanidade muito baixa...", "Não é permitida a entrada de gente maluca.", this);
        } else {
            if (PS.isBebeuAguaBenta()) {
                MyAlertDialogConstructor.showMessage("Expulso", "Voce foi banido da igreja.", this);
            } else {
                Intent intent = new Intent(this, IgrejaActivity.class);
                startActivity(intent);
            }
        }
    }

    public void onBtnBibliotecaMapaClick(View view){
        MyMediaPlayer.playButtonSound(this);
        if(PS.getSanidade() < 2){
            MyAlertDialogConstructor.showMessage("Sanidade muito baixa...", "Não é permitida a entrada de gente maluca.", this);
        } else {
            Intent intent = new Intent(this, BibliotecaActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        // não chame o super desse método
    }

}
