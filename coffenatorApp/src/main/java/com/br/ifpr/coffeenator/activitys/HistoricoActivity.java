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
import com.br.ifpr.coffeenator.myclasses.MyAlertDialogConstructor;
import com.br.ifpr.coffeenator.myclasses.Usuario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class HistoricoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_historico);
        Usuario.attInfo(this);
        TextView txtInfo = (TextView) findViewById(R.id.txtInfoHistorico);
        txtInfo.setText("Tempo total jogado: " + Usuario.getTempoJogado());
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroupHistorico);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(R.id.radioButtonMovimentosH == checkedId){
                    attRankingByMovimentos();
                } else if(R.id.radioButtonTempoH == checkedId){
                    attRankingByTempo();
                } else {
                    attRankingByOrdemCronologica();
                }
            }
        });
        attRankingByTempo();
    }

    private void attRankingByTempo(){
        try {
            JSONArray rankingJson = DBConector.getRankingPessoalByTime(this);
            ListView ranking = (ListView) findViewById(R.id.listViewHistorico);
            ArrayList<String> coisasAMostrar = new ArrayList<>();

            if(rankingJson.length() == 0){
                MyAlertDialogConstructor.showMessage("Sem vitórias", "Você não possui nenhum registro de vitória.\n(ou não está conectado à internet)", this);
            }

            for(int i = 0; i < rankingJson.length(); i++){
                JSONObject rankingO = (JSONObject) rankingJson.get(i);
                coisasAMostrar.add((i + 1) + "º " + rankingO.getString("tempo") + "  -  (" + rankingO.getString("dataVitoria") + ")");
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, coisasAMostrar);
            ranking.setAdapter(adapter);

            ((TextView) findViewById(R.id.txtTituloHistorico)).setText("Seus melhores tempos");
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void attRankingByMovimentos(){
        try {
            JSONArray rankingJson = DBConector.getRankingPessoalByMovimentos(this);
            ListView ranking = (ListView) findViewById(R.id.listViewHistorico);
            ArrayList<String> coisasAMostrar = new ArrayList<>();

            if(rankingJson.length() == 0){
                MyAlertDialogConstructor.showMessage("Sem vitórias", "Você não possui nenhum registro de vitória.\n(ou não está conectado à internet)", this);
            }

            for(int i = 0; i < rankingJson.length(); i++){
                JSONObject rankingO = (JSONObject) rankingJson.get(i);
                coisasAMostrar.add((i + 1) + "º " + rankingO.getString("movimentos") + "  -  (" + rankingO.getString("dataVitoria") + ")");
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, coisasAMostrar);
            ranking.setAdapter(adapter);
            ((TextView) findViewById(R.id.txtTituloHistorico)).setText("Suas menores quantidades de movimentos");
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void attRankingByOrdemCronologica(){
        try {
            JSONArray rankingJson = DBConector.getRankingPessoalByOrdemCronologica(this);
            ListView ranking = (ListView) findViewById(R.id.listViewHistorico);
            ArrayList<String> coisasAMostrar = new ArrayList<>();

            if(rankingJson.length() == 0){
                MyAlertDialogConstructor.showMessage("Sem vitórias", "Você não possui nenhum registro de vitória.\n(ou não está conectado à internet)", this);
            }

            for(int i = 0; i < rankingJson.length(); i++){
                JSONObject rankingO = (JSONObject) rankingJson.get(i);
                coisasAMostrar.add(rankingO.getString("dataVitoria") + " - Tempo: " + rankingO.get("tempo") + ", Movimentos: " + rankingO.get("movimentos"));
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, coisasAMostrar);
            ranking.setAdapter(adapter);

            ((TextView) findViewById(R.id.txtTituloHistorico)).setText("Vitórias mais recentes");
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onBtnVoltarHistoricoClick(View v){
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        // não chame o super desse método
    }
}
