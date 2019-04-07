package com.br.ifpr.coffeenator.activitys;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.br.ifpr.coffeenator.R;
import com.br.ifpr.coffeenator.myclasses.MyMediaPlayer;
import com.br.ifpr.coffeenator.myclasses.PS;

import java.util.Random;

public class BatalhaBossActivity extends AppCompatActivity {

    private static int velocidadeMinimaI;
    private static int velocidadeMaximaI;
    private static int quantidadeDeFlechasI;
    private static int tempoEntreFlechasI;
    private static float acrescimoNaDificuldade;
    private static float dificuldadeMiniGame;
    private static int projetilId;
    private static int backgroundId;
    private static int biblia;
    private static int bgEsquivar;

    private static int bossVidaMax;
    private static int bossVida;
    private static int danoBoss;
    private static String nomeBoss;
    private float tempoInicial = 3;
    private float tempo;


    private int screenWidth;
    private int screenHeigth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_batalha_boss2);

        tempo = tempoInicial;
        timerHandler.postDelayed(timerRunnable, 10);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle != null) {

            nomeBoss = bundle.getString("boss nome");
            bossVidaMax = bundle.getInt("boss vida");
            bossVida = bossVidaMax;
            danoBoss = bundle.getInt("boss dano");

            velocidadeMinimaI = bundle.getInt("velocidade minima");
            velocidadeMaximaI = bundle.getInt("velocidade maxima");
            tempoEntreFlechasI = bundle.getInt("tempo entre flechas");
            quantidadeDeFlechasI = bundle.getInt("quantidade de flechas");
            acrescimoNaDificuldade = bundle.getFloat("acrescimo na dificuldade");
            backgroundId = bundle.getInt("background id");
            biblia = bundle.getInt("biblia");
            bgEsquivar = bundle.getInt("bg esquivar");
            projetilId = bundle.getInt("projetil id");
            dificuldadeMiniGame = 1;

            MyMediaPlayer.setMusicaDeFundoPlayer(R.raw.rodolfo, this);
        }

        ImageButton btn1 = (ImageButton) findViewById(R.id.btnAtacarBoss);
        ImageButton btn2 = (ImageButton) findViewById(R.id.btnAtacarBoss2);
        ImageButton btn3 = (ImageButton) findViewById(R.id.btnAtacarBoss3);
        ImageButton btn4 = (ImageButton) findViewById(R.id.btnAtacarBoss4);

        btn1.setBackgroundResource(biblia);
        btn2.setBackgroundResource(biblia);
        btn3.setBackgroundResource(biblia);
        btn4.setBackgroundResource(biblia);

        TextView txtNome = (TextView) findViewById(R.id.txtMeuNome);
        TextView txtBoss = (TextView) findViewById(R.id.txtNomeBoss);

        ProgressBar bossVidaBar = (ProgressBar) findViewById(R.id.bossVidaBar);
        ProgressBar playerVidaBar = (ProgressBar) findViewById(R.id.playerVidaBar);
        bossVidaBar.setMax(bossVidaMax);
        bossVidaBar.setProgress(bossVida);
        playerVidaBar.setProgress(PS.getVida());

        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.batalhaBoss2Layout);
        layout.setBackgroundResource(backgroundId);

        txtNome.setText("Edison");
        txtBoss.setText(nomeBoss);

        Display mdisp = getWindowManager().getDefaultDisplay();
        Point mdispSize = new Point();
        mdisp.getSize(mdispSize);
        screenHeigth = mdispSize.y;
        screenWidth = mdispSize.x;

    }

    public void onBtnAtacarBossClick(View v){
        bossVida -= PS.getFe() + 1;
        ProgressBar bossVidaBar = (ProgressBar) findViewById(R.id.bossVidaBar);
        bossVidaBar.setProgress(bossVida);
        MyMediaPlayer.play(this, R.raw.levar_golpe1);
        if(bossVida <= 0){

            ImageButton btn1 = (ImageButton) findViewById(R.id.btnAtacarBoss);
            ImageButton btn2 = (ImageButton) findViewById(R.id.btnAtacarBoss2);
            ImageButton btn3 = (ImageButton) findViewById(R.id.btnAtacarBoss3);
            ImageButton btn4 = (ImageButton) findViewById(R.id.btnAtacarBoss4);

            btn1.setEnabled(false);
            btn2.setEnabled(false);
            btn3.setEnabled(false);
            btn4.setEnabled(false);

            ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.batalhaBoss2Layout);

            int dur1 = 70 + (int)(Math.random() * 30);
            int dur2 = 70 + (int)(Math.random() * 30);

            int repeticoes = 30;

            Animation an = new RotateAnimation(-4, 4, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

            an.setDuration(dur1);
            an.setRepeatCount(repeticoes);
            an.setRepeatMode(Animation.REVERSE);
            an.setFillAfter(true);


            Animation an2 = new TranslateAnimation(-TranslateAnimation.RELATIVE_TO_SELF,0.02f,
                    TranslateAnimation.RELATIVE_TO_SELF,0.02f,
                    -TranslateAnimation.RELATIVE_TO_SELF,0.02f,
                    TranslateAnimation.RELATIVE_TO_SELF,0.02f);

            an2.setDuration(dur2);
            an2.setRepeatCount(repeticoes);
            an2.setRepeatMode(Animation.REVERSE);
            an2.setFillAfter(true);

            AnimationSet s = new AnimationSet(false);
            s.addAnimation(an);
            s.addAnimation(an2);

            layout.setAnimation(s);


            final float volumeMusica = MyMediaPlayer.getVolumeMusica();
            MyMediaPlayer.changeMusicVolume(0);

            MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.caio_gritando);
            mediaPlayer.setVolume(MyMediaPlayer.getVolumeSFX(), MyMediaPlayer.getVolumeSFX());
            mediaPlayer.setLooping(false);
            final Context context = this;

            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    if(mp != null) {
                        mp.release();
                        mp = null;
                        Intent intent = new Intent(context, FinalActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putFloat("volume musica", volumeMusica);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }

                }
            });


            mediaPlayer.start();
        }
        aleatorizarBtnPosition((ImageButton) v);
    }

    private void aleatorizarBtnPosition(ImageButton btn){
        //ImageButton btn = (ImageButton) findViewById(R.id.btnAtacarBoss);

        Random r = new Random();
        int buttonHeight;
        int buttonWidth;
        buttonHeight = btn.getHeight();
        buttonWidth = btn.getWidth();

        int x = r.nextInt(screenWidth - buttonWidth);
        int y = r.nextInt(screenHeigth - buttonHeight);

        btn.setX(x);
        btn.setY(y);
    }

    private void onFrameUpdate(){
        tempo -= 0.01f;
        int color = mixColors(tempo / tempoInicial);

        TextView txtCronometro = (TextView) findViewById(R.id.txtCronometroBatalha);
        txtCronometro.setTextColor(color);
        txtCronometro.setText(String.format("%.2f", tempo) + "s");
    }

    private void onEnd(){

        Intent intent = new Intent(this, MiniGameEsquivarActivity.class);
        Bundle bundle = new Bundle();

        bundle.putInt("velocidade minima", Math.round(velocidadeMinimaI / dificuldadeMiniGame));
        bundle.putInt("velocidade maxima", Math.round(velocidadeMaximaI / dificuldadeMiniGame));
        bundle.putInt("quantidade de flechas", Math.round(quantidadeDeFlechasI * dificuldadeMiniGame));
        bundle.putInt("tempo entre flechas", tempoEntreFlechasI);
        bundle.putInt("dano da flecha", Math.round(dificuldadeMiniGame * danoBoss));
        bundle.putInt("projetil id", projetilId);
        bundle.putInt("bg", bgEsquivar);

        dificuldadeMiniGame += acrescimoNaDificuldade;

        intent.putExtras(bundle);
        startActivity(intent);
    }

    public int mixColors(double percent){
        double inverse_percent = 1.0 - percent;
        int color11 = ContextCompat.getColor(this, R.color.colorVerde);
        int color22 = ContextCompat.getColor(this, R.color.colorVermelho);
        int redPart = (int) (Color.red(color11)*percent + Color.red(color22)*inverse_percent);
        int greenPart = (int) (Color.green(color11)*percent + Color.green(color22)*inverse_percent);
        int bluePart = (int) (Color.blue(color11)*percent + Color.blue(color22)*inverse_percent);
        return Color.rgb(redPart, greenPart, bluePart);
    }

    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {

        @Override
        public void run() {
            if(tempo > 0) {
                onFrameUpdate();
                timerHandler.postDelayed(this, 10);
            } else {
                onEnd();
            }
        }
    };

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
