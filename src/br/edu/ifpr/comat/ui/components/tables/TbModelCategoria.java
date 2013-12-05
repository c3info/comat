package br.edu.ifpr.comat.ui.components.tables;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.edu.ifpr.comat.model.Categoria;

public class TbModelCategoria extends AbstractTableModel {

	private static final int COL_ID = 0;
	private static final int COL_NOME = 1;

	private List<Categoria> rows;
	private String[] columns = new String[] { "ID.", "Nome" };

	public TbModelCategoria(List<Categoria> categoria) {
		this.rows = categoria;
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
		Categoria c = rows.get(row);

		if (column == COL_ID) {
			return c.getIdCategoria();
		} else if (column == COL_NOME) {
			return c.getNomeCategoria();
		}

		return "";
	}

	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
}
