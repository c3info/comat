package br.edu.ifpr.comat.model;

public class ProdutoRelacionadoId implements java.io.Serializable {

	private int refProdutoRelFk;
	private int refProdutoRelacFk;

	public ProdutoRelacionadoId() {
	}

	public ProdutoRelacionadoId(int refProdutoRelFk, int refProdutoRelacFk) {
		this.refProdutoRelFk = refProdutoRelFk;
		this.refProdutoRelacFk = refProdutoRelacFk;
	}

	public int getRefProdutoRelFk() {
		return this.refProdutoRelFk;
	}

	public void setRefProdutoRelFk(int refProdutoRelFk) {
		this.refProdutoRelFk = refProdutoRelFk;
	}

	public int getRefProdutoRelacFk() {
		return this.refProdutoRelacFk;
	}

	public void setRefProdutoRelacFk(int refProdutoRelacFk) {
		this.refProdutoRelacFk = refProdutoRelacFk;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ProdutoRelacionadoId))
			return false;
		ProdutoRelacionadoId castOther = (ProdutoRelacionadoId) other;

		return (this.getRefProdutoRelFk() == castOther.getRefProdutoRelFk())
				&& (this.getRefProdutoRelacFk() == castOther
						.getRefProdutoRelacFk());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getRefProdutoRelFk();
		result = 37 * result + this.getRefProdutoRelacFk();
		return result;
	}
}
