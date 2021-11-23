package com.biblioteca.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.inject.Inject;
import javax.json.bind.annotation.JsonbDateFormat;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class Emprestimo extends PanacheEntity {

    private int cliente;

    private int livro;

    private String dataDevolucao;


    public int getCliente() {
        return cliente;
    }

    public void setCliente(int cliente) {
        this.cliente = cliente;
    }

    public int getLivro() {
        return livro;
    }

    public void setLivro(int livro) {
        this.livro = livro;
    }

    public String getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(String dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }
}
