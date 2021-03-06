package com.br.ifpr.coffeenator.myclasses;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Debug;
import android.util.Log;
import android.widget.Toast;

import com.br.ifpr.coffeenator.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class PS {
    private static char[] alphabet = {'/', '.', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
    private static char[] encodedAlphabet = {'/', '.', '1', 'g', 'i', '3', '7', 'u', '2', 'v', 'c', 'z', 'k', 'l', '4', 'o', 'f', '6', 'e', 'p', '5', 'a', '8', 's', 't', 'r', 'y', 'q', 'x', '9', 'n', 'm', '0', 'b', 'j', 'd', 'h', 'w'};

    private static String nome = "";

    private static int fome;
    private static int sede;
    private static int energia;
    private static int sanidade;
    private static int fe;
    private static int inteligencia;
    private static int vida;
    private static int nAcoes;

    private static long tempoInicio;

    private static float dinheiros;

    private static boolean paquerouMulherDoChefe;
    private static boolean bebeuAguaBenta;
    private static boolean temEmprego;
    private static boolean entrouNaIgreja;
    private static boolean entrouNaLoja;
    private static boolean programou;
    private static boolean entrouNosStatus;

    private PS(){}

    public static String changeFome(int dif, Context contexto){
        setFome(getFome() + dif, contexto);
        String retorno = "Fome: ";
        if(dif > 0){
            retorno += "+";
        }
        return "\n" + retorno + dif;
    }

    public static String changeSede(int dif, Context contexto){
        setSede(getSede() + dif, contexto);
        String retorno = "Sede: ";
        if(dif > 0){
            retorno += "+";
        }
        return "\n" + retorno + dif;
    }

    public static String changeSanidade(int dif, Context c){
        setSanidade(getSanidade() + dif);
        String retorno = "Sanidade: ";
        if(dif > 0){
            retorno += "+";
        }

        if(sanidade < 2){
            MyMediaPlayer.setMusicaDeFundoPlayer(R.raw.fundo_insano, c);
        }

        return "\n" + retorno + dif;
    }

    public static String changeDinheiros (float dif){
        setDinheiros(getDinheiros() + dif);
        return "\n" + "R$" + String.format("%.2f", dif).replace(".", ",");
    }

    public static String changeFe(int dif){
        setFe(getFe() + dif);
        String retorno = "Fé: ";
        if(dif > 0){
            retorno += "+";
        }
        return "\n" + retorno + dif;
    }

    public static String changeInteligencia (int dif){
        setInteligencia(getInteligencia() + dif);
        String retorno = "Inteligência: ";
        if(dif > 0){
            retorno += "+";
        }
        return "\n" + retorno + dif;
    }

    public static String changeEnergia(int dif, Context contexto){
        setEnergia(getEnergia() + dif, contexto);
        String retorno = "Energia: ";
        if(dif > 0){
            retorno += "+";
        }
        return "\n" + retorno + dif;
    }

    public static int getFome() {
        return fome;
    }

    private static void setFome(int fome, Context contexto) {
        if(fome > 0) {
            PS.fome = fome;
            if(fome >= 10){
                MyAlertDialogConstructor.showDeathMessage(contexto.getString(R.string.mortePorFome), contexto);
            }
        } else {
            PS.fome = 0;
        }
    }

    public static int getSede() {
        return sede;
    }

    private static void setSede(int sede, Context contexto) {
        if(sede > 0) {
            PS.sede = sede;
            if(sede >= 10){
                MyAlertDialogConstructor.showDeathMessage(contexto.getString(R.string.mortePorSede), contexto);
            }
        } else {
            PS.sede = 0;
        }
    }

    public static int getEnergia() {
        return energia;
    }

    private static void setEnergia(int energia, Context contexto) {
        if(energia < 10) {
            PS.energia = energia;
            if(PS.energia <= 0){
                MyAlertDialogConstructor.showDeathMessage(contexto.getString(R.string.mortePorEnergia), contexto);
            }
        } else {
            PS.energia = 10;
        }
    }

    public static int getSanidade() {
        return sanidade;
    }

    public static void setSanidade(int sanidade) {
        PS.sanidade = sanidade;
        if(sanidade > 30){
            PS.sanidade = 30;
        } else if(sanidade < 0){
            PS.sanidade = 0;
        }
    }

    public static float getDinheiros() {
        return dinheiros;
    }

    public static void setDinheiros(float dinheiros) {
        PS.dinheiros = dinheiros;
    }

    public static int getFe() {
        return fe;
    }

    public static void setFe(int fe) {
        if(fe > 0) {
            PS.fe = fe;
            if(fe > 10){
                PS.fe = 10;
            }
        } else {
            PS.fe = 0;
        }
    }

    public static int getInteligencia() {
        return inteligencia;
    }

    public static void setInteligencia(int inteligencia) {
        if(inteligencia > 0) {
            PS.inteligencia = inteligencia;
            if(inteligencia > 10){
                PS.inteligencia = 10;
            }
        } else {
            PS.inteligencia = 0;
        }
    }

    public static boolean isPaquerouMulherDoChefe() {
        return paquerouMulherDoChefe;
    }

    public static void setPaquerouMulherDoChefe(boolean paquerouMulherDoChefe) {
        PS.paquerouMulherDoChefe = paquerouMulherDoChefe;
    }

    public static boolean isBebeuAguaBenta() {
        return bebeuAguaBenta;
    }

    public static void setBebeuAguaBenta(boolean bebeuAguaBenta) {
        PS.bebeuAguaBenta = bebeuAguaBenta;
    }

    public static boolean isTemEmprego() {
        return temEmprego;
    }

    public static void setTemEmprego(boolean temEmprego) {
        PS.temEmprego = temEmprego;
    }

    public static int getVida() {
        return vida;
    }

    public static void setVida(int vida) {
        PS.vida = vida;
    }

    public static boolean isEntrouNaIgreja() {
        return entrouNaIgreja;
    }

    public static void setEntrouNaIgreja(boolean entrouNaIgreja) {
        PS.entrouNaIgreja = entrouNaIgreja;
    }

    public static boolean isEntrouNaLoja() {
        return entrouNaLoja;
    }

    public static void setEntrouNaLoja(boolean entrouNaLoja) {
        PS.entrouNaLoja = entrouNaLoja;
    }

    public static boolean isProgramou() {
        return programou;
    }

    public static void setProgramou(boolean programou) {
        PS.programou = programou;
    }

    public static boolean isEntrouNosStatus() {
        return entrouNosStatus;
    }

    public static void setEntrouNosStatus(boolean entrouNosStatus) {
        PS.entrouNosStatus = entrouNosStatus;
    }

    public static void fazerAcao(){
        nAcoes++;
    }

    public static int getNumeroAcoes(){
        return nAcoes;
    }

    public static long getTempoInicio() {
        return tempoInicio;
    }

    public static void setTempoInicio(long tempoInicio) {
        PS.tempoInicio = tempoInicio;
    }

    public static String getTempoTotal(){
        long tempoFinal = System.currentTimeMillis();
        long tempoTotal = tempoFinal - PS.getTempoInicio();
        int horas = (int) tempoTotal / 3600000;
        int minutos = (int) (tempoTotal % 3600000) / 60000;
        int segundos = (int) ((tempoTotal % 3600000) % 60000) / 1000;
        //int milesimos = (int) ((tempoTotal % 3600000) % 60000) % 1000;
        return horas + ":" + minutos + ":" + segundos;
    }

    public static void die(Context c){
        boolean deuCerto = false;
        try {
            deuCerto = DBConector.enviarDerrota(c);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(deuCerto){
            Toast.makeText(c, "Progresso salvo!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(c, "Ocorreu um erro na rede, progresso perdido.", Toast.LENGTH_LONG).show();
        }

        fome = 0;
        sede = 0;
        energia = 10;
        sanidade = 10;
        fe = 0;
        dinheiros = 20;
        inteligencia = 0;
        vida = 100;
        paquerouMulherDoChefe = false;
        bebeuAguaBenta = false;
        temEmprego = false;
        entrouNaIgreja = false;
        entrouNaLoja = false;
        entrouNosStatus = false;
        programou = false;
        Diario.clearMensagens();
        MyMediaPlayer.setMusicaDeFundoPlayer(R.raw.fundo_normal, c);
        tempoInicio = System.currentTimeMillis();
    }

    public static String getNome() {
        return nome;
    }

    public static void setNome(String nome) {
        PS.nome = nome;
    }

    public static void saveGame(Context contexto){
        String state = "";

        state += "/" + fome;
        state += "/" + sede;
        state += "/" + energia;
        state += "/" + sanidade;
        state += "/" + fe;
        state += "/" + inteligencia;
        state += "/" + vida;
        state += "/" + nAcoes;
        state += "/" + dinheiros;
        state += "/" + paquerouMulherDoChefe;
        state += "/" + bebeuAguaBenta;
        state += "/" + temEmprego;
        state += "/" + entrouNaIgreja;
        state += "/" + entrouNaLoja;
        state += "/" + programou;
        state += "/" + entrouNosStatus;

        SharedPreferences sp = contexto.getSharedPreferences("mySharedPreferences", contexto.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("save", encode(state));
        editor.apply();

    }

    public static void loadFromSave(Context contexto){
        final String def = "///";
        String state =  decode(contexto.getSharedPreferences("mySharedPreferences", contexto.MODE_PRIVATE).getString("save", def));
        if(state.equals(def)){
            return;
        }
        String[] splitedState = state.split("/");

        fome = Integer.parseInt(splitedState[1]);
        sede = Integer.parseInt(splitedState[2]);
        energia = Integer.parseInt(splitedState[3]);
        sanidade = Integer.parseInt(splitedState[4]);
        fe = Integer.parseInt(splitedState[5]);
        inteligencia = Integer.parseInt(splitedState[6]);
        vida = Integer.parseInt(splitedState[7]);
        nAcoes = Integer.parseInt(splitedState[8]);
        dinheiros = Float.parseFloat(splitedState[9]);
        paquerouMulherDoChefe = Boolean.parseBoolean(splitedState[10]);
        bebeuAguaBenta = Boolean.parseBoolean(splitedState[11]);
        temEmprego = Boolean.parseBoolean(splitedState[12]);
        entrouNaIgreja = Boolean.parseBoolean(splitedState[13]);
        entrouNaLoja = Boolean.parseBoolean(splitedState[14]);
        programou = Boolean.parseBoolean(splitedState[15]);
        entrouNosStatus = Boolean.parseBoolean(splitedState[16]);
    }

    private static String encode(String txt){
        String encodedTxt = "";
        for(int i = 0; i < txt.length(); i++){
            int index = new String(alphabet).indexOf(txt.charAt(i));
            encodedTxt += encodedAlphabet[index];
        }
        return  encodedTxt;
    }

    private static String decode(String encodedTxt){
        String txt = "";
        for(int i = 0; i < encodedTxt.length(); i++){
            int index = new String(encodedAlphabet).indexOf(encodedTxt.charAt(i));
            txt += alphabet[index];
        }
        return  txt;
    }
}
