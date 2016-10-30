package net.ddns.esof.ekonomi.rest.classes;

public class Supermercado {

    private int id;

    private String nome;

    private String rua;

    private int numero;

    private String bairro;

    private Cidade cidade;

    private int codPostal;

    private String compl;

    private double latitude;
    private double longitude;

    public Supermercado(String nome, Cidade cidade, String bairro, String rua, int numero, int codPostal, String compl, double latitude, double longitude) {
        this.nome = nome;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
        this.codPostal = codPostal;
        this.compl = compl;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Supermercado() {
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

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public Cidade getCidade() {
        return this.cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public String getCompl() {
        return this.compl;
    }

    public void setComp(String compl) {
        this.compl = compl;
    }

    public int getCodPostal() {
        return codPostal;
    }

    public void setCodPostal(int codPostal) {
        this.codPostal = codPostal;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
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
        Supermercado other = (Supermercado) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Supermercado [nome=" + nome + ", rua=" + rua + ", numero=" + numero + ", bairro=" + bairro + ", cidade="
                + cidade + ", codPostal=" + codPostal + "]";
    }


}
