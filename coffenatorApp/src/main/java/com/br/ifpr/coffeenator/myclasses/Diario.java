package com.br.ifpr.coffeenator.myclasses;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.br.ifpr.coffeenator.activitys.CasaActivity;
import com.br.ifpr.coffeenator.R;

import java.util.ArrayList;

public class Diario {
    private static ArrayList<Integer> mensagensPendentes;

    private Diario(){}

    static {
        mensagensPendentes = new ArrayList<Integer>();
    }

    public static void addMensagem(int mensagemId){
        mensagensPendentes.add(mensagemId);
    }

    public static void showMessages(Context c) {
        String mensagem = "";
        for (int i = 0; i < mensagensPendentes.size(); i++) {
            String aux = c.getString(mensagensPendentes.get(i));
            if (i > 0) {
                aux = aux.substring(0, 1).toUpperCase().concat(aux.substring(1));
            }
            mensagem += aux + "\n";
        }

        if (mensagem.equals("")) {
            if (PS.getSanidade() < 2) {
                mensagem = c.getString(R.string.diarioInsano);
            } else {
                mensagem = c.getString(R.string.diarioPadrao);
            }
        } else {
            mensagem = "    Hoje eu " + mensagem;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setCancelable(false);
        builder.setTitle("Diário");
        builder.setMessage(mensagem);

        final Context context = c;

        DialogInterface.OnClickListener btnOk = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyMediaPlayer.playButtonSound(context);
                CasaActivity.dormir(context);
            }
        };

        builder.setPositiveButton("Ok", btnOk);
        builder.create().show();
        clearMensagens();
    }

    public static void clearMensagens() {
        mensagensPendentes.clear();
    }

}
