package br.edu.ifpr.comat.ui.components.tables;

import java.awt.Color;
import java.awt.Component;
import java.text.NumberFormat;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TbRenderOrcamento extends DefaultTableCellRenderer {
	int coll;

	public TbRenderOrcamento(int coll) {
		this.coll = coll;
	}

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		Component result = super.getTableCellRendererComponent(table, value,
				isSelected, hasFocus, row, column);

		Integer status = (Integer) table.getValueAt(row, coll);

		switch (status) {
		case 1:
			setForeground(Color.gray);
			break;

		case 2:
			setForeground(Color.red);
			break;

		default:
			setForeground(Color.black);
			break;
		}

		return result;
	}
}
