package br.edu.ifpr.comat.model;

public class Obra implements java.io.Serializable {

	private Integer idObra;
	private String nome;
	private String telefone;
	private String responsavel;
	private Cliente cliente;
	private Endereco endereco;

	public Obra() {
	}

	public Obra(String nome, String telefone, String responsavel,
			Cliente cliente, Endereco endereco) {

		this.nome = nome;
		this.telefone = telefone;
		this.responsavel = responsavel;
		this.cliente = cliente;
		this.endereco = endereco;
	}

	public Integer getIdObra() {
		return idObra;
	}

	public void setIdObra(Integer idObra) {
		this.idObra = idObra;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

}
