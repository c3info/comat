package br.edu.ifpr.comat.model;

public class ItenOrcamento implements java.io.Serializable {

	private ItenOrcamentoId id;
	private Orcamento orcamento;
	private Produto produto;
	private Double preco;
	private Integer quantidade = 0;
	private Double desconto = 0d;

	public ItenOrcamento() {
	}

	public ItenOrcamento(ItenOrcamentoId id, Orcamento orcamento,
			Produto produto, double preco, int quantidade, Double desconto) {
		this.id = id;
		this.orcamento = orcamento;
		this.produto = produto;
		this.preco = preco;
		this.quantidade = quantidade;
		this.desconto = desconto;
	}

	public ItenOrcamentoId getId() {
		return this.id;
	}

	public void setId(ItenOrcamentoId id) {
		this.id = id;
	}

	public Orcamento getOrcamento() {
		return this.orcamento;
	}

	public void setOrcamento(Orcamento orcamento) {
		this.orcamento = orcamento;
	}

	public Produto getProduto() {
		return this.produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Double getPreco() {
		return this.preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public Integer getQuantidade() {
		return this.quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Double getDesconto() {
		return this.desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

}
