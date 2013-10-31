package br.edu.ifpr.comat.model;

public class Categoria implements java.io.Serializable {

	private Integer idCategoria;
	private String nomeCategoria;

	public Categoria() {

	}

	public Categoria(Integer idCategoria, String nomeCategoria) {
		this.idCategoria = idCategoria;
		this.nomeCategoria = nomeCategoria;
	}

	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}

}
