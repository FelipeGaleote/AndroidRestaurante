package com.perrest.restaurante.sincpedidos.presentation.presenter;


import android.app.Activity;
import android.widget.Toast;

import com.perrest.restaurante.sincpedidos.R;
import com.perrest.restaurante.sincpedidos.domain.entity.User;
import com.perrest.restaurante.sincpedidos.presentation.view_interface.LoginView;
import com.perrest.restaurante.sincpedidos.repository.UserRepository;
import com.perrest.restaurante.sincpedidos.util.SharedPrefsUtil;

public class LoginPresenter implements UserRepository.UserAuthListener{
    private LoginView view;
    private Activity activity;
    private String emailError;
    private String passwordError;
    private UserRepository userRepository;
    private boolean keepLoggedIn;

    public LoginPresenter(LoginView view) {
        this.view = view;
        this.activity = view.getActivityFromView();
        userRepository = new UserRepository(this);
    }

    public void validateAllFields(String email, String password, boolean keepLoggedIn) {
        this.keepLoggedIn = keepLoggedIn;
        validateEmail(email);
        validatePassword(password);
        if (emailError.isEmpty() && passwordError.isEmpty()){
            User user = new User();
            user.setNomeUsuario(email);
            user.setSenha(password);
            if(keepLoggedIn){
                SharedPrefsUtil.saveLoginInfo(activity.getBaseContext(),user);
            }
            userRepository.login(user);
        }
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
        if(SharedPrefsUtil.getSelectedTable(activity.getBaseContext()) == -1)
            view.goToChooseTableActivity();
        else
            view.goToMainActivity();
    }

    @Override
    public void onUserAuthenticated(String userId) {
        SharedPrefsUtil.saveToken(activity, userId);
        proceed();
    }

    @Override
    public void onUserRegistered(String userId) {

    }

    @Override
    public void onFailed(String errorMessage) {
        Toast.makeText(activity,errorMessage,Toast.LENGTH_SHORT).show();
    }
}
