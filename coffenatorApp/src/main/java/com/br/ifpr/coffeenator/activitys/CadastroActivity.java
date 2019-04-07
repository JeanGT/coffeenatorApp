package com.br.ifpr.coffeenator.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.br.ifpr.coffeenator.R;
import com.br.ifpr.coffeenator.myclasses.DBConector;

import java.io.IOException;

public class CadastroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_cadastro);
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

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
