package com.br.ifpr.coffeenator.myclasses;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class Usuario {
    private static String nome;
    private static String email;
    private static String senha;
    private static String tempoJogado;
    private static int nMortes;

    private Usuario(){}

    public static String getNome() {
        return nome;
    }

    public static void setNome(String nome) {
        Usuario.nome = nome;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        Usuario.email = email;
    }

    public static String getSenha() {
        return senha;
    }

    public static void setSenha(String senha) {
        Usuario.senha = senha;
    }

    public static String getTempoJogado() {
        return tempoJogado;
    }

    public static void setTempoJogado(String tempoJogado) {
//        String[] tempoJogadoSplit = tempoJogadoString.split(":");
//        tempoJogado = 0;
//        tempoJogado += Integer.parseInt(tempoJogadoSplit[0]) * 3600;
//        tempoJogado += Integer.parseInt(tempoJogadoSplit[1]) * 60;
//        tempoJogado += Integer.parseInt(tempoJogadoSplit[2]);
        Usuario.tempoJogado = tempoJogado;
    }

    public static int getnMortes() {
        return nMortes;
    }

    public static void setnMortes(int nMortes) {
        Usuario.nMortes = nMortes;
    }

    public static void attInfo(Context context){
        try {
            JSONObject usuarioJson = DBConector.login(email, senha, context);
            Usuario.setTempoJogado(usuarioJson.getString("tempo_jogado"));
            Usuario.setnMortes(usuarioJson.getInt("mortes"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void clear(){
        email = "";
        senha = "";
        nome = "";
        tempoJogado = "";
        nMortes = 0;
    }
}
