package br.edu.ifpr.comat.model;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @project Comat
 * @class ClienteFisica
 * @author Cristhiano Konczak Cardoso <cristhiano@c3info.com.br>
 * @date 17/09/2013
 */
public class ClienteFisica extends Cliente {

    long cpf;
    int rg;
    String nome;
    Date dataNasc;
    String celular;

    public ClienteFisica() {
    }

    public ClienteFisica(long cpf, int rg, String nome, Date dataNas, String celular, Integer idCliente, Integer status, String email, String site, String telefone, String observacoes, Endereco endereco) {
        super(idCliente, status, email, site, telefone, observacoes, endereco);
        this.cpf = cpf;
        this.rg = rg;
        this.nome = nome;
        this.dataNasc = dataNas;
        this.celular = celular;
    }

    public long getCpf() {
        return cpf;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }

    public int getRg() {
        return rg;
    }

    public void setRg(int rg) {
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

    public void setDataNasc(Date dataNas) {
        this.dataNasc = dataNas;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }
}
