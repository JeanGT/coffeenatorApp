package com.br.ifpr.coffeenator.myclasses;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import com.br.ifpr.coffeenator.activitys.MenuActivity;
import com.br.ifpr.coffeenator.R;

public class MyAlertDialogConstructor {

    private MyAlertDialogConstructor() {}

    public  static void showMessage(String titulo, String mensagem, final Context contexto){
        showMessage(titulo, mensagem, contexto, false);
    }

    public static void showDeathMessage(String mensagem, Context contexto){
        showMessage(contexto.getString(R.string.tituloDasMortes), mensagem, contexto, true);
    }

    private static void showMessage(String titulo, String mensagem, final Context contexto, final boolean mensagemDeMorte){
        AlertDialog.Builder builder = new AlertDialog.Builder(contexto);
        if(mensagemDeMorte) {
            builder.setIcon(R.drawable.death_icon);
            PS.die(contexto);
        }
        builder.setCancelable(false);
        builder.setTitle(titulo);
        builder.setMessage(mensagem);

        DialogInterface.OnClickListener btnOk = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(mensagemDeMorte){
                    MyMediaPlayer.setMusicaDeFundoPlayer(R.raw.menu, contexto);
                    Intent intent = new Intent(contexto, MenuActivity.class);
                    contexto.startActivity(intent);
                }
                return;
            }
        };

        builder.setPositiveButton("Ok",  btnOk);
        builder.create().show();
    }
}
