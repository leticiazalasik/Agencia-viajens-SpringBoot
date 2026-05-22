package com.example.agenciaviagens.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class Detalhes {

    private String descricao;

    private String hotel;

    private String atividadeTuristica;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public String getAtividadeTuristica() {
        return atividadeTuristica;
    }

    public void setAtividadeTuristica(String atividadeTuristica) {
        this.atividadeTuristica = atividadeTuristica;
    }
}