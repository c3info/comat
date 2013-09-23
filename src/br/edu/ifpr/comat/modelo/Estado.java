package br.edu.ifpr.comat.modelo;

import java.util.Objects;

/**
 * @project comat
 * @class Estado
 * @author Cristhiano Konczak Cardoso <cristhiano@c3info.com.br>
 * @date 20/09/2013
 */
public class Estado {

    String uf;
    String nome;

    public Estado() {
    }

    public Estado(String uf, String nome) {
        this.uf = uf;
        this.nome = nome;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.uf);
        hash = 71 * hash + Objects.hashCode(this.nome);
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
        final Estado other = (Estado) obj;
        if (!Objects.equals(this.uf, other.uf)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Estado{" + "uf=" + uf + ", nome=" + nome + '}';
    }
}
