package br.edu.ifpr.comat.ui.components.tables;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.edu.ifpr.comat.model.ProdutoRelacionado;

public class TbModelProdutoRelacionado extends AbstractTableModel {

	private static final int COL_REF = 0;
	private static final int COL_NOME = 1;
	private static final int COL_MARCA = 2;
	private static final int COL_UND = 3;
	private static final int COL_QUANT = 4;
	private static final int COL_PVENDA = 5;
	private static final int COL_DESC_MAX = 6;
	private static final int COL_STATUS = 7;

	private List<ProdutoRelacionado> rows;
	private String[] columns = new String[] { "Ref.", "Nome", "Marca",
			"Unidade", "Quant.", "Preço", "Desc.", "Status" };

	public TbModelProdutoRelacionado() {
		this.rows = new ArrayList<>();
	}

	public TbModelProdutoRelacionado(List<ProdutoRelacionado> produtos) {
		this.rows = produtos;
	}

	@Override
	public int getRowCount() {
		return rows.size();
	}

	@Override
	public int getColumnCount() {
		return columns.length;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return columns[columnIndex];
	}

	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

	@Override
	public Object getValueAt(int row, int column) {
		ProdutoRelacionado p = rows.get(row);

		if (column == COL_REF) {
			return p.getProdutoByRefProdutoRelacFk().getRefProduto();
		} else if (column == COL_NOME) {
			return p.getProdutoByRefProdutoRelacFk().getNome();
		} else if (column == COL_MARCA) {
			return p.getProdutoByRefProdutoRelacFk().getMarca();
		} else if (column == COL_UND) {
			return p.getProdutoByRefProdutoRelacFk().getUnidade();
		} else if (column == COL_QUANT) {
			return p.getProdutoByRefProdutoRelacFk().getQuantidade();
		} else if (column == COL_PVENDA) {
			return p.getProdutoByRefProdutoRelacFk().getPrecoVenda();
		} else if (column == COL_DESC_MAX) {
			return p.getProdutoByRefProdutoRelacFk().getDescontoMax();
		} else if (column == COL_STATUS) {
			return p.getProdutoByRefProdutoRelacFk().getStatus();
		}

		return "";
	}

	public ProdutoRelacionado getItens(int rowIndex) {
		return rows.get(rowIndex);
	}

	public void addIten(ProdutoRelacionado item) {
		rows.add(item);
		int ultimoIndice = getRowCount() - 1;
		fireTableRowsInserted(ultimoIndice, ultimoIndice);
	}

	public void removeIten(int rowIndex) {
		rows.remove(rowIndex);
		fireTableRowsDeleted(rowIndex, rowIndex);
	}

	public void addListItens(List<ProdutoRelacionado> itens) {
		int indice = getRowCount();
		rows.addAll(itens);
		fireTableRowsInserted(indice, indice + itens.size());
	}

	public List<ProdutoRelacionado> getRows() {
		return rows;
	}

	public void limpar() {
		rows.clear();
		fireTableDataChanged();
	}
}
