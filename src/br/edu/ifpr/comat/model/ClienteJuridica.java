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

    public ClienteJuridica(long cnpj, int inscricao, String razao, String fantasia, String fax, int idCliente, int status, String email, String site, String telefone, String observacoes, Endereco endereco, List<Contato> contatos) {
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + (int) (this.cnpj ^ (this.cnpj >>> 32));
        hash = 13 * hash + this.inscricao;
        hash = 13 * hash + Objects.hashCode(this.razao);
        hash = 13 * hash + Objects.hashCode(this.fantasia);
        hash = 13 * hash + Objects.hashCode(this.fax);
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
        final ClienteJuridica other = (ClienteJuridica) obj;
        if (this.cnpj != other.cnpj) {
            return false;
        }
        if (this.inscricao != other.inscricao) {
            return false;
        }
        if (!Objects.equals(this.razao, other.razao)) {
            return false;
        }
        if (!Objects.equals(this.fantasia, other.fantasia)) {
            return false;
        }
        if (!Objects.equals(this.fax, other.fax)) {
            return false;
        }
        return true;
    }
}
