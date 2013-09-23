package br.edu.ifpr.comat.modelo;

import java.util.Objects;

/**
 * @project comat
 * @class Endereco
 * @author Cristhiano Konczak Cardoso <cristhiano@c3info.com.br>
 * @date 20/09/2013
 */
public class Endereco {

    int idEnderco;
    String tipo;
    String logradouro;
    int numero;
    String complemento;
    String bairro;
    int cep;
    Cidade cidade;

    public Endereco() {
    }

    public Endereco(int idEnderco, String tipo, String logradouro, int numero, String complemento, String bairro, int cep, Cidade cidade) {
        this.idEnderco = idEnderco;
        this.tipo = tipo;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cep = cep;
        this.cidade = cidade;
    }

    public int getIdEnderco() {
        return idEnderco;
    }

    public void setIdEnderco(int idEnderco) {
        this.idEnderco = idEnderco;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public int getCep() {
        return cep;
    }

    public void setCep(int cep) {
        this.cep = cep;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + this.idEnderco;
        hash = 79 * hash + Objects.hashCode(this.tipo);
        hash = 79 * hash + Objects.hashCode(this.logradouro);
        hash = 79 * hash + this.numero;
        hash = 79 * hash + Objects.hashCode(this.complemento);
        hash = 79 * hash + Objects.hashCode(this.bairro);
        hash = 79 * hash + this.cep;
        hash = 79 * hash + Objects.hashCode(this.cidade);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Endereco other = (Endereco) obj;
        if (this.idEnderco != other.idEnderco) {
            return false;
        }
        if (!Objects.equals(this.tipo, other.tipo)) {
            return false;
        }
        if (!Objects.equals(this.logradouro, other.logradouro)) {
            return false;
        }
        if (this.numero != other.numero) {
            return false;
        }
        if (!Objects.equals(this.complemento, other.complemento)) {
            return false;
        }
        if (!Objects.equals(this.bairro, other.bairro)) {
            return false;
        }
        if (this.cep != other.cep) {
            return false;
        }
        if (!Objects.equals(this.cidade, other.cidade)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Endereco{" + "idEnderco=" + idEnderco + ", tipo=" + tipo + ", logradouro=" + logradouro + ", numero=" + numero + ", complemento=" + complemento + ", bairro=" + bairro + ", cep=" + cep + ", cidade=" + cidade + '}';
    }
}
