package com.br.ifpr.coffeenator.activitys;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.br.ifpr.coffeenator.R;
import com.br.ifpr.coffeenator.myclasses.DicionarioDeActivitys;
import com.br.ifpr.coffeenator.myclasses.Gif;
import com.br.ifpr.coffeenator.myclasses.MyMediaPlayer;
import com.br.ifpr.coffeenator.myclasses.PS;

public class MenuActivity extends AppCompatActivity{
    Gif gif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        if(!MyMediaPlayer.isMusicaDeFundoPlaying()) {
            MyMediaPlayer.setMusicaDeFundoPlayer(R.raw.menu, this);
        }
        ImageView fumaca = (ImageView) findViewById(R.id.imgFumaca);
        gif = new Gif(100, fumaca, R.drawable.fumaca03, R.drawable.fumaca02, R.drawable.fumaca01);
        gif.play();

        TextView txt1 = (TextView) findViewById(R.id.txtJogarM);
        TextView txt2 = (TextView) findViewById(R.id.txtInstrucoesM);
        TextView txt3 = (TextView) findViewById(R.id.txtOpcoesM);
        TextView txt4 = (TextView) findViewById(R.id.txtCreditosM);
        TextView txt5 = (TextView) findViewById(R.id.txtRGeralM);
        TextView txt6 = (TextView) findViewById(R.id.txtRPessoalM);

        Typeface tf1 = Typeface.createFromAsset(getAssets(), "font1.ttf");
        txt1.setTypeface(tf1);
        txt2.setTypeface(tf1);
        txt3.setTypeface(tf1);
        txt4.setTypeface(tf1);
        txt5.setTypeface(tf1);
        txt6.setTypeface(tf1);
    }

    public void onBtnJogarMenuClick(View view) {
        PS.setTempoInicio(System.currentTimeMillis()); //
        PS.die(this);
        MyMediaPlayer.playButtonSound(this);
        Intent intent = new Intent(this, CasaActivity.class);
        Bundle b = new Bundle();
        intent.putExtras(b);

        PS.loadFromSave(this);

        startActivity(intent);
    }

    public void onBtnInstrucoesClick(View v) {
        MyMediaPlayer.playButtonSound(this);
        Intent intent = new Intent(this, InstrucoesActivity.class);
        startActivity(intent);
    }

    public void onBtnCreditosClick(View v) {
        MyMediaPlayer.playButtonSound(this);
        Intent intent = new Intent(this, CreditosActivity.class);
        startActivity(intent);
    }

    public void onBtnRankingGeralClick(View v){
        MyMediaPlayer.playButtonSound(this);
        Intent intent = new Intent(this, RankingActivity.class);
        startActivity(intent);
    }

    public void onBtnRankingPessoalClick(View v){
        MyMediaPlayer.playButtonSound(this);
        Intent intent = new Intent(this, HistoricoActivity.class);
        startActivity(intent);
    }

    public void onBtnOpcoesClick(View v) {
        MyMediaPlayer.playButtonSound(this);
        Intent intent = new Intent(this, OpcoesActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("Old Activity ID", DicionarioDeActivitys.getIdByClass(this.getClass()));
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        // não chame o super desse método
    }

    @Override
    public void onPause(){
        gif.stop();
        super.onPause();
    }
}
