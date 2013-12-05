package br.edu.ifpr.comat.ui.components.panels.impl;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import br.edu.ifpr.comat.ui.components.panels.ComatJPanels;
import br.edu.ifpr.comat.ui.components.tables.TbModelItensOrcamento;
import br.edu.ifpr.comat.ui.components.tables.TbRenderBigDecimalOrc;
import br.edu.ifpr.comat.controller.OrcamentoController;
import br.edu.ifpr.comat.controller.ProdutoController;
import br.edu.ifpr.comat.model.Itensorcamento;
import br.edu.ifpr.comat.model.Orcamento;
import br.edu.ifpr.comat.model.Produto;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;

public class FormOrcamentoItens extends JPanel implements ComatJPanels,
		ActionListener {

	private JScrollPane contentPanel;
	private JPanel actionsPanel;
	private GridBagConstraints cons;

	private JButton btNovoItem, btDelItem;
	private JLabel lbValTotal;

	private JTable table;
	private TbModelItensOrcamento model;

	public FormOrcamentoItens() {
		setLayout(new BorderLayout(0, 0));
		//setLayout(new GridBagLayout());
		buildComponents();
	}

	private void buildComponents() {

		contentPanel = new JScrollPane();
		

		table = new JTable();		
		
		
		table.setPreferredSize(new Dimension(200,600));
		setModelTable(new TbModelItensOrcamento(criaItens()));
		// setModelTable(new TbModelItensOrcamento());

		table.setDefaultRenderer(BigDecimal.class, new TbRenderBigDecimalOrc());
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setShowHorizontalLines(true);
		table.setRowHeight(28);

		table.getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				// TODO Auto-generated method stub
				System.out.println(e);
			}
		});

		contentPanel.setViewportView(table);
		contentPanel.setPreferredSize(getMaximumSize());

		actionsPanel = new JPanel();

		btNovoItem = new JButton("Adicionar");
		btNovoItem.addActionListener(this);
		
		btDelItem = new JButton("Remover");
		btDelItem.addActionListener(this);

		JLabel lbValTotal = new JLabel("teste");

		JLabel lbTotal = new JLabel("Valor Total:");
		lbTotal.setFont(new Font("Tahoma", Font.BOLD, 11));

		GroupLayout gl_actionsPanel = new GroupLayout(actionsPanel);
		gl_actionsPanel.setHorizontalGroup(
			gl_actionsPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_actionsPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btNovoItem)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btDelItem)
					.addPreferredGap(ComponentPlacement.RELATED, 122, Short.MAX_VALUE)
					.addComponent(lbTotal)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lbValTotal, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_actionsPanel.setVerticalGroup(
			gl_actionsPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_actionsPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_actionsPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btNovoItem)
						.addComponent(btDelItem)
						.addComponent(lbValTotal)
						.addComponent(lbTotal))
					.addContainerGap(266, Short.MAX_VALUE))
		);
		actionsPanel.setLayout(gl_actionsPanel);

		add(BorderLayout.CENTER, contentPanel);
		add(BorderLayout.SOUTH, actionsPanel);
		
	}

	private List<Itensorcamento> criaItens() {
		List<Itensorcamento> itens = new ArrayList<Itensorcamento>();
		for (int i = 1; i <= 5; i++) {
			Itensorcamento it = new Itensorcamento();
			Produto p = new ProdutoController().search(1010);
			Orcamento o = new OrcamentoController().search(14);

			it.setProduto(p);
			it.setOrcamento(o);
			it.setPreco(p.getPrecoVenda());		

			itens.add(it);
		}
		return itens;
	}

	public void setModelTable(AbstractTableModel model) {
		table.setModel(model);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public JPanel getPanel() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public void setEnabledFields() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setDisableFields() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setCleanFields() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setLockFk() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setUnLockFk() {
		// TODO Auto-generated method stub

	}

}
