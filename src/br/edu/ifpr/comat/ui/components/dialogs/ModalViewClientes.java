package br.edu.ifpr.comat.ui.components.dialogs;


import java.awt.BorderLayout;
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
import javax.swing.JButton;
import javax.swing.JDialog;
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

import br.edu.ifpr.comat.controller.ClienteFisicaController;
import br.edu.ifpr.comat.controller.ClienteJuridicaController;
import br.edu.ifpr.comat.ui.components.tables.TbModelClienteFisica;
import br.edu.ifpr.comat.ui.components.tables.TbModelClienteJuridica;
import br.edu.ifpr.comat.ui.components.tables.TbRenderCliente;

public class ModalViewClientes extends JDialog implements ActionListener{
	
	private Integer idCli = null;
	
	private JPanel topPanel;
	private JScrollPane contentPanel;

	private ButtonGroup btGrPessoa;
	private JRadioButton rbtFisica, rbtJuridica;

	private JTable table;
	private TableRowSorter<TableModel> sorter;

	private JTextField txtSearch;
	
	private JButton btRecuperar, btCancelar;

	public ModalViewClientes() {
		buildComponents();	
		btRecuperar.setEnabled(false);
	}

	private void buildComponents() {
		setResizable(false);
		setTitle("Buscar clientes");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 780, 450);
		getContentPane().setLayout(new BorderLayout());

		topPanel = new JPanel();
		contentPanel = new JScrollPane();	

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
					.addGap(12))
		);
		gl_topPanel.setVerticalGroup(
			gl_topPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_topPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_topPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbPessoa)
						.addComponent(rbtFisica)
						.addComponent(rbtJuridica)
						.addComponent(lblBuscar)
						.addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		topPanel.setLayout(gl_topPanel);

		table = new JTable();
		setModelTable(new TbModelClienteFisica(new ClienteFisicaController().searchStatus(0)));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setShowHorizontalLines(true);
		table.setRowHeight(28);

		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				JTable tb = (JTable) me.getSource();
				Point p = me.getPoint();				
				if (me.getClickCount() == 1){					
					if(tbSelectedRow() == -1){ 
						if(idCli != null) idCli = null;
						btRecuperar.setEnabled(false);						
					} else { 
						idCli = (Integer) tb.getValueAt(tbSelectedRow(), 0);
						btRecuperar.setEnabled(true);
					}
				}
			}
		});

		contentPanel.setViewportView(table);
		getContentPane().add(BorderLayout.NORTH, topPanel);
		getContentPane().add(BorderLayout.CENTER, contentPanel);		
		
		JPanel actionsPanel = new JPanel();
		actionsPanel.setBorder(new EtchedBorder());
		actionsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));		
		
		btRecuperar = new JButton("Recuperar");
		btRecuperar.setToolTipText("Recuperar");	
		btRecuperar.addActionListener(this);
		actionsPanel.add(btRecuperar);

		btCancelar = new JButton("Cancelar");
		btCancelar.setToolTipText("Cancelar");		
		btCancelar.addActionListener(this);		
		actionsPanel.add(btCancelar);		

		getContentPane().add(BorderLayout.SOUTH, actionsPanel);

	}

	private int tbSelectedRow() {
		int rowSelected = table.getSelectedRow();
		return rowSelected;
	}
	
	private void newFilter() {
		btRecuperar.setEnabled(false);
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
		
		table.getColumnModel().getColumn(6).setMinWidth(0);
		table.getColumnModel().getColumn(6).setMaxWidth(0);
		table.getColumnModel().getColumn(6).setPreferredWidth(0);		
		
		table.getColumnModel().getColumn(7).setPreferredWidth(30);
		table.getColumnModel().getColumn(8).setPreferredWidth(20);
		table.getColumnModel().getColumn(9).setMinWidth(0);
		table.getColumnModel().getColumn(9).setMaxWidth(0);
		table.getColumnModel().getColumn(9).setPreferredWidth(0);
	}


	public void actionPerformed(ActionEvent e) { 
		if (e.getSource() == rbtFisica) {
			actionUpdateModel();

		} else if (e.getSource() == rbtJuridica) {
			actionUpdateModel();
			
		} else if (e.getSource() == btRecuperar) {
			dispose();	
			
		} else if (e.getSource() == btCancelar) {
			idCli = null;
			dispose();
		}
	}

	private void actionUpdateModel() {	
		btRecuperar.setEnabled(false);
		if (rbtFisica.isSelected()) {
			setModelTable(new TbModelClienteFisica(new ClienteFisicaController().searchStatus(0)));
		} else {
			setModelTable(new TbModelClienteJuridica(new ClienteJuridicaController().searchStatus(0)));
		}
	}

	public Integer getIdCli() {
		return idCli;
	}		
}
