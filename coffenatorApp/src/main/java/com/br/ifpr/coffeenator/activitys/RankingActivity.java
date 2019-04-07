package com.br.ifpr.coffeenator.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.br.ifpr.coffeenator.R;
import com.br.ifpr.coffeenator.myclasses.DBConector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class RankingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_ranking);
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroupHistorico);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(R.id.radioButtonMovimentos == checkedId){
                    attRankingByMovimentos();
                } else {
                    attRankingByTempo();
                }
            }
        });
        attRankingByTempo();
    }

    private void attRankingByTempo(){
        try {
            JSONArray rankingJson = DBConector.getRankingByTime(this);
            ListView ranking = (ListView) findViewById(R.id.listViewRanking);
            ArrayList<String> coisasAMostrar = new ArrayList<>();

            for(int i = 0; i < rankingJson.length(); i++){
                JSONObject rankingO = (JSONObject) rankingJson.get(i);
                coisasAMostrar.add((i + 1) + "º " + rankingO.getString("nome") + " - " + rankingO.getString("tempo"));
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, coisasAMostrar);
            ranking.setAdapter(adapter);

            ((TextView) findViewById(R.id.txtTituloRanking)).setText("Melhores tempos");
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void attRankingByMovimentos(){
        try {
            JSONArray rankingJson = DBConector.getRankingByMovimentos(this);
            ListView ranking = (ListView) findViewById(R.id.listViewRanking);
            ArrayList<String> coisasAMostrar = new ArrayList<>();

            for(int i = 0; i < rankingJson.length(); i++){
                JSONObject rankingO = (JSONObject) rankingJson.get(i);
                coisasAMostrar.add((i + 1) + "º " + rankingO.getString("nome") + " - " + rankingO.getString("movimentos"));
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, coisasAMostrar);
            ranking.setAdapter(adapter);
            ((TextView) findViewById(R.id.txtTituloRanking)).setText("Menores quantidades de movimentos");
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onBtnVoltarRankingClick(View v){
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        // não chame o super desse método
    }
}
