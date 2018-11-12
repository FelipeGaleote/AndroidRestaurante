package com.perrest.restaurante.sincpedidos.presentation.presenter;

import android.app.Activity;
import android.widget.Toast;

import com.perrest.restaurante.sincpedidos.R;
import com.perrest.restaurante.sincpedidos.domain.entity.User;
import com.perrest.restaurante.sincpedidos.presentation.view_interface.SignUpView;
import com.perrest.restaurante.sincpedidos.repository.UserRepository;
import com.perrest.restaurante.sincpedidos.util.SharedPrefsUtil;

public class SignupPresenter implements UserRepository.UserAuthListener{

    private SignUpView view;
    private Activity activity;
    private String nameError;
    private String emailError;
    private String passwordError;
    private UserRepository userRepository;

    public SignupPresenter(SignUpView signUpView) {
        this.view = signUpView;
        this.activity = signUpView.getActivityFromView();
        userRepository = new UserRepository(this);
    }

    public void validateAllFields(String name, String email, String password) {
        validateName(name);
        validateEmail(email);
        validatePassword(password);
        User user = new User();
        user.setNomeCompleto(name);
        user.setNomeUsuario(email);
        user.setSenha(password);
        if (emailError.isEmpty() && passwordError.isEmpty())
            proceed(user);
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

    private void proceed(User user){
        userRepository.createAccount(user);
    }

    @Override
    public void onUserAuthenticated(String userId) {

    }

    @Override
    public void onUserRegistered(String userId) {
        SharedPrefsUtil.saveToken(activity,userId);
        Toast.makeText(activity,"Registrado com sucesso",Toast.LENGTH_SHORT).show();
        view.goToChooseTableActivity();
    }

    @Override
    public void onFailed(String errorMessage) {
        Toast.makeText(activity,errorMessage,Toast.LENGTH_SHORT).show();
    }
}
