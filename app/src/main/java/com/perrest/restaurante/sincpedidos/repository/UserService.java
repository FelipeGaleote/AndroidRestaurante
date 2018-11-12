package com.perrest.restaurante.sincpedidos.repository;

import com.perrest.restaurante.sincpedidos.domain.entity.User;
import com.perrest.restaurante.sincpedidos.domain.responses.BaseResponse;
import com.perrest.restaurante.sincpedidos.domain.responses.CreatedUserResponse;
import com.perrest.restaurante.sincpedidos.domain.responses.UserLoggedResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {

    @POST("/usuarios")
    Call<CreatedUserResponse> createUser(@Body User user);

    @POST("/usuarios/token")
    Call<UserLoggedResponse> login(@Body User user);
}
