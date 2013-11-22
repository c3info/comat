package br.edu.ifpr.comat.ui.components.tables;

import java.util.List;

import javax.swing.table.AbstractTableModel;
import br.edu.ifpr.comat.model.Produto;

public class TbModelProduto extends AbstractTableModel {

	private static final int COL_REF = 0;
	private static final int COL_NOME = 1;
	private static final int COL_MARCA = 2;
	private static final int COL_UND = 3;
	private static final int COL_QUANT = 4;
	private static final int COL_PVENDA = 5;
	private static final int COL_DESC_MAX = 6;
	private static final int COL_STATUS = 7;

	private List<Produto> rows;
	private String[] columns = new String[] { "Ref.", "Nome", "Marca",
			"Unidade", "Quant.", "Preço", "Desc.","Status" };

	public TbModelProduto(List<Produto> produtos) {
		this.rows = produtos;
	}

	@Override
	public int getColumnCount() {
		return columns.length;
	}

	public String getColumnName(int index) {
		return columns[index];
	}

	@Override
	public int getRowCount() {
		return rows.size();
	}

	@Override
	public Object getValueAt(int row, int column) {
		Produto p = rows.get(row);

		if (column == COL_REF) {
			return p.getRefProduto();
		} else if (column == COL_NOME) {
			return p.getNome();
		} else if (column == COL_MARCA) {
			return p.getMarca();
		} else if (column == COL_UND) {
			return p.getUnidade();
		} else if (column == COL_QUANT) {
			return p.getQuantidade();
		} else if (column == COL_PVENDA) {
			return p.getPrecoVenda();
		} else if (column == COL_DESC_MAX) {
			return p.getDescontoMax();
		} else if (column == COL_STATUS) {
			return p.getStatus();
		}

		return "";
	}

	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
}
