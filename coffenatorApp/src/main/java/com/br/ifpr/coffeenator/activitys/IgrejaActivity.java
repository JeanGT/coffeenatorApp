package com.br.ifpr.coffeenator.activitys;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;

import com.br.ifpr.coffeenator.R;
import com.br.ifpr.coffeenator.myclasses.Diario;
import com.br.ifpr.coffeenator.myclasses.DicionarioDeActivitys;
import com.br.ifpr.coffeenator.myclasses.MyAlertDialogConstructor;
import com.br.ifpr.coffeenator.myclasses.MyMediaPlayer;
import com.br.ifpr.coffeenator.myclasses.PS;

import java.util.Random;

public class IgrejaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_igreja);
        if(!PS.isEntrouNaIgreja()) {
            MyAlertDialogConstructor.showMessage(getString(R.string.tPadre), getString(R.string.padreBoasVindas), this);
            PS.setEntrouNaIgreja(true);
        }
        ImageButton btnPadre = (ImageButton) findViewById(R.id.imgPadre);
        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) btnPadre.getLayoutParams();
        Random r = new Random();
        params.horizontalBias = r.nextFloat(); // here is one modification for example. modify anything else you want :)
        btnPadre.setLayoutParams(params); // request the view to use the new modified params
    }

    public void onBtnRezarIgrejaClick(View v){
        PS.fazerAcao();
        MyMediaPlayer.playButtonSound(this);
        MyAlertDialogConstructor.showMessage(getString(R.string.tRezar), getString(R.string.cRezar) + rezar(), this);
        if(PS.getFe() == 1){
            Diario.addMensagem(R.string.diarioRezar);
        }
    }

    private String rezar(){
        return PS.changeFe(1) + PS.changeFome(1, this) + PS.changeSede(2, this) + PS.changeEnergia(-2, this);
    }

    public void onBtnBeberAguaIgrejaClick(View v){
        PS.fazerAcao();
        MyMediaPlayer.playButtonSound(this);
        PS.setBebeuAguaBenta(true);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle(getString(R.string.tBeberAguaBenta));
        builder.setMessage(getString(R.string.cBeberAguaBenta) + PS.changeSede(-10, this));

        final Context contexto = this;

        DialogInterface.OnClickListener btnOk = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(contexto, Mapa01Activity.class);
                startActivity(intent);
                return;
            }
        };

        builder.setPositiveButton("Ok", btnOk);
        builder.create().show();

    }

    public void onBtnPadreIgrejaClick(View v){
        PS.fazerAcao();
        MyMediaPlayer.playButtonSound(this);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(getString(R.string.tPadre));
        builder.setMessage(getString(R.string.padreBoasVindas));

        final Context contexto = this;

        DialogInterface.OnClickListener btn01 = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyAlertDialogConstructor.showMessage(getString(R.string.tPadre), getString(R.string.padreProjeto), contexto);
                return;
            }
        };

        DialogInterface.OnClickListener btn02 = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                AlertDialog.Builder builder = new AlertDialog.Builder(contexto);
                builder.setTitle(getString(R.string.padreDoar));

                final EditText input = new EditText(contexto);
                input.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                builder.setView(input);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String m_Text = input.getText().toString();
                        float valorDoado = Math.round(Float.parseFloat(m_Text) * 100) / 100;
                        if(valorDoado != 0){
                            if(PS.getDinheiros() >= valorDoado) {
                                PS.changeDinheiros(-valorDoado);
                                MyAlertDialogConstructor.showMessage(getString(R.string.tPadre), getString(R.string.padreDoarSucesso), contexto);
                            } else {
                                MyAlertDialogConstructor.showMessage(getString(R.string.tPadre), "Você não possui essa quantidade de dinheiro.", contexto);
                            }
                        } else {
                           setExpulso();
                        }
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setExpulso();
                        dialog.cancel();
                    }
                });

                builder.show();
                return;
            }
        };

        DialogInterface.OnClickListener btn03 = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyAlertDialogConstructor.showDeathMessage(getString(R.string.mortePadre), contexto);
                return;
            }
        };

        builder.setPositiveButton("Perguntar sobre o projeto", btn01);
        builder.setNegativeButton("Doar", btn02);
        builder.setNeutralButton("Atacar padre", btn03);
        builder.create().show();
    }

    private void setExpulso(){
        PS.setBebeuAguaBenta(true);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle(getString(R.string.tPadre));
        builder.setMessage(getString(R.string.padreDoarFracasso));

        final Context contexto = this;

        DialogInterface.OnClickListener btnOk = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(contexto, Mapa01Activity.class);
                startActivity(intent);
                return;
            }
        };

        builder.setPositiveButton("Ok", btnOk);
        builder.create().show();
    }

    public void onBtnStatusIgrejaClick(View view){
        MyMediaPlayer.playButtonSound(this);
        Intent intent = new Intent(this, StatusActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("Old Activity ID", DicionarioDeActivitys.getIdByClass(this.getClass()));
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void onBtnOpcoesIgrejaClick(View v){
        MyMediaPlayer.playButtonSound(this);
        Intent intent = new Intent(this, OpcoesActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("Old Activity ID", DicionarioDeActivitys.getIdByClass(this.getClass()));
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void onBtnVoltarIgrejaClick(View v){
        MyMediaPlayer.playButtonSound(this);
        Intent intent = new Intent(this, Mapa01Activity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        // não chame o super desse método
    }
}
