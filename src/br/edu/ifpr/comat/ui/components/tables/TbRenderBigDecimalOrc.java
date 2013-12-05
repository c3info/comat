package br.edu.ifpr.comat.ui.components.tables;

import java.awt.Component;
import java.math.BigDecimal;
import java.text.NumberFormat;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class TbRenderBigDecimalOrc extends DefaultTableCellRenderer{

	@Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
                row, column);
         
        Object val = table.getValueAt(row, column);       
        if (val instanceof BigDecimal) {
            
        	setHorizontalAlignment(SwingConstants.RIGHT);
            BigDecimal valor = (BigDecimal) val;
           
            NumberFormat nf = NumberFormat.getCurrencyInstance();
            setText(valor != null ? nf.format(valor) : "");
        }
         
        return this;
    }
}
