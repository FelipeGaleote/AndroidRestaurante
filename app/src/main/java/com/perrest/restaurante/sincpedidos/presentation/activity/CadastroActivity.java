package com.perrest.restaurante.sincpedidos.presentation.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.perrest.restaurante.sincpedidos.R;

public class CadastroActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
        }
    }
}
