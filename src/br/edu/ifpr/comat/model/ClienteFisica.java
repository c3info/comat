package br.edu.ifpr.comat.model;

import java.util.Date;

/**
 * @project Comat
 * @class ClienteFisica
 * @author Cristhiano Konczak Cardoso <cristhiano@c3info.com.br>
 * @date 17/09/2013
 */
public class ClienteFisica extends Cliente implements java.io.Serializable {

	private String cpf;
	private String rg;
	private String nome;
	private Date dataNasc;
	private String celular;

	public ClienteFisica() {
	}

	public ClienteFisica(String cpf, String rg, String nome, Date dataNasc,
			String celular, Integer idCliente, Integer status, String email,
			String site, String telefone, String observacoes,
			Date dataCadastro, Endereco endereco) {
		super(status, email, site, telefone, observacoes,
				dataCadastro, endereco);
		this.cpf = cpf;
		this.rg = rg;
		this.nome = nome;
		this.dataNasc = dataNasc;
		this.celular = celular;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}
}
