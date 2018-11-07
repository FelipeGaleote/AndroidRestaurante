package com.perrest.restaurante.sincpedidos.presentation.presenter;


import android.app.Activity;

import com.perrest.restaurante.sincpedidos.R;
import com.perrest.restaurante.sincpedidos.presentation.view_interface.LoginView;

public class LoginPresenter {
    private LoginView view;
    private Activity activity;
    private String emailError;
    private String passwordError;

    public LoginPresenter(LoginView view) {
        this.view = view;
        this.activity = view.getActivityFromView();
    }

    public void validateAllFields(String email, String password) {
        validateEmail(email);
        validatePassword(password);
        if (emailError.isEmpty() && passwordError.isEmpty())
            proceed();
    }

    public void validateEmail(String email) {
        emailError = "";
        if (email.isEmpty()) {
            emailError = activity.getString(R.string.requerid_field);
        } else if (!email.contains("@") || !email.contains(".")) {
            emailError = activity.getString(R.string.invalid_email);
        }
        view.showEmailError(emailError);
    }

    public void validatePassword(String password) {
        passwordError = "";
        if (password.isEmpty()) {
            passwordError = activity.getString(R.string.requerid_field);
        }
        view.showPasswordError(passwordError);
    }

    private void proceed(){
        view.goToChooseTableActivity();
    }
}
