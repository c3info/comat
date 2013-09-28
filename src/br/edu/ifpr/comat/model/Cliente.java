package br.edu.ifpr.comat.model;

/**
 * @project Comat
 * @class Cliente
 * @author Cristhiano Konczak Cardoso <cristhiano@c3info.com.br>
 * @date 17/09/2013
 */
public class Cliente implements java.io.Serializable {

    Integer idCliente;
    Integer status;
    String email;
    String site;
    String telefone;
    String observacoes;
    Endereco endereco;
   // List<Contato> contatos;

    public Cliente() {
    }

    public Cliente(Integer idCliente, Integer status, String email, String site, String telefone, String observacoes, Endereco endereco) {
        this.idCliente = idCliente;
        this.status = status;
        this.email = email;
        this.site = site;
        this.telefone = telefone;
        this.observacoes = observacoes;
        this.endereco = endereco;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
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

    
    
}
