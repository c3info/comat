package br.edu.ifpr.comat.ui.components.tables;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.edu.ifpr.comat.model.ClienteFisica;
import br.edu.ifpr.comat.util.DateUtils;

public class TbModelClienteFisica extends AbstractTableModel {

	private static final int COL_ID = 0;
	private static final int COL_DT_CAD = 1;
	private static final int COL_NOME = 2;
	private static final int COL_CPF = 3;
	private static final int COL_TELEFONE = 4;
	private static final int COL_CELULAR = 5;
	private static final int COL_EMAIL = 6;
	private static final int COL_CIDADE = 7;
	private static final int COL_ESTADO = 8;
	private static final int COL_STATUS = 9;

	private List<ClienteFisica> rows;
	private String[] columns = new String[] { "Id", "Dt. Cadastro", "Nome",
			"CPF", "Telefone", "Celular", "Email", "Cidade", "UF", "Status" };

	public TbModelClienteFisica(List<ClienteFisica> clientes) {
		this.rows = clientes;
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
		ClienteFisica c = rows.get(row);

		if (column == COL_ID) {
			return c.getIdCliente();
		} else if (column == COL_DT_CAD) {
			return DateUtils.formatarData(c.getDataCadastro());
		} else if (column == COL_NOME) {
			return c.getNome();
		} else if (column == COL_CPF) {
			return c.getCpf();
		} else if (column == COL_TELEFONE) {			
			return c.getTelefone();
		} else if (column == COL_CELULAR) {
			return c.getCelular();
		} else if (column == COL_EMAIL) {
			if(c.getEmail() == null) return " ";
			else return	c.getEmail();
		} else if (column == COL_CIDADE) {
			return c.getEndereco().getCidade().getNome();
		} else if (column == COL_ESTADO) {
			return c.getEndereco().getCidade().getEstado().getUf();
		} else if (column == COL_STATUS) {
			return c.getStatus();
		}

		return "";
	}

	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

}
