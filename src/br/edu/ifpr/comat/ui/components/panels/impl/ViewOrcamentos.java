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

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
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

import br.edu.ifpr.comat.controller.OrcamentoController;
import br.edu.ifpr.comat.ui.ComatMainFrame;
import br.edu.ifpr.comat.ui.components.panels.ComatJPanels;
import br.edu.ifpr.comat.ui.components.tables.TbModelOrcamentos;
import br.edu.ifpr.comat.ui.components.tables.TbRenderOrcamento;

public class ViewOrcamentos extends JPanel implements ComatJPanels, ActionListener {

	private ComatMainFrame frame;
	private JPanel topPanel, bottomPanel;
	private JScrollPane contentPanel;

	private JComboBox<String> cbxStatus;

	private JTable table;
	private TableRowSorter<TableModel> sorter;

	private JTextField txtSearch;
	private JButton btAcAdicionar, btAcEditar;

	public ViewOrcamentos(ComatMainFrame frame) {
		this.frame = frame;
		setLayout(new BorderLayout(0, 0));
		buildComponents();
	}

	private void buildComponents() {
		topPanel = new JPanel();
		contentPanel = new JScrollPane();
		bottomPanel = new JPanel();

		topPanel.setBorder(new EtchedBorder());

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

		JLabel lblStatus = new JLabel("Status:");
		cbxStatus = new JComboBox(new OrcamentoController().getStatusList());
		cbxStatus.addItem("Qualquer");
		cbxStatus.setSelectedItem("Qualquer");
		cbxStatus.addActionListener(this);

		GroupLayout grtopPanel = new GroupLayout(topPanel);
		grtopPanel.setHorizontalGroup(
			grtopPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(grtopPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblBuscar)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, 160, Short.MAX_VALUE)
					.addGap(16)
					.addComponent(lblStatus)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(cbxStatus, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		grtopPanel.setVerticalGroup(
			grtopPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(grtopPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(grtopPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBuscar)
						.addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblStatus)
						.addComponent(cbxStatus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		topPanel.setLayout(grtopPanel);

		table = new JTable();
		setModelTable(new TbModelOrcamentos(new OrcamentoController().search()));
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
					frame.setCurrentPanel(new FormOrcamento(id));
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
		btAcAdicionar.setIcon(new ImageIcon(ViewOrcamentos.class.getResource("/br/edu/ifpr/comat/ui/images/new_page.png")));
		btAcAdicionar.setContentAreaFilled(false);
		btAcAdicionar.addActionListener(this);
		actionsPanel.add(btAcAdicionar);

		btAcEditar = new JButton("");
		btAcEditar.setToolTipText("Editar");
		btAcEditar.setPreferredSize(new Dimension(24, 16));
		btAcEditar.setIcon(new ImageIcon(ViewOrcamentos.class.getResource("/br/edu/ifpr/comat/ui/images/zoom_in.png")));
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
			txtSearch.setEnabled(false);
			table.setRowSorter(null);
		}

		table.setDefaultRenderer(Object.class, new TbRenderOrcamento(7));	
		
		table.getColumnModel().getColumn(0).setPreferredWidth(40);
		table.getColumnModel().getColumn(1).setPreferredWidth(70);
		table.getColumnModel().getColumn(2).setPreferredWidth(360);
		table.getColumnModel().getColumn(3).setPreferredWidth(160);
		table.getColumnModel().getColumn(4).setPreferredWidth(140);
		table.getColumnModel().getColumn(5).setPreferredWidth(90);
		table.getColumnModel().getColumn(6).setPreferredWidth(70);
		table.getColumnModel().getColumn(7).setMinWidth(0);
		table.getColumnModel().getColumn(7).setMaxWidth(0);
		table.getColumnModel().getColumn(7).setPreferredWidth(0);		
	}

	@Override
	public JPanel getPanel() {
		return this;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cbxStatus) {
			actionUpdateModel();
			
		}  else if (e.getSource() == btAcAdicionar) {
			frame.setCurrentPanel(new FormOrcamento());
			
		}  else if (e.getSource() == btAcEditar) {
			frame.setCurrentPanel(new FormOrcamento((Integer) table.getValueAt(tbSelectedRow(), 0)));
			
		} 
	}

	private void actionUpdateModel() {
		int status = cbxStatus.getSelectedIndex();		
		if(status == 3){
			setModelTable(new TbModelOrcamentos(new OrcamentoController().search()));
		} else {
			setModelTable(new TbModelOrcamentos(new OrcamentoController().searchStatus(status)));
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
