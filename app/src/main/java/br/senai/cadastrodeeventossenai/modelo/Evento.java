package br.senai.cadastrodeeventossenai.modelo;

import java.time.LocalDate;

public class Evento {
    private String nome;
    private LocalDate data;
    private String local;


    public Evento(String nome, LocalDate data, String local) {
        this.nome = nome;
        this.data = data;
        this.local = local;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(LocalDate date) {
        this.data = data;
    }

    @Override
    public String toString() {
        return nome + "\n" +
                "Data: " + data +
                " / Local : " + local;
    }
}

