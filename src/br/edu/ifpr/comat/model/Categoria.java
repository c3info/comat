package br.edu.ifpr.comat.model;

/**
 * @project comat
 * @class Categoria
 * @author Cristhiano Konczak Cardoso <cristhiano@c3info.com.br>
 * @date 12/11/2013
 */
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
