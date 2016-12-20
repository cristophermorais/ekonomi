package net.ddns.esof.ekonomi.rest.model;

/**
 * Created by Alexandre on 19/12/2016.
 */

public class PrecoSuperm{
    private double preco;
    private Supermercado supermercado;

    public PrecoSuperm(Supermercado supermercado, Double preco){
        this.preco = preco;
        this.supermercado = supermercado;
    }

    public double getPreco(){
        return preco;
    }

    public Supermercado getSupermercado(){
        return supermercado;
    }
}