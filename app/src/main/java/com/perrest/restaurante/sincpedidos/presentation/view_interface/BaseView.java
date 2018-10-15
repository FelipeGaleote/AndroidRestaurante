package com.perrest.restaurante.sincpedidos.presentation.view_interface;

import android.app.Activity;
import android.view.View;

public interface BaseView{
    Activity getActivityFromView();
    void showToast(String message);
}
