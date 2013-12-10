package br.edu.ifpr.comat.ui.components.tables;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.edu.ifpr.comat.model.Produto;

public class TbModelRelacionaProduto extends AbstractTableModel {

	private static final int COL_REF = 0;
	private static final int COL_NOME = 1;
	private static final int COL_MARCA = 2;
	private static final int COL_STATUS = 3;

	private boolean[] select;

	private List<Produto> rows;
	private String[] columns = new String[] { "Ref", "Nome", "Marca", " " };

	public TbModelRelacionaProduto(List<Produto> produtos) {
		this.rows = produtos;
		this.select = new boolean[produtos.size()];
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

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case COL_REF:
			return Integer.class;
		case COL_NOME:
			return String.class;
		case COL_MARCA:
			return String.class;
		case COL_STATUS:
			return Boolean.class;
		default:
			throw new IndexOutOfBoundsException(
					"Índice informado fora dos limites.");
		}
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
		} else if (column == COL_STATUS) {
			return select[row];
		}

		return "";
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Produto p = rows.get(rowIndex);

		switch (columnIndex) {

		case COL_STATUS:
			select[rowIndex] = ((Boolean) aValue);
			break;

		default:
			throw new IndexOutOfBoundsException(
					"Índice informado fora dos limites.");
		}
		fireTableCellUpdated(rowIndex, columnIndex);

	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if (columnIndex == COL_STATUS)
			return true;
		else
			return false;
	}

	public List<Produto> getRows() {
		return rows;
	}

	public boolean[] getSelect() {
		return select;
	}
	
	public void limpar() {
		rows.clear();
		fireTableDataChanged();
	}

}
