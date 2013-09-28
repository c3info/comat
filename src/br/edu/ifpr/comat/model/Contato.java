package br.edu.ifpr.comat.model;

import java.util.Objects;
import java.util.Set;

/**
 * @project comat
 * @class Contato
 * @author Cristhiano Konczak Cardoso <cristhiano@c3info.com.br>
 * @date 20/09/2013
 */
public class Contato {

    private Integer idContato;
    private String nome;
    private String telefone;
    private String celular;
    private String email;
    private String funcao;
    private Set clientes;

    public Contato() {
    }

    public Contato(Integer idContato, String nome, String telefone, String celular, String email, String funcao, Set clientes) {
        this.idContato = idContato;
        this.nome = nome;
        this.telefone = telefone;
        this.celular = celular;
        this.email = email;
        this.funcao = funcao;
        this.clientes = clientes;
    }

    public Integer getIdContato() {
        return idContato;
    }

    public void setIdContato(Integer idContato) {
        this.idContato = idContato;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public Set getClientes() {
        return clientes;
    }

    public void setClientes(Set clientes) {
        this.clientes = clientes;
    }
}
