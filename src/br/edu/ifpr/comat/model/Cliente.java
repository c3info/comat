package br.edu.ifpr.comat.model;

import java.util.Date;

/**
 * @project Comat
 * @class Cliente
 * @author Cristhiano Konczak Cardoso <cristhiano@c3info.com.br>
 * @date 17/09/2013
 */
public class Cliente implements java.io.Serializable {

	private Integer idCliente;
	private Integer status;
	private String email;
	private String site;
	private String telefone;
	private String observacoes;
	private Date dataCadastro;
	private Endereco endereco;

	public Cliente() {
	}

	public Cliente(Integer status, String email, String site, String telefone,
			String observacoes, Date dataCadastro, Endereco endereco) {

		this.status = status;
		this.email = email;
		this.site = site;
		this.telefone = telefone;
		this.observacoes = observacoes;
		this.dataCadastro = dataCadastro;
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

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
}
