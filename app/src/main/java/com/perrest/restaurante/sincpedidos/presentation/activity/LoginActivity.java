package com.perrest.restaurante.sincpedidos.presentation.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.perrest.restaurante.sincpedidos.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Button criarConta;
    Button logar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        criarConta = findViewById(R.id.login_cadastrar_btn);
        logar = findViewById(R.id.login_logar_btn);
        criarConta.setOnClickListener(this);
        logar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_cadastrar_btn:
                irParaTelaDeCadastro();
                break;
            case R.id.login_logar_btn:
                irParaTelaPrincipal();
                break;
        }
    }

    private void irParaTelaDeCadastro(){
        Intent intent = new Intent(LoginActivity.this,CadastroActivity.class);
        startActivity(intent);
    }

    private void irParaTelaPrincipal(){
        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
        startActivity(intent);
    }
}
