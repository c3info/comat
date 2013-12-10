package br.edu.ifpr.comat.model;

import java.util.Date;

public class ProdutoRelacionado implements java.io.Serializable {

	private ProdutoRelacionadoId id;
	private Produto produtoByRefProdutoRelFk;
	private Produto produtoByRefProdutoRelacFk;
	private Date data;
	private Integer tipo;

	public ProdutoRelacionado() {
	}

	public ProdutoRelacionado(ProdutoRelacionadoId id,
			Produto produtoByRefProdutoRelFk,
			Produto produtoByRefProdutoRelacFk, Date data) {
		this.id = id;
		this.produtoByRefProdutoRelFk = produtoByRefProdutoRelFk;
		this.produtoByRefProdutoRelacFk = produtoByRefProdutoRelacFk;
		this.data = data;
	}

	public ProdutoRelacionado(ProdutoRelacionadoId id,
			Produto produtoByRefProdutoRelFk,
			Produto produtoByRefProdutoRelacFk, Date data, Integer tipo) {
		this.id = id;
		this.produtoByRefProdutoRelFk = produtoByRefProdutoRelFk;
		this.produtoByRefProdutoRelacFk = produtoByRefProdutoRelacFk;
		this.data = data;
		this.tipo = tipo;
	}

	public ProdutoRelacionadoId getId() {
		return this.id;
	}

	public void setId(ProdutoRelacionadoId id) {
		this.id = id;
	}

	public Produto getProdutoByRefProdutoRelFk() {
		return this.produtoByRefProdutoRelFk;
	}

	public void setProdutoByRefProdutoRelFk(Produto produtoByRefProdutoRelFk) {
		this.produtoByRefProdutoRelFk = produtoByRefProdutoRelFk;
	}

	public Produto getProdutoByRefProdutoRelacFk() {
		return this.produtoByRefProdutoRelacFk;
	}

	public void setProdutoByRefProdutoRelacFk(Produto produtoByRefProdutoRelacFk) {
		this.produtoByRefProdutoRelacFk = produtoByRefProdutoRelacFk;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Integer getTipo() {
		return this.tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}
}
