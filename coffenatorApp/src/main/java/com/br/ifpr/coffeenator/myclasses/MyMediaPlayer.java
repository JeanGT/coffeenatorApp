package com.br.ifpr.coffeenator.myclasses;

import android.content.Context;
import android.media.MediaPlayer;

import java.util.Random;

import com.br.ifpr.coffeenator.R;

public class MyMediaPlayer {

    private static MediaPlayer musicaDeFundoPlayer;
    private static boolean isMuted = false;

    private static float volumeMusica = 0.8f;
    private static float volumeSFX = 0.8f;

    static {
        musicaDeFundoPlayer = new MediaPlayer();
    }

    private MyMediaPlayer(){}

    public static void play(Context contexto, int musicaId) {
        if(!isMuted) {
            MediaPlayer mediaPlayer = MediaPlayer.create(contexto, musicaId);
            mediaPlayer.setVolume(volumeSFX, volumeSFX);
            mediaPlayer.setLooping(false);
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    if(mp != null) {
                        mp.release();
                        mp = null;
                    }
                }
            });
            mediaPlayer.start();

        }
    }

    public static void playButtonSound(Context contexto) {
        Random r = new Random();
        int random = r.nextInt(3);
        int soundId = 0;
        switch (random) {
            case 0:
                soundId = R.raw.button_sound;
                break;
            case 1:
                soundId = R.raw.button_sound_02;
                break;
            case 2:
                soundId = R.raw.button_sound_03;
                break;
            default:
                break;
        }
        play(contexto, soundId);
    }
/*
    public static void playTypeSound(Context contexto) {
        if(!isMuted) {
            Random r = new Random();
            int random = r.nextInt(3);
            int soundId = 0;
            switch (random) {
                case 0:
                    soundId = R.raw.tecla1;
                    break;
                case 1:
                    soundId = R.raw.tecla2;
                    break;
                case 2:
                    soundId = R.raw.tecla4;
                    break;
                default:
                    break;
            }
            play(contexto, soundId);
            MediaPlayer mediaPlayer = MediaPlayer.create(contexto, soundId);
            mediaPlayer.setVolume(volumeSFX, volumeSFX);
            mediaPlayer.setLooping(false);
            mediaPlayer.start();
        }
    }
*/
    public static void setMusicaDeFundoPlayer(int musicaId, Context context) {
        MyMediaPlayer.musicaDeFundoPlayer.stop();
        MyMediaPlayer.musicaDeFundoPlayer.release();
        if(musicaId == 0){
            MyMediaPlayer.musicaDeFundoPlayer.stop();
            MyMediaPlayer.musicaDeFundoPlayer.release();
            return;
        }
        MyMediaPlayer.musicaDeFundoPlayer = MediaPlayer.create(context.getApplicationContext(), musicaId);
        if(!isMuted) {
            musicaDeFundoPlayer.setVolume(volumeMusica, volumeMusica);
        } else {
            musicaDeFundoPlayer.setVolume(0, 0);
        }
        MyMediaPlayer.musicaDeFundoPlayer.setLooping(true);
        MyMediaPlayer.musicaDeFundoPlayer.start();
    }

    public static void changeMusicVolume(float volume){
        if(!isMuted) {
            musicaDeFundoPlayer.setVolume(volume, volume);
        }
        volumeMusica = volume;
    }

    public static void changeSFXVolume(float volume){
        volumeSFX = volume;
    }

    public static void mute(){
        if(isMuted){
            isMuted = false;
            musicaDeFundoPlayer.setVolume(volumeMusica, volumeMusica);
        } else {
            isMuted = true;
            musicaDeFundoPlayer.setVolume(0, 0);
        }
    }

    public static boolean isMusicaDeFundoPlaying(){
        if(musicaDeFundoPlayer != null) {
            return musicaDeFundoPlayer.isPlaying();
        } else {
            return false;
        }
    }

    public static boolean isMuted() {
        return isMuted;
    }

    public static float getVolumeMusica() {
        return volumeMusica;
    }

    public static float getVolumeSFX() {
        return volumeSFX;
    }
}
