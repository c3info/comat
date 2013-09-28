package br.edu.ifpr.comat.model;

import java.util.Objects;

/**
 * @project comat
 * @class Estado
 * @author Cristhiano Konczak Cardoso <cristhiano@c3info.com.br>
 * @date 20/09/2013
 */
public class Estado implements java.io.Serializable {

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
}
