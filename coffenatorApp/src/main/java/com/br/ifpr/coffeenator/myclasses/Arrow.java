package com.br.ifpr.coffeenator.myclasses;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.widget.ImageView;

public class Arrow {
    private float imgY;
    private int speed;
    private long startTime;
    private ImageView img;
    private boolean animationStarted = false;
    public static float screenHeigth;


    public Arrow(ImageView img, int speed, long startTime){
        this.img = img;
        this.speed = speed;
        this.startTime = startTime;
    }

    public void startAnimation(){
        if(!animationStarted) {
            ObjectAnimator animation = ObjectAnimator.ofFloat(img, "translationY", screenHeigth + 1000);
            animation.setDuration(speed);
            animation.start();

            animation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    imgY = (Float)animation.getAnimatedValue();
                }
            });

            animationStarted = true;
        }
    }

    public void attRotation(){
        img.setRotation(speed * imgY / 10000);
    }

    public ImageView getImg(){
        return img;
    }

    public long getStartTime(){
        return startTime;
    }

    public int getImgY(){
        return Math.round(imgY);
    }
}
