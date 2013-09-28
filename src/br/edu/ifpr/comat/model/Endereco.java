package br.edu.ifpr.comat.model;

import java.util.Objects;

/**
 * @project comat
 * @class Endereco
 * @author Cristhiano Konczak Cardoso <cristhiano@c3info.com.br>
 * @date 20/09/2013
 */
public class Endereco implements java.io.Serializable {

    Integer idEndereco;
    String tipo;
    String logradouro;
    int numero;
    String complemento;
    String bairro;
    int cep;
    Cidade cidade;

    public Endereco() {
    }

    public Endereco(Integer idEnderco, String tipo, String logradouro, int numero, String complemento, String bairro, int cep, Cidade cidade) {
        this.idEndereco = idEnderco;
        this.tipo = tipo;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cep = cep;
        this.cidade = cidade;
    }

    public Integer getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Integer idEndereco) {
        this.idEndereco = idEndereco;
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
}
