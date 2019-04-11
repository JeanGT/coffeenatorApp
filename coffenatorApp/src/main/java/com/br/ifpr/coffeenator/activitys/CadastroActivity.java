package com.br.ifpr.coffeenator.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.br.ifpr.coffeenator.R;
import com.br.ifpr.coffeenator.myclasses.DBConector;
import com.br.ifpr.coffeenator.myclasses.Gif;

import java.io.IOException;

public class CadastroActivity extends AppCompatActivity {
    Gif gif;
    ImageView loadingImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        loadingImage = (ImageView) findViewById(R.id.imgLoadingCadastro);

        //Mostra o gif de carregamento
        Button btnLogin = findViewById(R.id.btnCadastrar);
        btnLogin.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                loadingImage.setVisibility(View.VISIBLE);
                gif = new Gif(100, loadingImage, R.drawable.fumaca03, R.drawable.fumaca02, R.drawable.fumaca01);
                gif.play();
                return false;
            }
        });
    }

    public void onBtnCadastrarClick(View v){
        Log.d("meuDebug", "teste");
        String nome = ((EditText) findViewById(R.id.editTextNomeR)).getText().toString();
        String email = ((EditText) findViewById(R.id.editTextEmailR)).getText().toString();
        String senha = ((EditText) findViewById(R.id.editTextSenhaR)).getText().toString();
        String confirmacaoSenha = ((EditText) findViewById(R.id.editTextConfirmarSenhaR)).getText().toString();
        if(nome.length() > 3 && nome.length() < 21){
            if(email.length() > 6 && email.length() < 100 && email.contains("@") && email.contains(".")){
                if(senha.length() > 3 && senha.length() < 46){
                    if(senha.equals(confirmacaoSenha)){
                        boolean deuCerto = false;
                        try {
                            deuCerto = DBConector.cadastrarUsuario(this, nome, senha, email);
                        } catch (IOException e) {
                            Log.d("meuDebug", "Erro no cadastro.");
                            e.printStackTrace();
                        }
                        if(deuCerto) {
                            gif.stop();
                            loadingImage.setVisibility(View.INVISIBLE);
                            Intent intent = new Intent(this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(this, "Ocorreu um erro no cadastro.", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(this, "As senhas não coincidem.", Toast.LENGTH_LONG).show();;
                    }
                } else {
                    Toast.makeText(this, "A senha deve possuir de 4 a 45 caracteres.", Toast.LENGTH_LONG).show();;
                }
            } else {
                Toast.makeText(this, "Email inválido.", Toast.LENGTH_LONG).show();;
            }
        } else {
            Toast.makeText(this, "O nome deve possuir de 4 a 20 caracteres.", Toast.LENGTH_LONG).show();;
        }
        loadingImage.setVisibility(View.INVISIBLE);
        gif.stop();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
