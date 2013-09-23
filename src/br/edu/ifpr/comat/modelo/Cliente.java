package br.edu.ifpr.comat.modelo;

import java.util.List;
import java.util.Objects;

/**
 * @project Comat
 * @class Cliente
 * @author Cristhiano Konczak Cardoso <cristhiano@c3info.com.br>
 * @date 17/09/2013
 */
public class Cliente {

    int idCliente;
    int status;
    String email;
    String site;
    String telefone;
    String observacoes;
    Endereco endereco;
    List<Contato> contatos;

    public Cliente() {
    }

    public Cliente(int idCliente, int status, String email, String site, String telefone, String observacoes, Endereco endereco, List<Contato> contatos) {
        this.idCliente = idCliente;
        this.status = status;
        this.email = email;
        this.site = site;
        this.telefone = telefone;
        this.observacoes = observacoes;
        this.endereco = endereco;
        this.contatos = contatos;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<Contato> getContatos() {
        return contatos;
    }

    public void setContatos(List<Contato> contatos) {
        this.contatos = contatos;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + this.idCliente;
        hash = 11 * hash + this.status;
        hash = 11 * hash + Objects.hashCode(this.email);
        hash = 11 * hash + Objects.hashCode(this.site);
        hash = 11 * hash + Objects.hashCode(this.telefone);
        hash = 11 * hash + Objects.hashCode(this.observacoes);
        hash = 11 * hash + Objects.hashCode(this.endereco);
        hash = 11 * hash + Objects.hashCode(this.contatos);
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
        final Cliente other = (Cliente) obj;
        if (this.idCliente != other.idCliente) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.site, other.site)) {
            return false;
        }
        if (!Objects.equals(this.telefone, other.telefone)) {
            return false;
        }
        if (!Objects.equals(this.observacoes, other.observacoes)) {
            return false;
        }
        if (!Objects.equals(this.endereco, other.endereco)) {
            return false;
        }
        if (!Objects.equals(this.contatos, other.contatos)) {
            return false;
        }
        return true;
    }
}
