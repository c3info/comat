package br.edu.ifpr.comat.ui.components.tables;

import java.awt.Component;
import java.math.BigDecimal;
import java.text.NumberFormat;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class TbRenderDecimalOrc extends DefaultTableCellRenderer{

	@Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
                row, column);
         
        Object val = table.getValueAt(row, column);       
        if (val instanceof Double) {
        	
        	setHorizontalAlignment(SwingConstants.RIGHT);
        	Double valor = (Double) val;
           
            NumberFormat nf = NumberFormat.getNumberInstance();
            nf.setMinimumFractionDigits(2);
            setText(valor != null ? nf.format(valor) : "");
        }
         
        return this;
    }
}
