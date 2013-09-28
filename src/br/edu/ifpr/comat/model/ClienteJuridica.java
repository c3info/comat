package br.edu.ifpr.comat.model;

import java.util.Objects;
import java.util.Set;

/**
 * @project Comat
 * @class ClienteJuridica
 * @author Cristhiano Konczak Cardoso <cristhiano@c3info.com.br>
 * @date 17/09/2013
 */
public class ClienteJuridica extends Cliente {

    private long cnpj;
    private Integer inscricao;
    private String razao;
    private String fantasia;
    private String fax;

    public ClienteJuridica() {
    }

    public ClienteJuridica(long cnpj, Integer inscricao, String razao, String fantasia, String fax, Integer idCliente, Integer status, String email, String site, String telefone, String observacoes, Endereco endereco, Set contatos) {
        super(idCliente, status, email, site, telefone, observacoes, endereco, contatos);
        this.cnpj = cnpj;
        this.inscricao = inscricao;
        this.razao = razao;
        this.fantasia = fantasia;
        this.fax = fax;
    }

    public long getCnpj() {
        return cnpj;
    }

    public void setCnpj(long cnpj) {
        this.cnpj = cnpj;
    }

    public Integer getInscricao() {
        return inscricao;
    }

    public void setInscricao(Integer inscricao) {
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
