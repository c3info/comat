package br.edu.ifpr.comat.model;

import java.util.Objects;

/**
 * @project comat
 * @class Endereco
 * @author Cristhiano Konczak Cardoso <cristhiano@c3info.com.br>
 * @date 20/09/2013
 */
public class Endereco implements java.io.Serializable {

    private Integer idEndereco;  
    private String logradouro;
    private Integer numero;
    private String complemento;
    private String bairro;
    private Integer cep;
    private Cidade cidade;

    public Endereco() {
    }

    public Endereco(Integer idEnderco, String logradouro, Integer numero, String complemento, String bairro, Integer cep, Cidade cidade) {
        this.idEndereco = idEnderco;       
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

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
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

    public Integer getCep() {
        return cep;
    }

    public void setCep(Integer cep) {
        this.cep = cep;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
}
