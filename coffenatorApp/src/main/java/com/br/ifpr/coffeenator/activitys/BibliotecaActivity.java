package com.br.ifpr.coffeenator.activitys;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.br.ifpr.coffeenator.R;
import com.br.ifpr.coffeenator.myclasses.Diario;
import com.br.ifpr.coffeenator.myclasses.DicionarioDeActivitys;
import com.br.ifpr.coffeenator.myclasses.MyAlertDialogConstructor;
import com.br.ifpr.coffeenator.myclasses.MyMediaPlayer;
import com.br.ifpr.coffeenator.myclasses.PS;

public class BibliotecaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biblioteca);
    }

    public void onBtnEstudarBibliotecaClick(View v){
        PS.fazerAcao();
        MyMediaPlayer.playButtonSound(this);
        MyAlertDialogConstructor.showMessage(getString(R.string.tEstudar), getString(R.string.cEstudar) + estudar(), this);
        if(PS.getInteligencia() == 1){
            Diario.addMensagem(R.string.diarioEstudar);
        }
    }

    private String estudar(){
        return PS.changeInteligencia(1) + PS.changeFome(2, this) + PS.changeSede(3, this) + PS.changeEnergia(-3, this);
    }

    public void onBtnPaquerarBibliotecaClick(View v){
        PS.fazerAcao();
        MyMediaPlayer.playButtonSound(this);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(getString(R.string.tBiblio));
        builder.setMessage(getString(R.string.biblioBoasVindas));

        final Context contexto = this;

        DialogInterface.OnClickListener btnPaquerar = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(!PS.isPaquerouMulherDoChefe()) {
                    Diario.addMensagem(R.string.diarioPaquerar);
                    MyAlertDialogConstructor.showMessage(getString(R.string.tBiblio), getString(R.string.biblioPaquera), contexto);
                } else {
                    MyAlertDialogConstructor.showMessage(getString(R.string.tBiblio), getString(R.string.cPaquerar), contexto);
                }
                PS.setPaquerouMulherDoChefe(true);
                return;
            }
        };

        DialogInterface.OnClickListener btnCafe = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyAlertDialogConstructor.showMessage(getString(R.string.tBiblio), getString(R.string.cPedirCafe), contexto);
                return;
            }
        };

        builder.setPositiveButton("Paquerar", btnPaquerar);
        builder.setNegativeButton("Pedir café", btnCafe);
        builder.create().show();
    }

    public void onBtnStatusBibliotecaClick(View view){
        MyMediaPlayer.playButtonSound(this);
        Intent intent = new Intent(this, StatusActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("Old Activity ID", DicionarioDeActivitys.getIdByClass(this.getClass()));
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void onBtnOpcoesBibliotecaClick(View v){
        MyMediaPlayer.playButtonSound(this);
        Intent intent = new Intent(this, OpcoesActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("Old Activity ID", DicionarioDeActivitys.getIdByClass(this.getClass()));
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void onBtnVoltarBibliotecaClick(View v){
        MyMediaPlayer.playButtonSound(this);
        Intent intent = new Intent(this, Mapa01Activity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        // não chame o super desse método
    }
}
