package com.perrest.restaurante.sincpedidos.presentation.view_interface;

public interface SignUpView extends BaseView {
    void goToMainActivity();

    void showNameError(String error);

    void showEmailError(String error);

    void showPasswordError(String error);
}
