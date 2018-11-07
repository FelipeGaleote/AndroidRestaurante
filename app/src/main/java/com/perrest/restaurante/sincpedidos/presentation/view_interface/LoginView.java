package com.perrest.restaurante.sincpedidos.presentation.view_interface;

public interface LoginView extends BaseView {
    void goToMainActivity();

    void goToChooseTableActivity();

    void showEmailError(String error);

    void showPasswordError(String error);
}
