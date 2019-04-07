package com.br.ifpr.coffeenator.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.br.ifpr.coffeenator.R;
import com.br.ifpr.coffeenator.myclasses.Diario;
import com.br.ifpr.coffeenator.myclasses.DicionarioDeActivitys;
import com.br.ifpr.coffeenator.myclasses.MyAlertDialogConstructor;
import com.br.ifpr.coffeenator.myclasses.MyMediaPlayer;
import com.br.ifpr.coffeenator.myclasses.PS;

public class LojaActivity extends AppCompatActivity {
    private TextView caixaDialogo;
    private TextView txtDinheiro;

    private final float custoComida = 7.5f;
    private final float custoAgua = 2.5f;
    private final float custoCafe = 50;
    private final int delayN = 30;
    private final int delayD = 300;

    private int delay;

    private String targetText;

    private boolean insano;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_loja);
        MyMediaPlayer.play(this, R.raw.entrar1);
        caixaDialogo = (TextView) findViewById(R.id.caixaDialogo);
        txtDinheiro = (TextView) findViewById(R.id.txtDinheiro);
        caixaDialogo.setText("");
        insano = PS.getSanidade() < 2;
        if(insano){
            targetText = getString(R.string.caioBoasVindasInsano);

            Button btnComprarComida = (Button) findViewById(R.id.btnComprarComida);
            Button btnComprarAgua = (Button) findViewById(R.id.btnComprarAgua);
            Button btnComprarCafe = (Button) findViewById(R.id.btnComprarCafe);
            ImageView imgBalcao = (ImageView) findViewById(R.id.imgBalcao);
            ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.barLayout);

            layout.setBackgroundResource(R.drawable.fundo_insano);

            imgBalcao.setImageResource(R.drawable.bar_balcao_insano);
            imgBalcao.clearColorFilter();

            btnComprarComida.setText("Vender sua alma");
            btnComprarAgua.setText("Comprar café R$ 99999,99");
            btnComprarCafe.setText("Roubar café");
        } else {
            targetText = getString(R.string.caioBoasVindas);
        }

        if(!PS.isEntrouNaLoja()){
            PS.setEntrouNaLoja(true);
            Diario.addMensagem(R.string.diarioLojinha);
        }

        timerHandler.postDelayed(timerRunnable, 1);
        attTxtDinheiro();
    }

    private void attTxtDinheiro(){
        txtDinheiro.setText("R$ " + String.format("%.2f", PS.getDinheiros()).replace(".", ","));
    }

    public void onBtnComprarComidaLojaClick(View v){
        PS.fazerAcao();
        MyMediaPlayer.playButtonSound(this);
        if(insano){
            MyAlertDialogConstructor.showDeathMessage(getString(R.string.mortePorVenderAAlma), this);
        } else {
            caixaDialogo.setText("");
            if (PS.getDinheiros() >= custoComida) {
                MyMediaPlayer.play(this, R.raw.comer);
                targetText = getString(R.string.caioPedirMiojo) + comprarComida();
                attTxtDinheiro();
            } else {
                targetText = getString(R.string.f_cComprarComida);
            }
        }
    }

    private String comprarComida(){
        return PS.changeFome(-10, this) + PS.changeSede(1, this) + PS.changeDinheiros(-custoComida);
    }

    public void onBtnComprarAguaLojaClick(View v){
        PS.fazerAcao();
        MyMediaPlayer.playButtonSound(this);
        if(insano){
            caixaDialogo.setText("");
            if (PS.getDinheiros() >= 100000) {
                targetText = getString(R.string.caioPedirCafe) + comprarCafe();
                attTxtDinheiro();
            } else {
                targetText = getString(R.string.f_cComprarCafe);
            }
        } else {
            caixaDialogo.setText("");
            if (PS.getDinheiros() >= custoAgua) {
                MyMediaPlayer.play(this, R.raw.beber_agua);
                targetText = getString(R.string.caioAgua) + comprarAgua();
                attTxtDinheiro();
            } else {
                targetText = getString(R.string.f_cComprarAgua);
            }
        }
    }

    private String comprarAgua(){
        return PS.changeSede(-10, this) + PS.changeDinheiros(-custoAgua);
    }

    public void onBtnComprarCafeLojaClick(View v){
        PS.fazerAcao();
        MyMediaPlayer.playButtonSound(this);
        if(insano){
            Intent intent = new Intent(this, BatalhaBossActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("boss nome", "Caio, o balconista");
            bundle.putInt("boss vida", 1000);
            bundle.putInt("boss dano", 6);
            bundle.putInt("velocidade minima", 1500);
            bundle.putInt("velocidade maxima", 3000);
            bundle.putInt("tempo entre flechas", 200);
            bundle.putInt("quantidade de flechas", 20);
            bundle.putBoolean("caio", true);
            bundle.putInt("biblia", R.drawable.biblia);
            bundle.putInt("bg esquivar", R.drawable.bar_interior_insano);

            bundle.putFloat("acrescimo na dificuldade", 0.05f);

            bundle.putInt("background id", R.drawable.bar_interior_insano);
            bundle.putInt("projetil id", R.drawable.garrafa);
            intent.putExtras(bundle);
            startActivity(intent);
        } else {
            caixaDialogo.setText("");
            if (PS.getDinheiros() >= custoCafe) {
                targetText = getString(R.string.caioPedirCafe) + comprarCafe();
                attTxtDinheiro();
            } else {
                targetText = getString(R.string.f_cComprarCafe);
            }
        }
    }

    private String comprarCafe(){
        return PS.changeDinheiros(-custoCafe) + PS.changeSanidade(10, this);
    }

    public void onBtnStatusLojaClick(View view){
        MyMediaPlayer.playButtonSound(this);
        Intent intent = new Intent(this, StatusActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("Old Activity ID", DicionarioDeActivitys.getIdByClass(this.getClass()));
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void onBtnOpcoesLojaClick(View v){
        MyMediaPlayer.playButtonSound(this);
        Intent intent = new Intent(this, OpcoesActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("Old Activity ID", DicionarioDeActivitys.getIdByClass(this.getClass()));
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void onBtnVoltarLojaClick(View v){
        MyMediaPlayer.playButtonSound(this);
        Intent intent = new Intent(this, Mapa03Activity.class);
        startActivity(intent);
    }

    private void onFrameUpdate() {
        if(!caixaDialogo.getText().equals(targetText)){
            caixaDialogo.setText(targetText.substring(0, caixaDialogo.getText().length() + 1));
            if(caixaDialogo.getText().charAt(caixaDialogo.getText().length() - 1) == '.'){
                delay = delayD;
            } else {
                delay = delayN;
            }
        }
    }

    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {

        @Override
        public void run() {
            onFrameUpdate();
            timerHandler.postDelayed(this, delay);
        }
    };

    public void onCaixaDialogoClick(View v){
        caixaDialogo.setText(targetText);
    }

    @Override
    public void onBackPressed() {
        // não chame o super desse método
    }

    @Override
    public void onPause(){
        timerHandler.removeCallbacks(timerRunnable);
        super.onPause();
    }
}
