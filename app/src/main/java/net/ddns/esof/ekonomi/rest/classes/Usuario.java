package net.ddns.esof.ekonomi.rest.classes;

public class Usuario {

    private String user;

    private String password;

    private String nome;

    private String rua;

    private int numero;

    private String bairro;

    private String compl;

    private Cidade cidade;

    private int codPostal;


    public Usuario() {
        super();
    }

    public Usuario(String user, String nome, String password, Cidade cidade, String bairro, String compl, String rua, int numero, int codPostal) {
        this.user = user;
        this.nome = nome;
        this.password = password;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.compl = compl;
        this.numero = numero;
        this.codPostal = codPostal;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getCompl() {
        return compl;
    }

    public void setCompl(String compl) {
        this.compl = compl;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public int getCodPostal() {
        return codPostal;
    }

    public void setCodPostal(int codPostal) {
        this.codPostal = codPostal;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((user == null) ? 0 : user.hashCode());
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
        Usuario other = (Usuario) obj;
        if (user == null) {
            if (other.user != null)
                return false;
        } else if (!user.equals(other.user))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Usuario [user=" + user + ", password=" + password + ", nome=" + nome + ", rua=" + rua + ", numero="
                + numero + ", bairro=" + bairro + ", cidade=" + cidade + ", codPostal=" + codPostal + "]";
    }


}
