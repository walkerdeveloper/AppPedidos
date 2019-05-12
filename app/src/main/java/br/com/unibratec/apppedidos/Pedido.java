package br.com.unibratec.apppedidos;

import java.io.Serializable;

public class Pedido implements Serializable {

    private int id;
    private String titulo;
    private String descricao;

    public Pedido(int id, String titulo, String descricao){
        this.setId(id);
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
