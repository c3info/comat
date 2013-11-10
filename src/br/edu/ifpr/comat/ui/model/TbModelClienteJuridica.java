package br.edu.ifpr.comat.ui.model;

import java.util.List;

import javax.swing.table.AbstractTableModel;
import br.edu.ifpr.comat.model.ClienteJuridica;
import br.edu.ifpr.comat.util.DateUtils;

public class TbModelClienteJuridica extends AbstractTableModel {

	private static final int COL_ID = 0;
	private static final int COL_DT_CAD = 1;
	private static final int COL_RAZAO = 2;
	private static final int COL_CNPJ = 3;
	private static final int COL_TELEFONE = 4;
	private static final int COL_FAX = 5;
	private static final int COL_EMAIL = 6;
	private static final int COL_CIDADE = 7;
	private static final int COL_ESTADO = 8;

	private List<ClienteJuridica> rows;
	private String[] columns = new String[] { "Id", "Dt. Cadastro", "Razão",
			"CNPJ", "Telefone", "Fax", "Email", "Cidade", "UF" };

	public TbModelClienteJuridica(List<ClienteJuridica> clientes) {
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
		ClienteJuridica c = rows.get(row);

		if (column == COL_ID) {
			return c.getIdCliente();
		} else if (column == COL_DT_CAD) {
			return DateUtils.formatarData(c.getDataCadastro());
		} else if (column == COL_RAZAO) {
			return c.getRazao();
		} else if (column == COL_CNPJ) {
			return c.getCnpj();
		} else if (column == COL_TELEFONE) {
			return c.getTelefone();
		} else if (column == COL_FAX) {
			return c.getFax();
		} else if (column == COL_EMAIL) {
			return c.getEmail();
		} else if (column == COL_CIDADE) {
			return c.getEndereco().getCidade().getNome();
		} else if (column == COL_ESTADO) {
			return c.getEndereco().getCidade().getEstado().getNome();
		}
		return "";
	}

}
