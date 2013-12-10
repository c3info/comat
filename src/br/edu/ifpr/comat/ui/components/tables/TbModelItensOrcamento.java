package br.edu.ifpr.comat.ui.components.tables;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.edu.ifpr.comat.model.ItenOrcamento;

public class TbModelItensOrcamento extends AbstractTableModel {

	private static final int COL_REF = 0;
	private static final int COL_NOME = 1;
	private static final int COL_UND = 2;
	private static final int COL_QUANT = 3;
	private static final int COL_PRECO = 4;
	private static final int COL_SUBTOTAL = 5;
	private static final int COL_DESCONTO = 6;
	private static final int COL_TOTAL = 7;

	private List<ItenOrcamento> rows;

	private String[] columns = new String[] { "Ref.", "Nome", "Unidade",
			"Quant.", "Preço", "Subtotal", "Desconto %", "Total" };

	public TbModelItensOrcamento() {
		this.rows = new ArrayList<>();
	}

	public TbModelItensOrcamento(List<ItenOrcamento> itens) {
		this.rows = itens;
	}

	@Override
	public int getRowCount() {
		return rows.size();
	}

	@Override
	public int getColumnCount() {
		return columns.length;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return columns[columnIndex];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case COL_REF:
			return Integer.class;
		case COL_NOME:
			return String.class;
		case COL_UND:
			return String.class;
		case COL_QUANT:
			return Integer.class;
		case COL_PRECO:
			return Double.class;
		case COL_SUBTOTAL:
			return BigDecimal.class;
		case COL_DESCONTO:
			return Double.class;
		case COL_TOTAL:
			return BigDecimal.class;

		default:
			throw new IndexOutOfBoundsException(
					"Índice informado fora dos limites.");
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		ItenOrcamento item = rows.get(rowIndex);
		
		switch (columnIndex) {

		case COL_REF:
			return item.getProduto().getRefProduto();
			
		case COL_NOME:
			return item.getProduto().getNome();
			
		case COL_UND:
			return item.getProduto().getUnidade();
			
		case COL_QUANT:
			return item.getQuantidade();
			
		case COL_PRECO:
			if(item.getPreco() == null) return item.getProduto().getPrecoVenda();
			else return item.getPreco();
			
		case COL_SUBTOTAL:
			if(item.getPreco() == null) return new BigDecimal(item.getQuantidade() * item.getProduto().getPrecoVenda());
			else return new BigDecimal(item.getQuantidade() * item.getPreco());		
			
		case COL_DESCONTO:
			return item.getDesconto();
		
		case COL_TOTAL:
			if(item.getPreco() == null) return new BigDecimal(item.getQuantidade() * item.getProduto().getPrecoVenda() - ((item.getQuantidade() * item.getProduto().getPrecoVenda() / 100) * item.getDesconto()));
			else return new BigDecimal(item.getQuantidade() * item.getPreco() - ((item.getQuantidade() * item.getPreco() / 100) * item.getDesconto()));
		
		default:
			throw new IndexOutOfBoundsException(
					"Índice informado fora dos limites.");

		}
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		ItenOrcamento item = rows.get(rowIndex);

		switch (columnIndex) {

		case COL_QUANT:
			item.setQuantidade((Integer) aValue);
			break;
		case COL_DESCONTO:
			if ((Double) aValue <= item.getProduto().getDescontoMax()) {
				item.setDesconto((Double) aValue);
			} else {
				item.setDesconto(item.getProduto().getDescontoMax());
			}
			break;
		default:
			throw new IndexOutOfBoundsException(
					"Índice informado fora dos limites.");
		}
		fireTableCellUpdated(rowIndex, columnIndex);
		fireTableCellUpdated(rowIndex, 5);
		fireTableCellUpdated(rowIndex, 7);

	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if (columnIndex == COL_QUANT)
			return true;
		if (columnIndex == COL_DESCONTO)
			return true;
		else
			return false;
	}

	public ItenOrcamento getItens(int rowIndex) {
		return rows.get(rowIndex);
	}

	public void addIten(ItenOrcamento item) {
		rows.add(item);
		int ultimoIndice = getRowCount() - 1;
		fireTableRowsInserted(ultimoIndice, ultimoIndice);
	}

	public void removeIten(int rowIndex) {
		rows.remove(rowIndex);
		fireTableRowsDeleted(rowIndex, rowIndex);
	}

	public void addListItens(List<ItenOrcamento> itens) {
		int indice = getRowCount();
		rows.addAll(itens);

		fireTableRowsInserted(indice, indice + itens.size());
	}	

	public List<ItenOrcamento> getRows() {
		return rows;
	}

	public void limpar() {
		rows.clear();
		fireTableDataChanged();
	}

}
