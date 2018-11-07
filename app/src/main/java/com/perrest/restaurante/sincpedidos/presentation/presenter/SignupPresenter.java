package com.perrest.restaurante.sincpedidos.presentation.presenter;

import android.app.Activity;

import com.perrest.restaurante.sincpedidos.R;
import com.perrest.restaurante.sincpedidos.presentation.view_interface.SignUpView;

public class SignupPresenter {

    private SignUpView view;
    private Activity activity;
    private String nameError;
    private String emailError;
    private String passwordError;

    public SignupPresenter(SignUpView signUpView) {
        this.view = signUpView;
        this.activity = signUpView.getActivityFromView();
    }

    public void validateAllFields(String name, String email, String password) {
        validateName(name);
        validateEmail(email);
        validatePassword(password);
        if (emailError.isEmpty() && passwordError.isEmpty())
            proceed();
    }

    public void validateName(String name) {
        nameError = "";
        if (name.isEmpty()) {
            nameError = activity.getString(R.string.requerid_field);
        }
        view.showNameError(nameError);
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
