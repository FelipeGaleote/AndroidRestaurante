package com.perrest.restaurante.sincpedidos.repository;

import com.perrest.restaurante.sincpedidos.domain.entity.User;
import com.perrest.restaurante.sincpedidos.domain.responses.BaseResponse;
import com.perrest.restaurante.sincpedidos.domain.responses.CreatedUserResponse;
import com.perrest.restaurante.sincpedidos.domain.responses.UserLoggedResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {

    public interface UserAuthListener {
        void onUserAuthenticated(String userId);
        void onUserRegistered(String userId);
        void onFailed(String errorMessage);
    }

    private UserAuthListener listener;
    private UserService userService;

    public UserRepository(UserAuthListener listener) {
        this.listener = listener;
        userService = RetrofitInstance.getRetrofitInstance().create(UserService.class);
    }

    public void login(User user) {
        Call<UserLoggedResponse> call = userService.login(user);
        call.enqueue(new Callback<UserLoggedResponse>() {
            @Override
            public void onResponse(Call<UserLoggedResponse> call, Response<UserLoggedResponse> response) {
                if(response.isSuccessful()){
                    if(response.body().getStatusCode() == 202) {
                        listener.onUserAuthenticated(response.body().getUserId());
                    } else {
                        listener.onFailed(response.body().getStatusMessage());
                    }
                } else {
                    listener.onFailed("Falha ao fazer login");
                }
            }

            @Override
            public void onFailure(Call<UserLoggedResponse> call, Throwable t) {
                listener.onFailed("Falha ao fazer login");
            }
        });
    }

    public void createAccount(User user) {
        Call<CreatedUserResponse> call = userService.createUser(user);
        call.enqueue(new Callback<CreatedUserResponse>() {
            @Override
            public void onResponse(Call<CreatedUserResponse> call, Response<CreatedUserResponse> response) {
                if(response.isSuccessful()){
                    if(response.body().getStatusCode() == 201) {
                        listener.onUserRegistered(response.body().getId());
                    } else {
                        listener.onFailed(response.body().getStatusMessage());
                    }
                } else {
                    listener.onFailed("Falha ao fazer cadastro");
                }
            }

            @Override
            public void onFailure(Call<CreatedUserResponse> call, Throwable t) {
                listener.onFailed("Falha ao fazer cadastro");
            }
        });
    }
}
