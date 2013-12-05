package br.edu.ifpr.comat.model;

import java.util.HashSet;
import java.util.Set;

/**
 * @project comat
 * @class Cidade
 * @author Cristhiano Konczak Cardoso <cristhiano@c3info.com.br>
 * @date 20/09/2013
 */
public class Cidade implements java.io.Serializable {
	
	private Integer idCidade;
	private String nome;
	private Estado estado;
	private Set enderecos = new HashSet(0);

	public Cidade() {
	}

	public Cidade(String nome, Estado estado) {
		this.nome = nome;
		this.estado = estado;
	}
	
	public Cidade(Estado estado, String nome, Set enderecos) {
		this.estado = estado;
		this.nome = nome;
		this.enderecos = enderecos;
	}

	public Integer getIdCidade() {
		return idCidade;
	}

	public void setIdCidade(Integer idCidade) {
		this.idCidade = idCidade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	public Set getEnderecos() {
		return this.enderecos;
	}

	public void setEnderecos(Set enderecos) {
		this.enderecos = enderecos;
	}
}
