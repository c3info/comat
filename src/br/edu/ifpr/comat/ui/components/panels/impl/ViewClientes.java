package br.edu.ifpr.comat.ui.components.panels.impl;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.border.EtchedBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import br.edu.ifpr.comat.controller.ClienteController;
import br.edu.ifpr.comat.controller.ClienteFisicaController;
import br.edu.ifpr.comat.controller.ClienteJuridicaController;
import br.edu.ifpr.comat.ui.ComatMainFrame;
import br.edu.ifpr.comat.ui.components.panels.ComatJPanels;
import br.edu.ifpr.comat.ui.components.tables.TbModelClienteFisica;
import br.edu.ifpr.comat.ui.components.tables.TbModelClienteJuridica;
import br.edu.ifpr.comat.ui.components.tables.TbRenderCliente;

public class ViewClientes extends JPanel implements ComatJPanels, ActionListener {

	private ComatMainFrame frame;
	private JPanel topPanel, bottomPanel;
	private JScrollPane contentPanel;

	private ButtonGroup btGrPessoa;
	private JRadioButton rbtFisica, rbtJuridica;

	private JComboBox<String> cbxStatus;

	private JTable table;
	private TableRowSorter<TableModel> sorter;

	private JTextField txtSearch;
	
	private JButton btAcAdicionar, btAcEditar;

	public ViewClientes(ComatMainFrame frame) {
		this.frame = frame;
		setLayout(new BorderLayout(0, 0));
		buildComponents();
	}

	private void buildComponents() {
		topPanel = new JPanel();
		contentPanel = new JScrollPane();
		bottomPanel = new JPanel();

		topPanel.setBorder(new EtchedBorder());

		JLabel lbPessoa = new JLabel("Pessoa:");

		rbtFisica = new JRadioButton("Física");
		rbtJuridica = new JRadioButton("Jurídica");
		rbtFisica.setSelected(true);

		rbtFisica.addActionListener(this);
		rbtJuridica.addActionListener(this);

		btGrPessoa = new ButtonGroup();
		btGrPessoa.add(rbtFisica);
		btGrPessoa.add(rbtJuridica);

		cbxStatus = new JComboBox(new ClienteController().getClienteStatusVetString());
		cbxStatus.addItem("Qualquer");
		cbxStatus.setSelectedItem("Qualquer");
		cbxStatus.addActionListener(this);

		JLabel lblStatus = new JLabel("Status:");

		JLabel lblBuscar = new JLabel("Filtrar:");
		lblBuscar.setFont(new Font("Tahoma", Font.BOLD, 12));

		txtSearch = new JTextField();
		txtSearch.setToolTipText("Selecione uma coluna para definir o filtro");
		txtSearch.setColumns(10);
		txtSearch.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				newFilter();
			}
			public void insertUpdate(DocumentEvent e) {
				newFilter();
			}
			public void removeUpdate(DocumentEvent e) {
				newFilter();
			}
		});

		GroupLayout gl_topPanel = new GroupLayout(topPanel);
		gl_topPanel.setHorizontalGroup(
			gl_topPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_topPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lbPessoa)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(rbtFisica)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(rbtJuridica)
					.addGap(16)
					.addComponent(lblBuscar)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, 160, Short.MAX_VALUE)
					.addGap(16)
					.addComponent(lblStatus)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(cbxStatus, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_topPanel.setVerticalGroup(
			gl_topPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_topPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_topPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbPessoa)
						.addComponent(rbtFisica)
						.addComponent(rbtJuridica)
						.addComponent(cbxStatus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblStatus)
						.addComponent(lblBuscar)
						.addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		topPanel.setLayout(gl_topPanel);

		table = new JTable();
		setModelTable(new TbModelClienteFisica(new ClienteFisicaController().search()));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setShowHorizontalLines(true);
		table.setRowHeight(28);

		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				JTable tb = (JTable) me.getSource();
				Point p = me.getPoint();
				int row = tb.rowAtPoint(p);
				if (me.getClickCount() == 2) {	
					Integer id = (Integer) tb.getValueAt(row, 0);
					if (tb.getModel() instanceof TbModelClienteFisica) {						
						frame.setCurrentPanel(new FormCliente(id, 1));
					} else {						
						frame.setCurrentPanel(new FormCliente(id, 2));
					}
				} else if (me.getClickCount() == 1){					
					if(tbSelectedRow() == -1){ 
						btAcEditar.setEnabled(false);
					} else { 
						btAcEditar.setEnabled(true);
					}
				}
			}
		});

		contentPanel.setViewportView(table);
		add(BorderLayout.NORTH, topPanel);
		add(BorderLayout.CENTER, contentPanel);		
		
		JPanel actionsPanel = new JPanel();
		actionsPanel.setBorder(new EtchedBorder());
		actionsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));		
		
		btAcAdicionar = new JButton("");
		btAcAdicionar.setToolTipText("Adicionar");
		btAcAdicionar.setPreferredSize(new Dimension(24, 16));
		btAcAdicionar.setIcon(new ImageIcon(ViewClientes.class.getResource("/br/edu/ifpr/comat/ui/images/new_page.png")));
		btAcAdicionar.setContentAreaFilled(false);
		btAcAdicionar.addActionListener(this);
		actionsPanel.add(btAcAdicionar);

		btAcEditar = new JButton("");
		btAcEditar.setToolTipText("Editar");
		btAcEditar.setPreferredSize(new Dimension(24, 16));
		btAcEditar.setIcon(new ImageIcon(ViewClientes.class.getResource("/br/edu/ifpr/comat/ui/images/zoom_in.png")));
		btAcEditar.setContentAreaFilled(false);
		btAcEditar.addActionListener(this);
		btAcEditar.setEnabled(false);
		actionsPanel.add(btAcEditar);		

		add(BorderLayout.SOUTH, actionsPanel);
	}

	private int tbSelectedRow() {
		int rowSelected = table.getSelectedRow();
		return rowSelected;
	}
	
	private void newFilter() {
		RowFilter<TableModel, Object> rf = null;
		try {
			rf = RowFilter.regexFilter("(?i)" + txtSearch.getText());
		} catch (java.util.regex.PatternSyntaxException e) {
			return;
		}
		sorter.setRowFilter(rf);
	}

	public void setModelTable(AbstractTableModel model) {

		table.setModel(model);

		if (model.getRowCount() >= 1) {
			sorter = new TableRowSorter<TableModel>(model);
			table.setRowSorter(sorter);
			txtSearch.setEnabled(true);
		} else {
			table.setRowSorter(null);
			txtSearch.setEnabled(false);
		}

		table.setDefaultRenderer(Object.class, new TbRenderCliente(9));

		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(0).setPreferredWidth(0);
		table.getColumnModel().getColumn(1).setPreferredWidth(10);
		table.getColumnModel().getColumn(2).setPreferredWidth(120);
		table.getColumnModel().getColumn(3).setPreferredWidth(60);
		table.getColumnModel().getColumn(4).setPreferredWidth(10);
		table.getColumnModel().getColumn(5).setPreferredWidth(10);
		table.getColumnModel().getColumn(6).setPreferredWidth(120);
		table.getColumnModel().getColumn(7).setPreferredWidth(30);
		table.getColumnModel().getColumn(8).setPreferredWidth(20);
		table.getColumnModel().getColumn(9).setMinWidth(0);
		table.getColumnModel().getColumn(9).setMaxWidth(0);
		table.getColumnModel().getColumn(9).setPreferredWidth(0);
	}

	@Override
	public JPanel getPanel() {
		return this;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cbxStatus) {
			JComboBox cb = (JComboBox) e.getSource();
			String status = (String) cb.getSelectedItem();
			int selectid = cb.getSelectedIndex();

			if (status.equals("Ativo")) {
				actionUpdateModel(selectid);
				
			} else if (status.equals("Inativo")) {
				actionUpdateModel(selectid);
				
			} else if (status.equals("Bloqueado")) {
				actionUpdateModel(selectid);
				
			} else if (status.equals("Qualquer")) {				
				if (rbtFisica.isSelected()) {
					setModelTable(new TbModelClienteFisica(new ClienteFisicaController().search()));
					
				} else {
					setModelTable(new TbModelClienteJuridica(new ClienteJuridicaController().search()));
				}
			} 			

		} else {
			if (e.getSource() == rbtFisica) {
				cbxStatus.setSelectedItem("Qualquer");
				setModelTable(new TbModelClienteFisica(new ClienteFisicaController().search()));
				txtSearch.setText("");

			} else if (e.getSource() == rbtJuridica) {
				cbxStatus.setSelectedItem("Qualquer");
				setModelTable(new TbModelClienteJuridica(new ClienteJuridicaController().search()));
				txtSearch.setText("");
				
			} else if (e.getSource() == btAcAdicionar) {
				frame.setCurrentPanel(new FormCliente());
				
			} else if (e.getSource() == btAcEditar) {				
				int id =(Integer) table.getValueAt(tbSelectedRow(), 0);
				
				if (table.getModel() instanceof TbModelClienteFisica) {						
					frame.setCurrentPanel(new FormCliente(id, 1));
				} else {						
					frame.setCurrentPanel(new FormCliente(id, 2));
				}
			}
		}
	}

	private void actionUpdateModel(int i) {
		
		if (rbtFisica.isSelected()) {
			setModelTable(new TbModelClienteFisica(new ClienteFisicaController().searchStatus(i)));
		} else {
			setModelTable(new TbModelClienteJuridica(new ClienteJuridicaController().searchStatus(i)));
		}
	}

	@Override
	public void setEnabledFields() {
		cbxStatus.setEnabled(true);
	}

	@Override
	public void setDisableFields() {
		cbxStatus.setEnabled(false);
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
