package com.perrest.restaurante.sincpedidos.domain.entity;

import java.io.Serializable;


public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;
    private long idMesa;
    private String idUsuario;
    private long dataDeInicio;
    private long dataDeFinalizacao;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(long idMesa) {
        this.idMesa = idMesa;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public long getDataDeInicio() {
        return dataDeInicio;
    }

    public void setDataDeInicio(long dataDeInicio) {
        this.dataDeInicio = dataDeInicio;
    }

    public long getDataDeFinalizacao() {
        return dataDeFinalizacao;
    }

    public void setDataDeFinalizacao(long dataDeFinalizacao) {
        this.dataDeFinalizacao = dataDeFinalizacao;
    }
}
