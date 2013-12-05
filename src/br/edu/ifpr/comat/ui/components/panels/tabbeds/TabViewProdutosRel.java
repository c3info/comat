package br.edu.ifpr.comat.ui.components.panels.tabbeds;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import br.edu.ifpr.comat.controller.ProdutoController;
import br.edu.ifpr.comat.ui.components.tables.TbModelProdutoRel;

public class TabViewProdutosRel extends JPanel implements ActionListener {

	private Integer idProduto = null;
	private JTable table;
	private JButton btAdicionar, btEditar, btExcluir;

	public TabViewProdutosRel() {
		setLayout(new BorderLayout(0, 0));
		buildComponents();
		stop();
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

		btEditar = new JButton("");
		btEditar.setToolTipText("Editar");
		btEditar.setPreferredSize(new Dimension(24, 16));
		btEditar.setIcon(new ImageIcon(TabViewProdutosRel.class
				.getResource("/br/edu/ifpr/comat/ui/images/pencil.png")));
		btEditar.setContentAreaFilled(false);
		btEditar.addActionListener(this);
		bottomPanel.add(btEditar);

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
		loadModelTable();

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

	public void loadModelTable() {
		table.setModel(new TbModelProdutoRel(new ProdutoController()
				.searchRel()));

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
		btEditar.setEnabled(false);
		btExcluir.setEnabled(false);
		table.setEnabled(true);
	}

	public void stop() {
		btAdicionar.setEnabled(false);
		btEditar.setEnabled(false);
		btExcluir.setEnabled(false);
		table.setEnabled(false);
	}

	private void edit() {
		btEditar.setEnabled(true);
		btExcluir.setEnabled(true);
	}

	private void delete() {
		Integer id = (Integer) table.getValueAt(tbSelectedRow(), 0);
		String nome = (String) table.getValueAt(tbSelectedRow(), 1);

		int excluir = JOptionPane.showConfirmDialog(null,
				"Deseja excluir a obra " + nome + "?", "Pedido de Exclusão",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (excluir == JOptionPane.YES_OPTION) {
			// new ObraController().delete(id);
			btEditar.setEnabled(false);
			btExcluir.setEnabled(false);
			loadModelTable();
		}
	}

	public Integer getIdCliente() {
		return idProduto;
	}

	public void setIdCliente(Integer idCliente) {
		this.idProduto = idCliente;
	}

	private void observerInsert() {
		java.awt.EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				// FormObra obra = new FormObra();
				// obra.setInsert(true);
				// obra.setIdCli(idProduto);
				// obra.setModal(true);
				// obra.setVisible(true);
				loadModelTable();
			}
		});
	}

	private void observerEdit() {
		java.awt.EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				// FormObra obra = new FormObra((Integer) table.getValueAt(
				// tbSelectedRow(), 0));
				// obra.setIdCli(idProduto);
				// obra.setModal(true);
				// obra.setVisible(true);
				// loadModelTable();
			}
		});
	}

	private int tbSelectedRow() {
		int rowSelected = table.getSelectedRow();
		return rowSelected;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btAdicionar) {
			observerInsert();

		} else if (e.getSource() == btEditar) {
			observerEdit();

		} else if (e.getSource() == btExcluir) {
			delete();
		}
	}
}
