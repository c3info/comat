package br.edu.ifpr.comat.model;

import java.util.List;
import java.util.Objects;

/**
 * @project Comat
 * @class ClienteJuridica
 * @author Cristhiano Konczak Cardoso <cristhiano@c3info.com.br>
 * @date 17/09/2013
 */
public class ClienteJuridica extends Cliente {

    long cnpj;
    int inscricao;
    String razao;
    String fantasia;
    String fax;

    public ClienteJuridica() {
    }

    public ClienteJuridica(long cnpj, int inscricao, String razao, String fantasia, String fax, Integer idCliente, Integer status, String email, String site, String telefone, String observacoes, Endereco endereco) {
        super(idCliente, status, email, site, telefone, observacoes, endereco);
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

    public int getInscricao() {
        return inscricao;
    }

    public void setInscricao(int inscricao) {
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
