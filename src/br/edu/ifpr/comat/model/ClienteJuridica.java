package br.edu.ifpr.comat.model;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

/**
 * @project Comat
 * @class ClienteJuridica
 * @author Cristhiano Konczak Cardoso <cristhiano@c3info.com.br>
 * @date 17/09/2013
 */
public class ClienteJuridica extends Cliente implements java.io.Serializable{

    private String cnpj;
    private String inscricao;
    private String razao;
    private String fantasia;
    private String fax;

    public ClienteJuridica() {
    }

    public ClienteJuridica(String cnpj, String inscricao, String razao, String fantasia, String fax, Integer idCliente, Integer status, String email, String site, String telefone, String observacoes, Date dataCadastro, Endereco endereco) {
        super(idCliente, status, email, site, telefone, observacoes, dataCadastro, endereco);
        this.cnpj = cnpj;
        this.inscricao = inscricao;
        this.razao = razao;
        this.fantasia = fantasia;
        this.fax = fax;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getInscricao() {
        return inscricao;
    }

    public void setInscricao(String inscricao) {
        this.inscricao = inscricao;
    }

    public String getRazao() {
        return razao;
    }

    public void setRazao(String razao) {
        this.razao = razao;
    }

    public String getFantasia() {
        return fantasia;
    }

    public void setFantasia(String fantasia) {
        this.fantasia = fantasia;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }
}
