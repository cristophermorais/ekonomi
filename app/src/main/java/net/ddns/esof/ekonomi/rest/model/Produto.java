package net.ddns.esof.ekonomi.rest.model;

import java.io.Serializable;

public class Produto implements Serializable {
    private int id;

    private String nome;

    private UnMedida medida;

    public Produto(String nome, UnMedida medida) {
        this.nome = nome;
        this.medida = medida;
    }

    public Produto() {
        super();
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

    public UnMedida getMedida() {
        return medida;
    }

    public void setMedida(UnMedida medida) {
        this.medida = medida;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Produto other = (Produto) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return nome;

    }
}
