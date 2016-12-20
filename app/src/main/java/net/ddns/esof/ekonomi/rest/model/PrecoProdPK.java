package net.ddns.esof.ekonomi.rest.model;

import java.io.Serializable;

public class PrecoProdPK implements Serializable {

    private static final long serialVersionUID = 1L;

    private int marca;
    private int prod;
    private int superm;
    private float qtUnPad;

    public PrecoProdPK(int marca, int produto, int supermercado, float qtUnPad) {
        this.marca = marca;
        this.prod = produto;
        this.superm = supermercado;
        this.qtUnPad = qtUnPad;
    }

    public PrecoProdPK() {
        super();
    }

    public int getMarca() {
        return marca;
    }

    public void setMarca(int marca) {
        this.marca = marca;
    }

    public int getProd() {
        return prod;
    }

    public void setProd(int prod) {
        this.prod = prod;
    }

    public int getSuperm() {
        return superm;
    }

    public void setSuperm(int superm) {
        this.superm = superm;
    }

    public float getQtUnPad() {
        return qtUnPad;
    }

    public void setQtUnPad(float qtUnPad) {
        this.qtUnPad = qtUnPad;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + marca;
        result = prime * result + prod;
        result = prime * result + Float.floatToIntBits(qtUnPad);
        result = prime * result + superm;
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
        PrecoProdPK other = (PrecoProdPK) obj;
        if (marca != other.marca)
            return false;
        if (prod != other.prod)
            return false;
        if (Float.floatToIntBits(qtUnPad) != Float.floatToIntBits(other.qtUnPad))
            return false;
        if (superm != other.superm)
            return false;
        return true;
    }


}
