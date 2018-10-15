package com.perrest.restaurante.sincpedidos.presentation.activity;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.perrest.restaurante.sincpedidos.R;
import com.perrest.restaurante.sincpedidos.presentation.presenter.LoginPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity{

    @BindView(R.id.login_cadastrar_btn)
    Button createAccount;
    @BindView(R.id.login_logar_btn)
    Button login;
    @BindView(R.id.login_email_input_layout)
    TextInputLayout emailField;
    @BindView(R.id.login_senha_input_layout)
    TextInputLayout passwordField;
    @BindView(R.id.login_show_password_button)
    ImageView showPasswordButton;

    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        presenter = new LoginPresenter(this);

        configFields();
    }

    private void configFields(){
        emailField.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                presenter.validateEmail(s.toString().trim());
            }
        });

        passwordField.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                presenter.validatePassword(s.toString().trim());
            }
        });
    }

    @OnClick(R.id.login_cadastrar_btn)
    protected void goToCreateAccountActivity(){
        Intent intent = new Intent(LoginActivity.this,CadastroActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.login_logar_btn)
    protected void validateFields(){
        String email = emailField.getEditText() != null ? emailField.getEditText().getText().toString() : "";
        String password = passwordField.getEditText() != null ? passwordField.getEditText().getText().toString() : "";
        presenter.validateAllFields(email,password);
    }

    public void showEmailError(String error){
        if(error.isEmpty()) {
            emailField.setErrorEnabled(false);
        } else {
            emailField.setError(error);
        }
    }

    public void showPasswordError(String error){
        if(error.isEmpty()){
            passwordField.setErrorEnabled(false);
        } else {
            passwordField.setError(error);
        }
    }

    public void goToMainActivity(){
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.login_show_password_button)
    protected void changePasswordVisibility(){
        String previusError = passwordField.getError() != null ? passwordField.getError().toString() : "";
        if(passwordField.getEditText().getInputType() == (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD)) {
            passwordField.getEditText().setInputType(InputType.TYPE_CLASS_TEXT);
        } else {
            passwordField.getEditText().setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
        passwordField.getEditText().setSelection(passwordField.getEditText().getText().length());
        showPasswordError(previusError);
    }
}
