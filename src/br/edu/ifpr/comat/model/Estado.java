package br.edu.ifpr.comat.model;

import java.util.HashSet;
import java.util.Set;

/**
 * @project comat
 * @class Estado
 * @author Cristhiano Konczak Cardoso <cristhiano@c3info.com.br>
 * @date 20/09/2013
 */
public class Estado implements java.io.Serializable {

	private String uf;
	private String nome;
	private Set cidades = new HashSet(0);

	public Estado() {
	}

	public Estado(String uf, String nome) {
		this.uf = uf;
		this.nome = nome;
	}

	public Estado(String uf, String nome, Set cidades) {
		this.uf = uf;
		this.nome = nome;
		this.cidades = cidades;
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

	public Set getCidades() {
		return this.cidades;
	}

	public void setCidades(Set cidades) {
		this.cidades = cidades;
	}
}
