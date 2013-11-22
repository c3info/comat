package br.edu.ifpr.comat.ui.components.tables;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import br.edu.ifpr.comat.model.Contato;


public class TbModelContato extends AbstractTableModel {

	private static final int COL_ID = 0;
	private static final int COL_NOME = 1;
	private static final int COL_FONE = 2;
	private static final int COL_CEL = 3;
	private static final int COL_EMAIL = 4;
	private static final int COL_FUNCAO = 5;


	private List<Contato> rows;
	private String[] columns = new String[] { "ID.", "Nome", "Telefone",
			"Celular", "e-mail", "Função" };

	public TbModelContato(List<Contato> contatos) {	
		this.rows = new ArrayList<>(contatos);
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
		Contato c = rows.get(row);

		if (column == COL_ID) {
			return c.getIdContato();
		} else if (column == COL_NOME) {
			return c.getNome();
		} else if (column == COL_FONE) {
			return c.getTelefone();
		} else if (column == COL_CEL) {
			return c.getCelular();
		} else if (column == COL_EMAIL) {
			return c.getEmail();
		} else if (column == COL_FUNCAO) {
			return c.getFuncao();
		} 

		return "";
	}

	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
}
