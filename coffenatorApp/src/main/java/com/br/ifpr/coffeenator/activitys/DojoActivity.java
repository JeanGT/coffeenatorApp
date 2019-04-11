package com.br.ifpr.coffeenator.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.br.ifpr.coffeenator.R;

public class DojoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dojo);
    }

    public void onBtnLuta1Click(View v){
        Intent intent = new Intent(this, BatalhaBossActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("boss nome", "Lutador 1");
        bundle.putInt("boss vida", 1000);
        bundle.putInt("boss dano", 6);
        bundle.putInt("velocidade minima", 1500);
        bundle.putInt("velocidade maxima", 3000);
        bundle.putInt("tempo entre flechas", 200);
        bundle.putInt("quantidade de flechas", 20);
        bundle.putBoolean("caio", false);
        bundle.putInt("biblia", R.drawable.biblia);
        bundle.putInt("bg esquivar", R.drawable.bar_interior_insano);

        bundle.putFloat("acrescimo na dificuldade", 0.05f);

        bundle.putInt("background id", R.drawable.bar_interior_insano);
        bundle.putInt("projetil id", R.drawable.garrafa);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        // não chame o super desse método
    }
}
