package br.edu.ifpr.comat.ui.components.dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
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

import br.edu.ifpr.comat.controller.CategoriaController;
import br.edu.ifpr.comat.controller.ProdutoController;
import br.edu.ifpr.comat.ui.components.tables.TbModelProduto;
import br.edu.ifpr.comat.ui.components.tables.TbRenderProduto;

public class ModalViewProdutos extends JDialog implements ActionListener {

	private Integer refProd = null;
	
	private JPanel topPanel;
	private JScrollPane contentPanel;

	private JComboBox<String> cbxCategoria;

	private JTable table;
	private TableRowSorter<TableModel> sorter;

	private JTextField txtSearch;
	
	private JButton btRecuperar, btCancelar;
	
	public ModalViewProdutos() {
		buildComponents();
		btRecuperar.setEnabled(false);
	}

	private void buildComponents() {
		setResizable(false);
		setTitle("Buscar produtos");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 780, 450);
		getContentPane().setLayout(new BorderLayout());		
		
		topPanel = new JPanel();
		contentPanel = new JScrollPane();
		
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

		JLabel lblCategorias = new JLabel("Categoria:");
		cbxCategoria = new JComboBox(new CategoriaController().getCategoriasVetString());
		cbxCategoria.addItem("Qualquer");
		cbxCategoria.setSelectedItem("Qualquer");
		cbxCategoria.addActionListener(this);

		GroupLayout grtopPanel = new GroupLayout(topPanel);
		grtopPanel.setHorizontalGroup(
			grtopPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(grtopPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblBuscar)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, 160, Short.MAX_VALUE)
					.addGap(16)
					.addComponent(lblCategorias)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(cbxCategoria, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
					.addGap(6))
		);
		grtopPanel.setVerticalGroup(
			grtopPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(grtopPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(grtopPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBuscar)
						.addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCategorias)
						.addComponent(cbxCategoria, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		topPanel.setLayout(grtopPanel);

		table = new JTable();
		setModelTable(new TbModelProduto(new ProdutoController().searchStatus("Disponível")));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setShowHorizontalLines(true);
		table.setRowHeight(28);

		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				JTable tb = (JTable) me.getSource();
				Point p = me.getPoint();				
				if (me.getClickCount() == 1){					
					if(tbSelectedRow() == -1){ 
						if(refProd != null) refProd = null;
						btRecuperar.setEnabled(false);						
					} else { 
						refProd = (Integer) tb.getValueAt(tbSelectedRow(), 0);
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
			txtSearch.setEnabled(false);
			table.setRowSorter(null);
		}

		table.setDefaultRenderer(Object.class, new TbRenderProduto(7));		

		table.getColumnModel().getColumn(0).setPreferredWidth(40);
		table.getColumnModel().getColumn(1).setPreferredWidth(380);
		
		table.getColumnModel().getColumn(2).setMinWidth(0);
		table.getColumnModel().getColumn(2).setMaxWidth(0);
		table.getColumnModel().getColumn(2).setPreferredWidth(0);
		
		table.getColumnModel().getColumn(3).setPreferredWidth(40);
		table.getColumnModel().getColumn(4).setPreferredWidth(40);
		table.getColumnModel().getColumn(5).setPreferredWidth(80);
		table.getColumnModel().getColumn(6).setPreferredWidth(40);
		table.getColumnModel().getColumn(7).setMinWidth(0);
		table.getColumnModel().getColumn(7).setMaxWidth(0);
		table.getColumnModel().getColumn(7).setPreferredWidth(0);
	}

	private void actionUpdateModel() {
		btRecuperar.setEnabled(false);
		String cat = (String) cbxCategoria.getSelectedItem();		
		String def = "Qualquer";
		
		 if (cat.equals(def)) {
			 setModelTable(new TbModelProduto(new ProdutoController().searchStatus("Disponível")));
		 } else {
			 setModelTable(new TbModelProduto(new ProdutoController().searchStatusCategoria("Disponível", cat)));
		 }		

	}	

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cbxCategoria){
			actionUpdateModel();
		}
		else if (e.getSource() == btRecuperar) {
			dispose();	
			
		} else if (e.getSource() == btCancelar) {
			refProd = null;
			dispose();
		}
	}
	
	public Integer getRefProd() {
		return refProd;
	}
	
}
