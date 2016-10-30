package net.ddns.esof.ekonomi.rest.classes;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PrecoProd {

    private Marca marca;

    private Produto prod;

    private Supermercado superm;

    private float qtUnPad;

    private float precoProd;

    private Calendar dataInic;

    private Usuario user;

    public PrecoProd(Produto prod, Marca marca, Supermercado superm, float qtUn, float preco, Calendar dataInic, Usuario user) {
        this.marca = marca;
        this.prod = prod;
        this.superm = superm;
        this.qtUnPad = qtUn;
        this.precoProd = preco;
        this.dataInic = dataInic;
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
        return prod;
    }

    public void setProduto(Produto produto) {
        this.prod = produto;
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

    public Calendar getDataInc() {
        return dataInic;
    }

    public void setDataInc(Calendar dataInic) {
        this.dataInic = dataInic;
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
        result = prime * result + ((prod == null) ? 0 : prod.hashCode());
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
        if (prod == null) {
            if (other.prod != null)
                return false;
        } else if (!prod.equals(other.prod))
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

    @Override
    public String toString() {
        SimpleDateFormat f = new SimpleDateFormat("dd/MMMM/yyyy, HH:mm:ss");
        return "PrecoProd [marca=" + marca + "\nprod=" + prod + "\nsuperm=" + superm + "\nqtUnPad=" + qtUnPad
                + "\nprecoProd=" + precoProd + "\ndataInic=" + f.format(dataInic.getTime()) + "\nuser=" + user + "]";
    }


}
