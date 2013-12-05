package br.edu.ifpr.comat.ui.components.tables;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.edu.ifpr.comat.model.Obra;

public class TbModelObra extends AbstractTableModel {

	private static final int COL_ID = 0;
	private static final int COL_NOME = 1;
	private static final int COL_FONE = 2;
	private static final int COL_RESP = 3;
	private static final int COL_ENDER = 4;
	private static final int COL_CIDADE = 5;
	private static final int COL_UF = 6;

	private List<Obra> rows;
	private String[] columns = new String[] { "ID.", "Nome / Apelido",
			"Telefone", "Responsável", "Endereço", "Cidade", "UF" };

	public TbModelObra(List<Obra> obras) {
		this.rows = new ArrayList<>(obras);
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
		Obra o = rows.get(row);

		if (column == COL_ID) {
			return o.getIdObra();
		} else if (column == COL_NOME) {
			return o.getNome();
		} else if (column == COL_FONE) {
			return o.getTelefone();
		} else if (column == COL_RESP) {
			return o.getResponsavel();
		} else if (column == COL_ENDER) {
			return o.getEndereco().getLogradouro() + ", "
					+ o.getEndereco().getNumero();
		} else if (column == COL_CIDADE) {
			return o.getEndereco().getCidade().getNome();
		} else if (column == COL_UF) {
			return o.getEndereco().getCidade().getEstado().getUf();
		}

		return "";
	}

	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
}
