package com.br.ifpr.coffeenator.activitys;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import com.br.ifpr.coffeenator.R;
import com.br.ifpr.coffeenator.myclasses.DBConector;
import com.br.ifpr.coffeenator.myclasses.DicionarioDeActivitys;
import com.br.ifpr.coffeenator.myclasses.MyAlertDialogConstructor;
import com.br.ifpr.coffeenator.myclasses.MyMediaPlayer;
import com.br.ifpr.coffeenator.myclasses.PS;
import com.br.ifpr.coffeenator.myclasses.Usuario;

import java.io.IOException;

public class OpcoesActivity extends AppCompatActivity{

    int oldActivityId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_opcoes);

        SharedPreferences sp = getSharedPreferences("mySharedPreferences", this.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sp.edit();

        Intent oldIntent = getIntent();
        Bundle bundle = oldIntent.getExtras();
        oldActivityId = bundle.getInt("Old Activity ID");

        final SeekBar musicaBar =(SeekBar) findViewById(R.id.seekBarMusicaO);
        musicaBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
                // TODO Auto-generated method stub
                float volume = ((float)musicaBar.getProgress()) / 100;
                MyMediaPlayer.changeMusicVolume(volume);
                editor.putFloat("volumeMusica", volume);
                editor.apply();
            }
        });

        musicaBar.setProgress((int) (MyMediaPlayer.getVolumeMusica() * 100));

        final SeekBar SFXBar =(SeekBar) findViewById(R.id.seekBarSFXO);
        SFXBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
                // TODO Auto-generated method stub
                float volume = ((float)SFXBar.getProgress()) / 100;
                MyMediaPlayer.changeSFXVolume(volume);
                editor.putFloat("volumeSFX", volume);
                editor.apply();
            }
        });

        SFXBar.setProgress((int) (MyMediaPlayer.getVolumeSFX() * 100));

        CheckBox muted = (CheckBox) findViewById(R.id.muteBoxO);
        muted.setChecked(MyMediaPlayer.isMuted());
    }

    public void onBtnVoltarOClick(View v){
        Intent intent = new Intent(this, DicionarioDeActivitys.getClassById(oldActivityId));
        startActivity(intent);
    }

    public void onBtnDeslogarClick(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Deslogar");
        builder.setMessage("Você tem certeza que deseja trocar de conta?");

        final Context contexto = this;

        DialogInterface.OnClickListener btn01 = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Usuario.clear();
                Intent intent = new Intent(contexto, MainActivity.class);
                startActivity(intent);
                return;
            }
        };

        DialogInterface.OnClickListener btn02 = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        };

        builder.setPositiveButton("Sim", btn01);
        builder.setNegativeButton("Não", btn02);
        builder.create().show();
    }

    public void onBtnExcluirContaClick(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Excluir conta");
        builder.setMessage("Você tem certeza que deseja excluir a sua conta?");

        final Context contexto = this;

        DialogInterface.OnClickListener btn01 = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                boolean deuCerto = false;
                try {
                    deuCerto = DBConector.excluirUsuario(contexto);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(deuCerto) {
                    Usuario.clear();
                    Intent intent = new Intent(contexto, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(contexto, "Erro na rede. Conta não excluida", Toast.LENGTH_LONG).show();
                }
                return;
            }
        };

        DialogInterface.OnClickListener btn02 = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                return;
            }
        };

        builder.setPositiveButton("Sim", btn01);
        builder.setNegativeButton("Não", btn02);
        builder.create().show();
    }

    public void onMuteBoxClick(View v){
        SharedPreferences sp = getSharedPreferences("mySharedPreferences", this.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        MyMediaPlayer.mute();
        editor.putBoolean("mutado", MyMediaPlayer.isMuted());
        editor.apply();

    }

    @Override
    public void onBackPressed() {
        // não chame o super desse método
    }
}
