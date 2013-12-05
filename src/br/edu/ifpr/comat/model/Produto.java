package br.edu.ifpr.comat.model;

public class Produto implements java.io.Serializable {

	private Integer refProduto;
	private String codBarra;
	private String codFabricante;
	private String nome;
	private String descricao;
	private String unidade;
	private Double precoCusto;
	private Double precoVenda;
	private Double descontoMax;
	private Integer quantidade;
	private int status;
	private String marca;
	private Double peso;
	private Categoria categoria;

	public Produto() {

	}

	public Produto(Integer refProduto, String codBarra, String codFabricante,
			String nome, String descricao, String unidade, Double precoCusto,
			Double precoVenda, Double descontoMax, Integer quantidade,
			int status, String marca, Double peso, Categoria categoria) {

		this.refProduto = refProduto;
		this.codBarra = codBarra;
		this.codFabricante = codFabricante;
		this.nome = nome;
		this.descricao = descricao;
		this.unidade = unidade;
		this.precoCusto = precoCusto;
		this.precoVenda = precoVenda;
		this.descontoMax = descontoMax;
		this.quantidade = quantidade;
		this.status = status;
		this.marca = marca;
		this.peso = peso;
		this.categoria = categoria;
	}

	public Integer getRefProduto() {
		return refProduto;
	}

	public void setRefProduto(Integer refProduto) {
		this.refProduto = refProduto;
	}

	public String getCodBarra() {
		return codBarra;
	}

	public void setCodBarra(String codBarra) {
		this.codBarra = codBarra;
	}

	public String getCodFabricante() {
		return codFabricante;
	}

	public void setCodFabricante(String codFabricante) {
		this.codFabricante = codFabricante;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	public Double getPrecoCusto() {
		return precoCusto;
	}

	public void setPrecoCusto(Double precoCusto) {
		this.precoCusto = precoCusto;
	}

	public Double getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(Double precoVenda) {
		this.precoVenda = precoVenda;
	}

	public Double getDescontoMax() {
		return descontoMax;
	}

	public void setDescontoMax(Double descontoMax) {
		this.descontoMax = descontoMax;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

}
