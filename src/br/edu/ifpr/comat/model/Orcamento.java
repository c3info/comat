package br.edu.ifpr.comat.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @project comat
 * @class Orcamento
 * @author Cristhiano Konczak Cardoso <cristhiano@c3info.com.br>
 * @date 23/11/2013
 */
public class Orcamento implements java.io.Serializable {
	
	private Integer idOrcamento;
	private Date data;
	private Integer status;
	private Date validade;
	private String observacoes;
	private BigDecimal total;
	private Cliente cliente;
	private Obra obra;

	public Orcamento() {

	}

	public Orcamento(Date data, Integer status,
			Date validade, String observacoes, BigDecimal total,
			Cliente cliente, Obra obra) {
		
		this.data = data;
		this.status = status;
		this.validade = validade;
		this.observacoes = observacoes;
		this.total = total;
		this.cliente = cliente;
		this.obra = obra;
	}

	public Integer getIdOrcamento() {
		return idOrcamento;
	}

	public void setIdOrcamento(Integer idOrcamento) {
		this.idOrcamento = idOrcamento;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getValidade() {
		return validade;
	}

	public void setValidade(Date validade) {
		this.validade = validade;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Obra getObra() {
		return obra;
	}

	public void setObra(Obra obra) {
		this.obra = obra;
	}

}
