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
    Date dataNas;
    String celular;

    public ClienteFisica() {
    }

    public ClienteFisica(long cpf, int rg, String nome, Date dataNas, String celular, int idCliente, int status, String email, String site, String telefone, String observacoes, Endereco endereco, List<Contato> contatos) {
        super(idCliente, status, email, site, telefone, observacoes, endereco, contatos);
        this.cpf = cpf;
        this.rg = rg;
        this.nome = nome;
        this.dataNas = dataNas;
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

    public Date getDataNas() {
        return dataNas;
    }

    public void setDataNas(Date dataNas) {
        this.dataNas = dataNas;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + (int) (this.cpf ^ (this.cpf >>> 32));
        hash = 47 * hash + this.rg;
        hash = 47 * hash + Objects.hashCode(this.nome);
        hash = 47 * hash + Objects.hashCode(this.dataNas);
        hash = 47 * hash + Objects.hashCode(this.celular);
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
        final ClienteFisica other = (ClienteFisica) obj;
        if (this.cpf != other.cpf) {
            return false;
        }
        if (this.rg != other.rg) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.dataNas, other.dataNas)) {
            return false;
        }
        if (!Objects.equals(this.celular, other.celular)) {
            return false;
        }
        return true;
    }
}
