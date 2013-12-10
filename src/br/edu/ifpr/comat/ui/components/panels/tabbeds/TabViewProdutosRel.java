package br.edu.ifpr.comat.ui.components.panels.tabbeds;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;

import br.edu.ifpr.comat.controller.ProdutoRelacionadoController;
import br.edu.ifpr.comat.model.Produto;
import br.edu.ifpr.comat.ui.components.dialogs.ModalViewProdutosRel;
import br.edu.ifpr.comat.ui.components.tables.TbModelProdutoRelacionado;

public class TabViewProdutosRel extends JPanel implements ActionListener {

	private Integer idProduto = null;
	private JTable table;
	private JButton btAdicionar, btExcluir;
	private TbModelProdutoRelacionado model;

	public TabViewProdutosRel() {
		setLayout(new BorderLayout(0, 0));
		buildComponents();
	}

	private void buildComponents() {
		JScrollPane scrollPane = new JScrollPane();
		JPanel bottomPanel = new JPanel();

		bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

		btAdicionar = new JButton("");
		btAdicionar.setToolTipText("Adicionar");
		btAdicionar.setPreferredSize(new Dimension(24, 16));
		btAdicionar.setIcon(new ImageIcon(TabViewProdutosRel.class
				.getResource("/br/edu/ifpr/comat/ui/images/add.png")));
		btAdicionar.setContentAreaFilled(false);
		btAdicionar.addActionListener(this);
		bottomPanel.add(btAdicionar);

		btExcluir = new JButton("");
		btExcluir.setToolTipText("Excluir");
		btExcluir.setPreferredSize(new Dimension(24, 16));
		btExcluir.setIcon(new ImageIcon(TabViewProdutosRel.class
				.getResource("/br/edu/ifpr/comat/ui/images/delete.png")));
		btExcluir.setContentAreaFilled(false);
		btExcluir.addActionListener(this);
		bottomPanel.add(btExcluir);

		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setShowHorizontalLines(true);
		table.setRowHeight(28);
		model = new TbModelProdutoRelacionado();
		loadModelTable(model);

		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				JTable tb = (JTable) me.getSource();
				Point p = me.getPoint();
				int row = tb.rowAtPoint(p);

				if (me.getClickCount() == 1) {
					if (table.isEnabled()) {
						edit();
					}
				}
			}
		});

		scrollPane.setViewportView(table);

		add(scrollPane, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);
	}

	public void loadModelTable(AbstractTableModel model) {
		table.setModel(model);

		table.getColumnModel().getColumn(0).setPreferredWidth(40);
		table.getColumnModel().getColumn(1).setPreferredWidth(380);
		table.getColumnModel().getColumn(2).setPreferredWidth(180);
		table.getColumnModel().getColumn(3).setPreferredWidth(40);
		table.getColumnModel().getColumn(4).setPreferredWidth(40);
		table.getColumnModel().getColumn(5).setPreferredWidth(80);
		table.getColumnModel().getColumn(6).setPreferredWidth(40);
		table.getColumnModel().getColumn(7).setMinWidth(0);
		table.getColumnModel().getColumn(7).setMaxWidth(0);
		table.getColumnModel().getColumn(7).setPreferredWidth(0);
	}

	public void start() {
		btAdicionar.setEnabled(true);
		btExcluir.setEnabled(false);
		table.setEnabled(true);
	}

	public void stop() {
		btAdicionar.setEnabled(false);
		btExcluir.setEnabled(false);
		table.setEnabled(false);
	}

	private void edit() {
		btExcluir.setEnabled(true);
	}

	private void observerInsert() {
		java.awt.EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				ModalViewProdutosRel modal = new ModalViewProdutosRel();

				modal.setModal(true);
				modal.setVisible(true);

				System.out.println(modal.getModel().getSelect());
				System.out.println(modal.getModel().getRows());

				relacionaItens(modal.getModel().getSelect(), modal.getModel()
						.getRows());
			}
		});
	}

	private void relacionaItens(boolean[] selecionados, List<Produto> lista) {
		
		model.addListItens(new ProdutoRelacionadoController()
				.processaRelacionamento(selecionados, lista));
	}

	private int tbSelectedRow() {
		int rowSelected = table.getSelectedRow();
		return rowSelected;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btAdicionar) {
			observerInsert();
		} else if (e.getSource() == btExcluir) {

			if (tbSelectedRow() != -1) {
				model.removeIten(tbSelectedRow());
			} else {
				JOptionPane.showMessageDialog(null, "Selecione uma linha!");
			}

		}
	}

	public Integer getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}

	public TbModelProdutoRelacionado getModel() {
		return model;
	}

	public void setModel(AbstractTableModel model) {
		this.model = (TbModelProdutoRelacionado) model;
	}

}
