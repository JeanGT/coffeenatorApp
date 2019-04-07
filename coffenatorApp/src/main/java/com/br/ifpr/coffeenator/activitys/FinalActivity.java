package com.br.ifpr.coffeenator.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.br.ifpr.coffeenator.R;
import com.br.ifpr.coffeenator.myclasses.DBConector;
import com.br.ifpr.coffeenator.myclasses.PS;

import java.io.IOException;

public class FinalActivity extends AppCompatActivity {
    private boolean timerFinished = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_final);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        final float volumeMusica = bundle.getFloat("volume musica");

        TextView txt = (TextView) findViewById(R.id.txtFinal);
        txt.setText(R.string.oFinal);
        txt.setText(txt.getText() + "Número de açoes: " + PS.getNumeroAcoes() + "\nTempo: " + PS.getTempoTotal());
        timerHandler.postDelayed(timerRunnable, 2000);
        final Context context = this;
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(timerFinished) {
                    Intent intent = new Intent(context, CreditosActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putFloat("volume musica", volumeMusica);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });
        boolean deuCerto = false;

        try {
            deuCerto = DBConector.enviarVitoria(this);
        } catch (IOException e) {
            Log.d("meuDebug", "Erro final");
            e.printStackTrace();
        }

        if(deuCerto){
            Toast.makeText(this, "Vitória salva com sucesso!", Toast.LENGTH_LONG).show();
            PS.die(this);
        } else {
            Toast.makeText(this, "Erro na rede.", Toast.LENGTH_LONG).show();
        }
    }

    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {

        @Override
        public void run() {
            timerFinished = true;
        }
    };

    @Override
    public void onPause(){
        timerHandler.removeCallbacks(timerRunnable);
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        // não chame o super desse método
    }

}
