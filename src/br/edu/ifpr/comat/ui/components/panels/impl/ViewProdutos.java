package br.edu.ifpr.comat.ui.components.panels.impl;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.border.EtchedBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JComboBox;

import br.edu.ifpr.comat.controller.CategoriaController;
import br.edu.ifpr.comat.controller.ProdutoController;
import br.edu.ifpr.comat.ui.ComatMainFrame;
import br.edu.ifpr.comat.ui.components.panels.ComatJPanels;
import br.edu.ifpr.comat.ui.components.tables.TbModelProduto;
import br.edu.ifpr.comat.ui.components.tables.TbRenderCliente;
import br.edu.ifpr.comat.ui.components.tables.TbRenderProduto;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import resources.NumberRenderer;

import java.awt.Font;

public class ViewProdutos extends JPanel implements ComatJPanels ,
ActionListener {	

	private ComatMainFrame frame;
	
	private JPanel topPanel, bottomPanel;
	private JScrollPane contentPanel;

	private JComboBox<String> cbxCategoria, cbxStatus;
	
	private JTable table;
	private TableRowSorter<TableModel> sorter;	
	
	private JTextField txtSearch;
	
	public ViewProdutos(ComatMainFrame frame) {
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
		txtSearch.getDocument().addDocumentListener(
                new DocumentListener() {
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
		cbxStatus = new JComboBox<String>(new ProdutoController().getStatusList());
		cbxStatus.addItem("Qualquer");
		cbxStatus.setSelectedItem("Qualquer");
		cbxStatus.addActionListener(this);		
		
		JLabel lblCategorias = new JLabel("Categoria:");
		cbxCategoria = new JComboBox<String>(new CategoriaController().getCategoriasList());
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
					.addGap(96)
					.addComponent(lblCategorias)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(cbxCategoria, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
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
						.addComponent(lblCategorias)
						.addComponent(cbxCategoria, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblStatus)
						.addComponent(cbxStatus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		topPanel.setLayout(grtopPanel);
	
		table = new JTable();
		setModelTable(new TbModelProduto(new ProdutoController().search()));		
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setShowHorizontalLines(true);		
		table.setRowHeight(28);
		
		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
		        JTable tb =(JTable) me.getSource();
		        Point p = me.getPoint();
		        int row = tb.rowAtPoint(p);
		        if (me.getClickCount() == 2) {
		        	int rowSelected = tb.getSelectedRow();			        	
		        	Integer ref =  (Integer) tb.getValueAt(rowSelected, 0);			        	
		        	frame.setCurrentPanel(new FormProduto(ref));    				       		
		        }
		    }
		});
				
		contentPanel.setViewportView(table);
		add(BorderLayout.NORTH, topPanel);
		add(BorderLayout.CENTER, contentPanel);			
	    // add(BorderLayout.SOUTH, bottomPanel);		
		// contentPanel.setPreferredSize(new Dimension(760, 40));	
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
	
	public void setModelTable(AbstractTableModel model){		
		
		table.setModel(model);		
				
		if(model.getRowCount() >= 1){
			sorter = new TableRowSorter<TableModel>(model);
			table.setRowSorter(sorter);
			txtSearch.setEnabled(true);
		} else {
			txtSearch.setEnabled(false);
			table.setRowSorter(null);			
		}
		
		TableColumnModel m = table.getColumnModel();	
		m.getColumn(5).setCellRenderer(TbRenderProduto.CurrencyRender());
		m.getColumn(6).setCellRenderer(TbRenderProduto.decimalRender());
			
		table.setDefaultRenderer(Object.class, new TbRenderProduto(7));
		
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
	
	@Override
	public JPanel getPanel() {
		return this;
	}

	public void actionPerformed(ActionEvent e) {	
		if(e.getSource() instanceof JComboBox){
			actionUpdateModel();
		}				
	}
	
	private void actionUpdateModel(){
		String cat = (String) cbxCategoria.getSelectedItem();
		String sts = (String) cbxStatus.getSelectedItem();
		String def = "Qualquer";
		
		if(cat.equals(def) && sts.equals(def)){			
			setModelTable(new TbModelProduto(new ProdutoController().search()));		
			
		} else if (cat.equals(def)){			
			setModelTable(new TbModelProduto(new ProdutoController().searchStatus(sts)));
			
		} else if (sts.equals(def)){			
			setModelTable(new TbModelProduto(new ProdutoController().searchCategoria(cat)));
		
		} else {					
			setModelTable(new TbModelProduto(new ProdutoController().searchStatusCategoria(sts,cat)));
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
