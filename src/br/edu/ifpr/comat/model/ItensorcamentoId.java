package br.edu.ifpr.comat.model;

public class ItensorcamentoId implements java.io.Serializable {

	private int idOrcamentoFk;
	private int refProdutoFk;

	public ItensorcamentoId() {
	}

	public ItensorcamentoId(int idOrcamentoFk, int refProdutoFk) {
		this.idOrcamentoFk = idOrcamentoFk;
		this.refProdutoFk = refProdutoFk;
	}

	public int getIdOrcamentoFk() {
		return this.idOrcamentoFk;
	}

	public void setIdOrcamentoFk(int idOrcamentoFk) {
		this.idOrcamentoFk = idOrcamentoFk;
	}

	public int getRefProdutoFk() {
		return this.refProdutoFk;
	}

	public void setRefProdutoFk(int refProdutoFk) {
		this.refProdutoFk = refProdutoFk;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ItensorcamentoId))
			return false;
		ItensorcamentoId castOther = (ItensorcamentoId) other;

		return (this.getIdOrcamentoFk() == castOther.getIdOrcamentoFk())
				&& (this.getRefProdutoFk() == castOther.getRefProdutoFk());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getIdOrcamentoFk();
		result = 37 * result + this.getRefProdutoFk();
		return result;
	}
}
