package br.senai.cadastrodeeventossenai.modelo;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Evento implements Serializable {

    private int id;
    private String nome;
    private String data;
    private String local;


    public Evento(int id, String nome, String data, String local) {
        this.id = id;
        this.nome = nome;
        this.data = data;
        this.local = local;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        String formatter = String.format("dd/MM/yyyy");
        this.data = data.format(formatter);
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(LocalDate date) {
        this.data = data;
    }

    @NonNull
    @Override
    public String toString() {
        return nome + "\n" +
                "Data: " + data +
                " / Local : " + local;
    }
}

