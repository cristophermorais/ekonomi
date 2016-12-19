package net.ddns.esof.ekonomi.rest.classes;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PrecoProd {

    private Marca marca;

    private Produto produto;

    private Supermercado superm;

    private float qtUnPad;

    private float precoProd;

    private Double dataInc;

    private Usuario user;

    public PrecoProd(Produto produto, Marca marca, Supermercado superm, float qtUn, float preco, Double dataInic, Usuario user) {
        this.marca = marca;
        this.produto = produto;
        this.superm = superm;
        this.qtUnPad = qtUn;
        this.precoProd = preco;
        this.dataInc = dataInic;
        this.user = user;
    }


    public PrecoProd() {
        super();
    }


    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Supermercado getSuperm() {
        return superm;
    }

    public void setSuperm(Supermercado superm) {
        this.superm = superm;
    }

    public Float getQtUnPad() {
        return qtUnPad;
    }

    public void setQtUnPad(Float qtUnPad) {
        this.qtUnPad = qtUnPad;
    }

    public float getPrecoProd() {
        return precoProd;
    }

    public void setPrecoProd(float precoProd) {
        this.precoProd = precoProd;
    }

    public Double getDataInc() {
        return dataInc;
    }

    public void setDataInc(Double dataInc) {
        this.dataInc = dataInc;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((marca == null) ? 0 : marca.hashCode());
        result = prime * result + ((produto == null) ? 0 : produto.hashCode());
        result = prime * result + Float.floatToIntBits(qtUnPad);
        result = prime * result + ((superm == null) ? 0 : superm.hashCode());
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
        PrecoProd other = (PrecoProd) obj;
        if (marca == null) {
            if (other.marca != null)
                return false;
        } else if (!marca.equals(other.marca))
            return false;
        if (produto == null) {
            if (other.produto != null)
                return false;
        } else if (!produto.equals(other.produto))
            return false;
        if (Float.floatToIntBits(qtUnPad) != Float.floatToIntBits(other.qtUnPad))
            return false;
        if (superm == null) {
            if (other.superm != null)
                return false;
        } else if (!superm.equals(other.superm))
            return false;
        return true;
    }
}
