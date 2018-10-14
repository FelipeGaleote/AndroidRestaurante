package com.perrest.restaurante.sincpedidos.presentation.presenter;


import com.perrest.restaurante.sincpedidos.R;
import com.perrest.restaurante.sincpedidos.presentation.activity.LoginActivity;

public class LoginPresenter {
    private LoginActivity activity;
    private String emailError;
    private String passwordError;

    public LoginPresenter(LoginActivity activity){
        this.activity = activity;
    }

    public void validateAllFields(String email, String password){
        validateEmail(email);
        validatePassword(password);
        if(emailError.isEmpty() && passwordError.isEmpty())
            activity.goToMainActivity();
    }

    public void validateEmail(String email){
        emailError = "";
        if (email.isEmpty()) {
            emailError = activity.getString(R.string.campo_obrigatorio);
        } else if(!email.contains("@") || !email.contains(".")) {
            emailError = activity.getString(R.string.invalid_email);
        }
        activity.showEmailError(emailError);
    }

    public void validatePassword(String password){
        passwordError = "";
        if (password.isEmpty()) {
            passwordError = activity.getString(R.string.campo_obrigatorio);
        }
        activity.showPasswordError(passwordError);
    }
}
