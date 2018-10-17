package com.perrest.restaurante.sincpedidos.presentation.fragment;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.perrest.restaurante.sincpedidos.R;
import com.perrest.restaurante.sincpedidos.presentation.activity.MainActivity;
import com.perrest.restaurante.sincpedidos.presentation.presenter.SignupPresenter;
import com.perrest.restaurante.sincpedidos.presentation.view_interface.SignUpView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment implements SignUpView {

    @BindView(R.id.signup_signup_button)
    Button signup;
    @BindView(R.id.signup_name_input_layout)
    TextInputLayout nameField;
    @BindView(R.id.signup_email_input_layout)
    TextInputLayout emailField;
    @BindView(R.id.signup_password_input_layout)
    TextInputLayout passwordField;
    @BindView(R.id.signup_show_password_button)
    ImageView showPasswordButton;

    private SignupPresenter presenter;

    public SignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        ButterKnife.bind(this, view);
        configFields();
        presenter = new SignupPresenter(this);
        return view;
    }

    private void configFields() {
        nameField.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                presenter.validateName(s.toString().trim());
            }
        });

        emailField.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                presenter.validateEmail(s.toString().trim());
            }
        });

        passwordField.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                presenter.validatePassword(s.toString().trim());
            }
        });
    }

    @OnClick(R.id.signup_signup_button)
    protected void validateFields() {
        String name = nameField.getEditText() != null ? nameField.getEditText().getText().toString() : "";
        String email = emailField.getEditText() != null ? emailField.getEditText().getText().toString() : "";
        String password = passwordField.getEditText() != null ? passwordField.getEditText().getText().toString() : "";
        presenter.validateAllFields(name, email, password);
    }

    @Override
    public void showNameError(String error) {
        if (error.isEmpty()) {
            nameField.setError(null);
        } else {
            nameField.setError(error);
        }
    }

    @Override
    public void showEmailError(String error) {
        if (error.isEmpty()) {
            emailField.setError(null);
        } else {
            emailField.setError(error);
        }
    }

    @Override
    public void showPasswordError(String error) {
        if (error.isEmpty()) {
            passwordField.setError(null);
        } else {
            passwordField.setError(error);
        }
    }

    @Override
    public void goToMainActivity() {
        Intent intent = new Intent(getContext(), MainActivity.class);
        startActivity(intent);
        if (getActivity() != null)
            getActivity().finish();
    }

    @OnClick(R.id.signup_show_password_button)
    protected void changePasswordVisibility() {
        String previusError = passwordField.getError() != null ? passwordField.getError().toString() : "";
        if (passwordField.getEditText().getInputType() == (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD)) {
            passwordField.getEditText().setInputType(InputType.TYPE_CLASS_TEXT);
        } else {
            passwordField.getEditText().setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
        passwordField.getEditText().setSelection(passwordField.getEditText().getText().length());
        showPasswordError(previusError);
    }

    @Override
    public Activity getActivityFromView() {
        return getActivity();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

}
