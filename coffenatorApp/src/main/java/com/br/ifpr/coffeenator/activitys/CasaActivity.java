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

public class CasaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_casa);
        if(PS.getSanidade() < 2){
            Button btnDormir = (Button) findViewById(R.id.btnDormir);
            Button btnSuicidio = (Button) findViewById(R.id.btnSuicidio);
            ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.casaLayout);

            btnDormir.setBackgroundResource(R.drawable.cama_insano);
            btnSuicidio.setBackgroundResource(R.drawable.ventilador_insano);
            layout.setBackgroundResource(R.drawable.casa_interior_insano);
        }
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null){
            String mensagem = getString(R.string.diarioIntroducao);
            mensagem = mensagem.replaceAll("Nickname", PS.getNome());
            MyAlertDialogConstructor.showMessage("Diário", mensagem, this);
        }
    }

    public void onBtnDormirCasaClick(View v){
        PS.fazerAcao();
        MyMediaPlayer.playButtonSound(this);
        Diario.showMessages(this);
    }

    public static void dormir(Context c){
        if(PS.isPaquerouMulherDoChefe()){
            MyAlertDialogConstructor.showDeathMessage(c.getString(R.string.mortePorPaquera), c);
        }
        MyAlertDialogConstructor.showMessage(c.getString(R.string.tDomir), c.getString(R.string.cDormir) + PS.changeEnergia(10, c) + PS.changeFome(2, c) + PS.changeSede(3, c), c);
    }

    public void onBtnSuicidioCasaClick(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Tem certeza?");
        builder.setMessage("Deseja cometer suicidio?");

        final Context context = this;

        DialogInterface.OnClickListener btnSim = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                PS.fazerAcao();
                MyAlertDialogConstructor.showDeathMessage(getString(R.string.mortePorSuicidio), context);
            }
        };

        DialogInterface.OnClickListener btnNao = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        };

        builder.setPositiveButton("Sim",  btnSim);
        builder.setNegativeButton("Por enquanto não...", btnNao);
        builder.create().show();
        MyMediaPlayer.playButtonSound(this);
    }

    public void onBtnProcurarCafeCasaClick(View v) {
        PS.fazerAcao();
        MyMediaPlayer.playButtonSound(this);
        if (PS.getSanidade() < 2) {
            MyAlertDialogConstructor.showMessage("Sanidade muito baixa...", "É só catchup.", this);
        } else {
            MyAlertDialogConstructor.showMessage(getString(R.string.tProcurarCafe), getString(R.string.cProcurarCafe), this);
        }
    }

    public void onBtnStatusCasaClick(View v){
        MyMediaPlayer.playButtonSound(this);
        Intent intent = new Intent(this, StatusActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("Old Activity ID", DicionarioDeActivitys.getIdByClass(this.getClass()));
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void onBtnOpcoesCasaClick(View v){
        MyMediaPlayer.playButtonSound(this);
        Intent intent = new Intent(this, OpcoesActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("Old Activity ID", DicionarioDeActivitys.getIdByClass(this.getClass()));
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void onBtnVoltarCasaClick(View v){
        MyMediaPlayer.playButtonSound(this);
        Intent intent = new Intent(this, Mapa02Activity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        // não chame o super desse método
    }
}
