package com.perrest.restaurante.sincpedidos.presentation.fragment;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.perrest.restaurante.sincpedidos.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AddProductDialogFragment extends DialogFragment {

    public interface OnClickListener {
        void onConfirmClick(String quantity);
    }

    @BindView(R.id.dialog_plus_button)
    protected ImageView plusButton;
    @BindView(R.id.dialog_minus_button)
    protected ImageView minusButton;
    @BindView(R.id.dialog_confirm_button)
    protected Button confirmButton;
    @BindView(R.id.dialog_counter)
    protected EditText quantityEditText;

    private OnClickListener listener;
    public AddProductDialogFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_add_product, container, false);

        ButterKnife.bind(this,view);

        listener = (OnClickListener) getArguments().getSerializable("listener");

        plusButton.setOnClickListener(v -> {
            int quantity = Integer.valueOf(quantityEditText.getText().toString());
            quantity++;
            quantityEditText.setText(String.valueOf(quantity));
        });

        minusButton.setOnClickListener(v -> {
            int quantity = Integer.valueOf(quantityEditText.getText().toString());
            quantity--;
            quantityEditText.setText(String.valueOf(quantity));
        });

        confirmButton.setOnClickListener(v -> {
            listener.onConfirmClick(quantityEditText.getText().toString());
            dismiss();
        });
        return view;
    }
}
