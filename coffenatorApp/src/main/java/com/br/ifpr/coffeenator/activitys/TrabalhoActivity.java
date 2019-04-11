package com.br.ifpr.coffeenator.activitys;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.br.ifpr.coffeenator.R;
import com.br.ifpr.coffeenator.myclasses.Diario;
import com.br.ifpr.coffeenator.myclasses.DicionarioDeActivitys;
import com.br.ifpr.coffeenator.myclasses.MyAlertDialogConstructor;
import com.br.ifpr.coffeenator.myclasses.MyMediaPlayer;
import com.br.ifpr.coffeenator.myclasses.PS;

import java.util.Random;

public class TrabalhoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trabalho);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null){
            MyAlertDialogConstructor.showMessage(getString(R.string.tChefe), getString(R.string.cReceberSalario) + receberSalario(), this);
        }
        Button btnTrabalho = (Button) findViewById(R.id.btnEntrevista);
        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) btnTrabalho.getLayoutParams();
        Random r = new Random();
        params.horizontalBias = r.nextFloat(); // here is one modification for example. modify anything else you want :)
        btnTrabalho.setLayoutParams(params); // request the view to use the new modified params
    }

    private String receberSalario(){
        Random r = new Random();
        return PS.changeDinheiros(PS.getInteligencia() + ((float)Math.round(r.nextFloat() * 200)) / 100) + PS.changeEnergia(-3, this) + PS.changeFome(2, this) + PS.changeSede(3, this) + PS.changeSanidade(-1, this);
    }

    public void onBtnFazerEntrevistaTrabalhoClick(View v){
        MyMediaPlayer.playButtonSound(this);
        if(PS.isTemEmprego()){
            MyAlertDialogConstructor.showMessage(getString(R.string.f_tFazerEntrevista), getString(R.string.f_cFazerEntrevista), this);
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(true);
            builder.setTitle(getString(R.string.tFazerEntrevista));
            builder.setMessage(getString(R.string.cFazerEntrevista));

            final Context contexto = this;

            DialogInterface.OnClickListener btnSim = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    PS.fazerAcao();
                    if(PS.getInteligencia() > 2){
                        MyAlertDialogConstructor.showMessage(getString(R.string.tChefe), getString(R.string.cFoiContratado) + custoFazerEntrevista(), contexto);
                        Diario.addMensagem(R.string.diarioEmprego);
                        PS.setTemEmprego(true);
                    } else {
                        MyAlertDialogConstructor.showMessage(getString(R.string.tChefe), getString(R.string.f_cFoiContrado) + custoFazerEntrevista(), contexto);
                    }
                    return;
                }
            };

            DialogInterface.OnClickListener btnDepois = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    return;
                }
            };

            builder.setPositiveButton(getString(R.string.sim), btnSim);
            builder.setNegativeButton(getString(R.string.depois), btnDepois);
            builder.create().show();
        }
    }

    private String custoFazerEntrevista(){
        return PS.changeFome(1, this) + PS.changeSede(1, this) + PS.changeEnergia(-1, this);
    }

    public void onBtnTrabalharTrabalhoClick(View v){
        PS.fazerAcao();
        MyMediaPlayer.playButtonSound(this);
        if(PS.isTemEmprego()){
            if(!PS.isProgramou()){
                PS.setProgramou(true);
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setCancelable(true);
                builder.setTitle(getString(R.string.tIntrucoes));
                builder.setMessage(getString(R.string.instrocoesProgramar));

                DialogInterface.OnClickListener btnOk = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        irTrabalhar();
                        return;
                    }
                };

                builder.setPositiveButton("Ok", btnOk);
                builder.show();
                return;
            } else {
                irTrabalhar();
            }
        } else {
            MyAlertDialogConstructor.showDeathMessage(getString(R.string.mortePorTrabalhar), this);
        }

    }

    private void irTrabalhar(){
        Intent intent = new Intent(this, TrabalhandoActivity.class);
        startActivity(intent);
    }

    public void onBtnMaquinaDeCafeTrabalhoClick(View v){
        PS.fazerAcao();
        MyMediaPlayer.playButtonSound(this);
        if(PS.isTemEmprego()){
            MyAlertDialogConstructor.showMessage(getString(R.string.tMaquinaDeCafe), getString(R.string.cMaquinaDeCafe), this);
        } else {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(false);
            builder.setTitle(getString(R.string.f_tMaquinaDeCafe));
            builder.setMessage(getString(R.string.f_cMaquinaDeCafe));

            final Context contexto = this;

            DialogInterface.OnClickListener btnOk = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent = new Intent(contexto, Mapa03Activity.class);
                    startActivity(intent);
                    return;
                }
            };

            builder.setPositiveButton("Ok", btnOk);
            builder.create().show();
        }
    }

    public void onBtnStatusTrabalhoClick(View view){
        MyMediaPlayer.playButtonSound(this);
        Intent intent = new Intent(this, StatusActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("Old Activity ID", DicionarioDeActivitys.getIdByClass(this.getClass()));
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void onBtnOpcoesTrabalhoClick(View v){
        MyMediaPlayer.playButtonSound(this);
        Intent intent = new Intent(this, OpcoesActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("Old Activity ID", DicionarioDeActivitys.getIdByClass(this.getClass()));
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void onBtnVoltarTrabalhoClick(View v){
        MyMediaPlayer.playButtonSound(this);
        Intent intent = new Intent(this, Mapa03Activity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        // não chame o super desse método
    }
}
