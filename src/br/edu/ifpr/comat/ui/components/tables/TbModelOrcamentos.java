package br.edu.ifpr.comat.ui.components.tables;

import java.text.NumberFormat;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;

import br.edu.ifpr.comat.model.ClienteFisica;
import br.edu.ifpr.comat.model.ClienteJuridica;
import br.edu.ifpr.comat.model.Orcamento;
import br.edu.ifpr.comat.util.DateUtils;

public class TbModelOrcamentos extends AbstractTableModel {

	private static final int COL_ID = 0;
	private static final int COL_DATA = 1;
	private static final int COL_CLIENTE = 2;
	private static final int COL_DOCUMENTO = 3;
	private static final int COL_CIDADE = 4;
	private static final int COL_TOTAL = 5;
	private static final int COL_VALIDADE = 6;
	private static final int COL_STATUS = 7;

	private List<Orcamento> rows;
	private String[] columns = new String[] { "Cod.", "Data", "Cliente",
			"CPF / CNPJ", "Cidade", "Total", "Validade", "Status" };

	public TbModelOrcamentos(List<Orcamento> orcamentos) {
		this.rows = orcamentos;
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
		Orcamento orc = rows.get(row);
		NumberFormat formatter = NumberFormat.getCurrencyInstance();		

		if (column == COL_ID) {
			return orc.getIdOrcamento();
		} else if (column == COL_DATA) {
			return DateUtils.date2Str(orc.getData());
		} else if (column == COL_CLIENTE) {
			if (orc.getCliente() instanceof ClienteFisica) {
				return ((ClienteFisica) orc.getCliente()).getNome();
			} else {
				return ((ClienteJuridica) orc.getCliente()).getRazao();
			}
		} else if (column == COL_DOCUMENTO) {
			if (orc.getCliente() instanceof ClienteFisica) {
				return ((ClienteFisica) orc.getCliente()).getCpf();
			} else {
				return ((ClienteJuridica) orc.getCliente()).getCnpj();
			}
		} else if (column == COL_CIDADE) {
			return orc.getCliente().getEndereco().getCidade().getNome();
		} else if (column == COL_TOTAL) {
			return formatter.format(orc.getTotal());
		} else if (column == COL_VALIDADE) {
			return DateUtils.date2Str(orc.getValidade());
		} else if (column == COL_STATUS) {
			return orc.getStatus();
		}

		return "";
	}

	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
}
