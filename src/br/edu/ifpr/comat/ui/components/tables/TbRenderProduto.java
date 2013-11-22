package br.edu.ifpr.comat.ui.components.tables;

import java.awt.Color;
import java.awt.Component;
import java.text.NumberFormat;
import java.text.ParseException;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.text.NumberFormatter;

import resources.NumberRenderer;

public class TbRenderProduto extends DefaultTableCellRenderer {
	int coll;

	public TbRenderProduto(int coll) {
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

	public static NumberRenderer decimalRender() {
		NumberFormat percentFormat = NumberFormat.getNumberInstance();
		percentFormat.setMinimumFractionDigits(2);

		return new NumberRenderer(percentFormat);
	}
	
	public static NumberRenderer CurrencyRender() {
		NumberFormat percentFormat = NumberFormat.getCurrencyInstance();
		percentFormat.setMinimumFractionDigits(2);

		return new NumberRenderer(percentFormat);
	}
}
