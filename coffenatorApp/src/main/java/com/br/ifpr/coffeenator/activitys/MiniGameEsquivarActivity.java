package com.br.ifpr.coffeenator.activitys;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.br.ifpr.coffeenator.R;
import com.br.ifpr.coffeenator.myclasses.Arrow;
import com.br.ifpr.coffeenator.myclasses.MyAlertDialogConstructor;
import com.br.ifpr.coffeenator.myclasses.MyMediaPlayer;
import com.br.ifpr.coffeenator.myclasses.PS;

import java.util.ArrayList;
import java.util.Random;

public class MiniGameEsquivarActivity extends AppCompatActivity {

    private int arrowSpeedMin ;
    private int arrowSpeedMax;
    private int quantidadeFlechas;
    private int arrowDamage;
    private long timeBetweenArrows;
    private int projetilId;

    private ArrayList<Arrow> flechas;

    private ImageView heart;

    private ProgressBar vidaBar;

    private int screenWidth;
    private int screenHeigth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_mini_game);
        timerHandler.postDelayed(timerRunnable, 1);

        heart = (ImageView) findViewById(R.id.imgHeart);
        vidaBar = (ProgressBar) findViewById(R.id.vidaBarMiniGame);

        vidaBar.setMax(100);
        vidaBar.setProgress(PS.getVida());

        Display mdisp = getWindowManager().getDefaultDisplay();
        Point mdispSize = new Point();
        mdisp.getSize(mdispSize);
        screenHeigth = mdispSize.y;
        screenWidth = mdispSize.x;
        Arrow.screenHeigth = screenHeigth;

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        arrowSpeedMin = bundle.getInt("velocidade minima");
        arrowSpeedMax = bundle.getInt("velocidade maxima");
        quantidadeFlechas = bundle.getInt("quantidade de flechas");
        timeBetweenArrows = bundle.getInt("tempo entre flechas");
        arrowDamage = bundle.getInt("dano da flecha");
        projetilId = bundle.getInt("projetil id");
        int bg = bundle.getInt("bg");

        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.miniGameLayout);
        layout.setBackgroundResource(bg);
        criarFlechas(quantidadeFlechas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        setHeartPosition(x, y);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
        }
        return false;
    }

    private void criarFlechas(int quantidade) {
        flechas = new ArrayList<Arrow>();
        for (int i = 0; i < quantidade; i++) {
            flechas.add(criarFlechaAleatoria(System.currentTimeMillis() + ((i + 1) * timeBetweenArrows)));
        }
    }

    private Arrow criarFlechaAleatoria(Long startTime) {
        Random r = new Random();

        float x = r.nextFloat() * (screenWidth + 200);
        x -= 100;
        int speed = arrowSpeedMin + r.nextInt(arrowSpeedMax - arrowSpeedMin);

        return criarFlecha(x, speed, startTime);
    }

    private Arrow criarFlecha(float x, int speed, long startTime) {
        ImageView imgArrow = new ImageView(this);
        imgArrow.setBackgroundResource(projetilId);

        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.miniGameLayout);
        layout.addView(imgArrow);

        imgArrow.setX(x);
        imgArrow.setY(-1000);

        Arrow arrow = new Arrow(imgArrow, speed, startTime);
        return arrow;
    }

    private void setHeartPosition(int x, int y) {

        x -= heart.getWidth() / 2;
        y -= heart.getHeight();
        heart.setX(x);
        heart.setY(y);
    }

    public void destruirFlecha(Arrow flecha) {
        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.miniGameLayout);
        layout.removeView(flecha.getImg());
        flechas.remove(flecha);
        if (flechas.size() <= 0 && vidaBar.getProgress() > 0) {
            PS.setVida(vidaBar.getProgress());
            Intent intent = new Intent(this, BatalhaBossActivity.class);
            startActivity(intent);
        }
    }

    private void onFrameUpdate() {
        for (int i = 0; i < flechas.size(); i++) {
            if (flechas.get(i).getStartTime() < System.currentTimeMillis()) {
                flechas.get(i).startAnimation();
                flechas.get(i).attRotation();
                if (flechas.get(i).getImgY() > screenHeigth) {
                    destruirFlecha(flechas.get(i));
                } else if (isCollidingWhitHeart(flechas.get(i))) {
                    hurtPlayer(Math.round(((float) arrowDamage)));
                    destruirFlecha(flechas.get(i));
                }
            }
        }
    }

    private void hurtPlayer(int damage){
        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.miniGameLayout);

        int dur1 = 70 + (int)(Math.random() * 30);
        int dur2 = 70 + (int)(Math.random() * 30);

        int repeticoes = 1;

        Animation an = new RotateAnimation(-3, 3, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

        an.setDuration(dur1);
        an.setRepeatCount(repeticoes);
        an.setRepeatMode(Animation.REVERSE);
        an.setFillAfter(true);

        // Create an animation instance
        Animation an2 = new TranslateAnimation(-TranslateAnimation.RELATIVE_TO_SELF,0.02f,
                TranslateAnimation.RELATIVE_TO_SELF,0.02f,
                -TranslateAnimation.RELATIVE_TO_SELF,0.02f,
                TranslateAnimation.RELATIVE_TO_SELF,0.02f);

        an2.setDuration(dur2);               // duration in ms
        an2.setRepeatCount(repeticoes);      // -1 = infinite repeated
        an2.setRepeatMode(Animation.REVERSE);
        an2.setFillAfter(true);               // keep rotation after animation

        AnimationSet s = new AnimationSet(false);//false means don't share interpolators

        layout.setAnimation(s);
        s.addAnimation(an);
        s.addAnimation(an2);

        MyMediaPlayer.play(this, R.raw.guilherme_gemido);

        vidaBar.setProgress(vidaBar.getProgress() - damage);
        if(vidaBar.getProgress() <= 0){
            MyAlertDialogConstructor.showDeathMessage(getString(R.string.morteEmBatalha), this);
        }
    }

    private boolean isCollidingWhitHeart(Arrow flecha) {
        ImageView img = flecha.getImg();
        if(heart.getX() < img.getX() + img.getWidth() && heart.getX() > img.getX() - heart.getWidth()){
            if(heart.getY() < flecha.getImgY() + img.getHeight() && heart.getY() > flecha.getImgY() - heart.getHeight()){
                return true;
            }
        }
        return false;
    }

    ////// códigos que eu robei dos fóruns

    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {

        @Override
        public void run() {
            onFrameUpdate();
            timerHandler.postDelayed(this, 5);
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




/*
        long deltaTime = System.currentTimeMillis() - oldTimeMillis;
        oldTimeMillis = System.currentTimeMillis();
        for (int i = 0; i < flechas.size(); i++) {
            if(flechas.get(i).getStartTime() < System.currentTimeMillis()) {
                flechas.get(i).move(deltaTime);
                if (!flechas.get(i).isAtiva) {
                    destruirFlecha(flechas.get(i));
                }
            }
        }
        if(flechas.size() == 0){
            ((TextView) findViewById(R.id.txtTeste)).setText("Acabou");
        }
    }
*/
