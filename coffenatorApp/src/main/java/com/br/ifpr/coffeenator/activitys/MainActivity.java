package com.br.ifpr.coffeenator.activitys;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.br.ifpr.coffeenator.R;
import com.br.ifpr.coffeenator.myclasses.DBConector;
import com.br.ifpr.coffeenator.myclasses.Gif;
import com.br.ifpr.coffeenator.myclasses.MyMediaPlayer;
import com.br.ifpr.coffeenator.myclasses.PS;
import com.br.ifpr.coffeenator.myclasses.Usuario;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Gif gif;
    ImageView loadingImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        SharedPreferences sp = getSharedPreferences("mySharedPreferences", this.MODE_PRIVATE);
        float volumeMusica = sp.getFloat("volumeMusica", 0.2f);
        float volumeSFX = sp.getFloat("volumeSFX", 1);
        boolean mutado = sp.getBoolean("mutado", false);
        MyMediaPlayer.changeMusicVolume(volumeMusica);
        MyMediaPlayer.changeSFXVolume(volumeSFX);
        if(mutado){
            MyMediaPlayer.mute();
        }
        loadingImage = (ImageView) findViewById(R.id.imgLoading);
        //Mostra o gif de carregamento
        Button btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                loadingImage.setVisibility(View.VISIBLE);
                gif = new Gif(100, loadingImage, R.drawable.fumaca03, R.drawable.fumaca02, R.drawable.fumaca01);
                gif.play();
                return false;
            }
        });

//        Intent intent = new Intent(this, MenuActivity.class);
//        startActivity(intent);
    }

    public void onBtnLoginClick(View v) throws InterruptedException {



        TextView txtCarregando = (TextView) findViewById(R.id.txtCarregando);
        String email = ((EditText) findViewById(R.id.editTextEmailL)).getText().toString();
        String senha = ((EditText) findViewById(R.id.editTextSenhaL)).getText().toString();

        JSONObject usuarioJson = null;
        try {
            usuarioJson = DBConector.login(email, senha, this);
        } catch (IOException e) {
            Log.d("meuDebug", "Erro login");
            e.printStackTrace();
        } catch (JSONException e) {
            Log.d("meuDebug", "Erro login2");
            e.printStackTrace();
        }
        if(usuarioJson != null) {
            Usuario.setEmail(email);
            Usuario.setSenha(senha);
            try {
                Usuario.setNome(usuarioJson.getString("nome"));
                Usuario.setTempoJogado(usuarioJson.getString("tempo_jogado"));

                Usuario.setnMortes(usuarioJson.getInt("mortes"));
            } catch (JSONException e) {
                Log.d("meuDebug", "Erro login 3");
                e.printStackTrace();
            }
            gif.stop();
            loadingImage.setVisibility(View.INVISIBLE);
            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);
        } else {
            loadingImage.setVisibility(View.INVISIBLE);
            gif.stop();
            Toast.makeText(this, "Email ou senha inválidos.", Toast.LENGTH_LONG).show();
        }
        //findViewById(R.id.loadingPanel).setVisibility(View.GONE);
    }

    public void onTxtCadastrarClick(View v){
        Intent intent = new Intent(this, CadastroActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        // não chame o super desse método
    }
}
