package br.edu.ifpr.comat.model;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

/**
 * @project Comat
 * @class ClienteFisica
 * @author Cristhiano Konczak Cardoso <cristhiano@c3info.com.br>
 * @date 17/09/2013
 */
public class ClienteFisica extends Cliente {

    private long cpf;
    private Integer rg;
    private String nome;
    private Date dataNasc;
    private String celular;

    public ClienteFisica() {
    }

    public ClienteFisica(long cpf, Integer rg, String nome, Date dataNasc, String celular, Integer idCliente, Integer status, String email, String site, String telefone, String observacoes, Endereco endereco, Set contatos) {
        super(idCliente, status, email, site, telefone, observacoes, endereco, contatos);
        this.cpf = cpf;
        this.rg = rg;
        this.nome = nome;
        this.dataNasc = dataNasc;
        this.celular = celular;
    }

    public long getCpf() {
        return cpf;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }

    public Integer getRg() {
        return rg;
    }

    public void setRg(Integer rg) {
        this.rg = rg;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }
}
