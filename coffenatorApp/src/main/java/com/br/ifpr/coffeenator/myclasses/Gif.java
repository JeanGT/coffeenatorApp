package com.br.ifpr.coffeenator.myclasses;

import android.os.Handler;
import android.widget.ImageView;

import java.util.ArrayList;

public class Gif {
    private ImageView imgView;
    private ArrayList<Integer> imagens;
    private int delay;

    private int cImg = 0;

    public Gif(int delay, ImageView imgView, int... imagens){
        this.imgView = imgView;
        this.delay = delay;
        this.imagens = new ArrayList<Integer>();
        for(int imagem : imagens){
            this.imagens.add(imagem);
        }
        attImgView();
    }

    public void play(){
        timerHandler.postDelayed(timerRunnable, delay);
    }

    public void stop(){
        timerHandler.removeCallbacks(timerRunnable);
    }

    private void attImgView(){
        imgView.setImageResource(imagens.get(cImg));
    }

    private void onFrameUpdate(){
        cImg = (cImg + 1) % imagens.size();
        attImgView();
    }

    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {

        @Override
        public void run() {
            onFrameUpdate();
            timerHandler.postDelayed(this, delay);
        }
    };
}
