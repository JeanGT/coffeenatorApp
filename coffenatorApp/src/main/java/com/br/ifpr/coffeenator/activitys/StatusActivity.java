package com.br.ifpr.coffeenator.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.br.ifpr.coffeenator.R;
import com.br.ifpr.coffeenator.myclasses.DicionarioDeActivitys;
import com.br.ifpr.coffeenator.myclasses.MyAlertDialogConstructor;
import com.br.ifpr.coffeenator.myclasses.MyMediaPlayer;
import com.br.ifpr.coffeenator.myclasses.PS;

public class StatusActivity extends AppCompatActivity {

    int oldActivityId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_status);
        Intent oldIntent = getIntent();
        Bundle bundle = oldIntent.getExtras();
        oldActivityId = bundle.getInt("Old Activity ID");
        attStatus();
        if(!PS.isEntrouNosStatus()){
            PS.setEntrouNosStatus(true);
            MyAlertDialogConstructor.showMessage(getString(R.string.tIntrucoes), getString(R.string.instrucoesStatus), this);
        }
    }

    public void onBtnStatusVoltarClick(View view) {
        MyMediaPlayer.playButtonSound(this);
        Intent intent = new Intent(this, DicionarioDeActivitys.getClassById(oldActivityId));
        startActivity(intent);
    }

    private void attStatus(){
        ProgressBar barraDeFome = (ProgressBar) findViewById(R.id.fomeBar);
        barraDeFome.setProgress(PS.getFome() * 10 + 1);

        ProgressBar barraDeSede = (ProgressBar) findViewById(R.id.sedeBar);
        barraDeSede.setProgress(PS.getSede() * 10 + 1);

        ProgressBar barraDeSanidade = (ProgressBar) findViewById(R.id.sanidadeBar);
        barraDeSanidade.setProgress(PS.getSanidade() * 10 + 1);

        ProgressBar barraDeEnergia = (ProgressBar) findViewById(R.id.energiaBar);
        barraDeEnergia.setProgress(PS.getEnergia() * 10 + 1);

        ProgressBar barraDeFe = (ProgressBar) findViewById(R.id.feBar);
        barraDeFe.setProgress(PS.getFe() * 10 + 1);

        ProgressBar barraDeInteligencia = (ProgressBar) findViewById(R.id.inteligenciaBar);
        barraDeInteligencia.setProgress(PS.getInteligencia() * 10 + 1);

        TextView txtDinheiros = (TextView) findViewById(R.id.txtDinheiros);
        txtDinheiros.setText("R$ " + String.format("%.2f", PS.getDinheiros()).replace(".", ","));

        TextView txtFome = (TextView) findViewById(R.id.txtFome);
        txtFome.setText("Fome " + PS.getFome() + "/10");

        TextView txtSede = (TextView) findViewById(R.id.txtSede);
        txtSede.setText("Sede " + PS.getSede() + "/10");

        TextView txtEnergia = (TextView) findViewById(R.id.txtEnergia);
        txtEnergia.setText("Energia " + PS.getEnergia() + "/10");

        TextView txtSanidade = (TextView) findViewById(R.id.txtSanidade);
        txtSanidade.setText("Sanidade " + PS.getSanidade() + "/10");

        TextView txtInteligencia = (TextView) findViewById(R.id.txtInteligencia);
        txtInteligencia.setText("Inteligência " + PS.getInteligencia() + "/10");

        TextView txtFe = (TextView) findViewById(R.id.txtFe);
        txtFe.setText("Fé " + PS.getFe() + "/10");

    }

    @Override
    public void onBackPressed() {
        // não chame o super desse método
    }
}
