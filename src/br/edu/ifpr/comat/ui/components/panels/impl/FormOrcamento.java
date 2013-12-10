package br.edu.ifpr.comat.ui.components.panels.impl;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

import br.edu.ifpr.comat.controller.ClienteController;
import br.edu.ifpr.comat.controller.ItenOrcamentoController;
import br.edu.ifpr.comat.controller.ObraController;
import br.edu.ifpr.comat.controller.OrcamentoController;
import br.edu.ifpr.comat.controller.ProdutoController;
import br.edu.ifpr.comat.model.Cliente;
import br.edu.ifpr.comat.model.ClienteFisica;
import br.edu.ifpr.comat.model.ClienteJuridica;
import br.edu.ifpr.comat.model.ItenOrcamento;
import br.edu.ifpr.comat.model.Obra;
import br.edu.ifpr.comat.model.Orcamento;
import br.edu.ifpr.comat.model.Produto;
import br.edu.ifpr.comat.ui.components.dialogs.ModalFormCliente;
import br.edu.ifpr.comat.ui.components.dialogs.ModalFormObra;
import br.edu.ifpr.comat.ui.components.dialogs.ModalViewProdutos;
import br.edu.ifpr.comat.ui.components.dialogs.ModalViewClientes;
import br.edu.ifpr.comat.ui.components.panels.ComatJPanels;
import br.edu.ifpr.comat.ui.components.tables.TbModelItensOrcamento;
import br.edu.ifpr.comat.ui.components.tables.TbRenderNumberBigDecimalOrc;
import br.edu.ifpr.comat.ui.components.tables.TbRenderNumberDecimalOrc;
import br.edu.ifpr.comat.ui.components.toolbars.CrudToolBar;
import br.edu.ifpr.comat.ui.utils.MaskFields;
import br.edu.ifpr.comat.util.DateUtils;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.ListSelectionModel;

import java.awt.Font;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.SwingConstants;

public class FormOrcamento extends JPanel implements ComatJPanels, ActionListener {
	
	private Integer idCliente = null;
	private Integer idObra = null;
	private Integer idOrcamento = null;
	
	private Boolean insert = false;
	
	private JPanel headerPanel, topPanel, contentPanel, formPanel, gridPanel, actionsPanel, bottonPanel;
	private JScrollPane scollItens;	

	private CrudToolBar crudBar;
	
	private JLabel lbValData, lbValStatus, lbValIdPedido;
	private JTextField txTotal;	
	private JFormattedTextField txVencimento;	
	private JTextField txCliente, txDocumento, txTelefone, txEmail, txCidade, txPessoa, txObFone, txObResponsavel;		
	private JButton btCliPesquisar, btCliAdicionar, btObAdicionar, btOrcFechar, btOrcCancelar, btItemAdd, btItemDel;
	private JComboBox cbxObra;	
	
	private JTable table;
	private TbModelItensOrcamento model;
	
	public FormOrcamento() {
		buildComponents();
				
		this.model = new TbModelItensOrcamento();
		setModelTable(model);
		
		setDisableFields();
		setCleanFields();
		crudBar.incluir();
		btOrcFechar.setEnabled(false);
		btOrcCancelar.setEnabled(false);
	}
	public FormOrcamento(int idOrc) {
		this.idOrcamento = idOrc;		
		this.idCliente = new OrcamentoController().search(idOrc).getCliente().getIdCliente();	
		buildComponents();
		
		this.model = new TbModelItensOrcamento(new ItenOrcamentoController().search(idOrc));		
		setModelTable(model);
		
		restoreFields();
		setDisableFields();	
		setBtAcoes();
		crudBar.alterar();	
		updateTotal();
	}

	private void buildComponents() {
		setLayout(new BorderLayout(0, 0));

		headerPanel = new JPanel();
		topPanel = new JPanel();
		contentPanel = new JPanel();
		gridPanel = new JPanel();
		formPanel = new JPanel();
		actionsPanel = new JPanel();
		bottonPanel = new JPanel();

		headerPanel.setLayout(new BorderLayout(0, 0));
		contentPanel.setLayout(new BorderLayout(0, 0));		
		gridPanel.setLayout(new GridLayout(2, 1));

		topPanel.setBorder(new EtchedBorder());
		formPanel.setBorder(new EtchedBorder());
		actionsPanel.setBorder(new EtchedBorder());

		crudBar = new CrudToolBar(this);
		crudBar.setFloatable(false);
		
		JLabel lbOrcamento = new JLabel("Orçamento:");		
		lbValIdPedido = new JLabel("NOVO");
		lbValIdPedido.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lbData = new JLabel("Data:");		
		lbValData = new JLabel("01/11/2013");
		lbValData.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lbVencimento = new JLabel("Vencimento:");		
		
		txVencimento = new JFormattedTextField(MaskFields.marcarate("##/##/####"));
		
		JLabel lbStatus = new JLabel("Status:");		
		lbValStatus = new JLabel(new OrcamentoController().getStatusList()[0]);		
		lbValStatus.setFont(new Font("Tahoma", Font.BOLD, 11));
		
				
		GroupLayout grtopPanel = new GroupLayout(topPanel);
		grtopPanel.setHorizontalGroup(
			grtopPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(grtopPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lbOrcamento)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lbValIdPedido, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(lbData)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lbValData, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lbVencimento)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txVencimento, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 339, Short.MAX_VALUE)
					.addComponent(lbStatus)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lbValStatus, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
					.addGap(12))
		);
		grtopPanel.setVerticalGroup(
			grtopPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(grtopPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(grtopPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbData)
						.addComponent(lbValData)
						.addComponent(lbVencimento)
						.addComponent(txVencimento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbValStatus)
						.addComponent(lbStatus, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbOrcamento)
						.addComponent(lbValIdPedido))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		topPanel.setLayout(grtopPanel);
		
				
		JLabel lbCliente = new JLabel("Cliente:");		
		txCliente = new JTextField();
		txCliente.setEnabled(false);
		txCliente.setEditable(false);
		txCliente.setColumns(10);
		
		btCliPesquisar = new JButton("");
		btCliPesquisar.setToolTipText("Pesquisar clientes");
		btCliPesquisar.setIcon(new ImageIcon(FormOrcamento.class.getResource("/br/edu/ifpr/comat/ui/images/search.png")));
		btCliPesquisar.addActionListener(this);
		
		btCliAdicionar = new JButton("");
		btCliAdicionar.setToolTipText("Cadastrar cliente");
		btCliAdicionar.setIcon(new ImageIcon(FormOrcamento.class.getResource("/br/edu/ifpr/comat/ui/images/add.png")));
		btCliAdicionar.addActionListener(this);
		
		JLabel lbDocumento = new JLabel("CPF/CNPJ:");		
		txDocumento = new JTextField();
		txDocumento.setEnabled(false);
		txDocumento.setEditable(false);
		txDocumento.setColumns(10);
		
		JLabel lblTelefone = new JLabel("Telefone:");		
		txTelefone = new JTextField();
		txTelefone.setEnabled(false);
		txTelefone.setEditable(false);
		txTelefone.setColumns(10);
		
		JLabel lblEmail = new JLabel("e-mail:");		
		txEmail = new JTextField();
		txEmail.setEditable(false);
		txEmail.setEnabled(false);
		txEmail.setColumns(10);
		
		JLabel lbCidade = new JLabel("Cidade:");		
		txCidade = new JTextField();
		txCidade.setEnabled(false);
		txCidade.setEditable(false);
		
		JLabel lbTipo = new JLabel("Pessoa:");		
		txPessoa = new JTextField();
		txPessoa.setEnabled(false);
		txPessoa.setEditable(false);
		
		JLabel lblObra = new JLabel("Obra:");		
		cbxObra = new JComboBox();
		cbxObra.setEnabled(false);
		cbxObra.addActionListener(this);
		
		JLabel lblTelefoneObr = new JLabel("Telefone:");		
		txObFone = new JTextField();
		txObFone.setEnabled(false);
		txObFone.setEditable(false);
		txObFone.setColumns(10);
		
		JLabel lblResponsvel = new JLabel("Responsável:");		
		txObResponsavel = new JTextField();
		txObResponsavel.setEnabled(false);
		txObResponsavel.setEditable(false);
		txObResponsavel.setColumns(10);
		
		btObAdicionar = new JButton("");		
		btObAdicionar.setToolTipText("Cadastrar obra");
		btObAdicionar.setIcon(new ImageIcon(FormOrcamento.class.getResource("/br/edu/ifpr/comat/ui/images/add.png")));
		btObAdicionar.addActionListener(this);
		
		GroupLayout grContentPanel = new GroupLayout(formPanel);
		grContentPanel.setHorizontalGroup(
			grContentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(grContentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(grContentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(grContentPanel.createSequentialGroup()
							.addComponent(lbCliente)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txCliente, GroupLayout.PREFERRED_SIZE, 401, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btCliPesquisar)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btCliAdicionar)
							.addGap(16)
							.addComponent(lbDocumento)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txDocumento, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
							.addGap(6))
						.addGroup(grContentPanel.createSequentialGroup()
							.addComponent(lblTelefone)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txTelefone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(16)
							.addComponent(lblEmail)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txEmail, GroupLayout.PREFERRED_SIZE, 247, Short.MAX_VALUE)
							.addGap(16)
							.addComponent(lbCidade)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txCidade, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lbTipo)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txPessoa, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
							.addGap(6))
						.addGroup(grContentPanel.createSequentialGroup()
							.addComponent(lblObra)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cbxObra, GroupLayout.PREFERRED_SIZE, 307, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btObAdicionar)
							.addGap(16)
							.addComponent(lblTelefoneObr)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txObFone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(16)
							.addComponent(lblResponsvel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txObResponsavel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		grContentPanel.setVerticalGroup(
			grContentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(grContentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(grContentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbCliente)
						.addComponent(txCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btCliPesquisar)
						.addComponent(btCliAdicionar)
						.addComponent(txDocumento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbDocumento))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(grContentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTelefone)
						.addComponent(txTelefone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEmail)
						.addComponent(txEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txPessoa, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbTipo)
						.addComponent(txCidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbCidade))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(grContentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblObra)
						.addComponent(cbxObra, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txObResponsavel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblResponsvel)
						.addComponent(txObFone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTelefoneObr)
						.addComponent(btObAdicionar)
						
						).addContainerGap(12,Short.MAX_VALUE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		formPanel.setLayout(grContentPanel);
		
		scollItens = new JScrollPane();
		table = new JTable();
		
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setShowHorizontalLines(true);
		table.setRowHeight(28);		
		
		scollItens.setViewportView(table);		
		
		contentPanel.add(BorderLayout.NORTH, formPanel);
		contentPanel.add(BorderLayout.CENTER,scollItens);
		
		bottonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		bottonPanel.setBorder(new EtchedBorder());
		
		btOrcFechar = new JButton("Fechar orçamento");
		btOrcFechar.addActionListener(this);		
		bottonPanel.add(btOrcFechar);		

		btOrcCancelar = new JButton("Cancelar orçamento");
		btOrcCancelar.addActionListener(this);
		bottonPanel.add(btOrcCancelar);
		
		btItemAdd = new JButton("");
		btItemAdd.setToolTipText("Adicionar item");
		btItemAdd.setIcon(new ImageIcon(FormOrcamento.class.getResource("/br/edu/ifpr/comat/ui/images/add.png")));
		btItemAdd.addActionListener(this);
		
		btItemDel = new JButton("");
		btItemDel.setToolTipText("Remover item");
		btItemDel.setIcon(new ImageIcon(FormOrcamento.class.getResource("/br/edu/ifpr/comat/ui/images/delete.png")));
		btItemDel.addActionListener(this);
		
		JLabel lbTotal = new JLabel("Valor Total:");
		lbTotal.setFont(new Font("Tahoma", Font.BOLD, 11));
		txTotal = new JTextField("R$ 0,00");
		txTotal.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		txTotal.setEditable(false);
		txTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		txTotal.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		GroupLayout gl_actionsPanel = new GroupLayout(actionsPanel);
		gl_actionsPanel.setHorizontalGroup(
			gl_actionsPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_actionsPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btItemAdd)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btItemDel)
					.addPreferredGap(ComponentPlacement.RELATED, 482, Short.MAX_VALUE)
					.addComponent(lbTotal)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txTotal, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_actionsPanel.setVerticalGroup(
			gl_actionsPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_actionsPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_actionsPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_actionsPanel.createParallelGroup(Alignment.BASELINE)
							.addComponent(btItemAdd)
							.addComponent(btItemDel)
							.addGroup(gl_actionsPanel.createSequentialGroup()
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(txTotal, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addComponent(lbTotal, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap())
		);
		actionsPanel.setLayout(gl_actionsPanel);
		
		gridPanel.add(actionsPanel);
		gridPanel.add(bottonPanel);
				
		headerPanel.add(BorderLayout.NORTH, crudBar);
		headerPanel.add(BorderLayout.SOUTH, topPanel);
		
		add(BorderLayout.NORTH, headerPanel);
		add(BorderLayout.CENTER, contentPanel);		
		add(BorderLayout.SOUTH, gridPanel);
		
	}
	
	public void setModelTable(AbstractTableModel model) {
		table.setModel(model);
		table.setDefaultRenderer(BigDecimal.class, new TbRenderNumberBigDecimalOrc());
		table.setDefaultRenderer(Double.class, new TbRenderNumberDecimalOrc());
		table.getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				updateTotal(); 				
			}
			
		});			
		
		table.getColumnModel().getColumn(0).setPreferredWidth(30);
		table.getColumnModel().getColumn(1).setPreferredWidth(380);
		table.getColumnModel().getColumn(2).setPreferredWidth(30);
		table.getColumnModel().getColumn(3).setPreferredWidth(40);
		table.getColumnModel().getColumn(4).setPreferredWidth(40);
		table.getColumnModel().getColumn(5).setPreferredWidth(60);
		table.getColumnModel().getColumn(6).setPreferredWidth(40);
		table.getColumnModel().getColumn(7).setPreferredWidth(60);
		
	}
	
	private int tbSelectedRow() {
		int rowSelected = table.getSelectedRow();
		return rowSelected;
	}

	private void updateTotal() {		
		String result = String.format("%.2f", new BigDecimal(getTotal()));
		txTotal.setText("R$ " + result);
	}
	
	private double getTotal(){		
		double total = 0d;
		for(int i = 0; i < table.getRowCount(); i++){  
			total += Double.parseDouble(table.getValueAt(i, 7).toString());
		}  
		return total;
	}
	
	private List<ItenOrcamento> criaItens() {
		List<ItenOrcamento> itens = new ArrayList<ItenOrcamento>();
		for (int i = 1; i <= 5; i++) {
			ItenOrcamento it = new ItenOrcamento();
			Produto p = new ProdutoController().search(1010);
			Orcamento o = new OrcamentoController().search(14);

			it.setProduto(p);
			it.setOrcamento(o);
			it.setPreco(p.getPrecoVenda());		

			itens.add(it);
		}
		return itens;
	}

	@Override
	public JPanel getPanel() {
		return this;
	}

	@Override
	public void setEnabledFields() {
		btCliAdicionar.setEnabled(true);		
		btCliPesquisar.setEnabled(true);
		btObAdicionar.setEnabled(false);	
		txVencimento.setEnabled(true);
		txTotal.setEnabled(true);
	}

	@Override
	public void setDisableFields() {
		btCliAdicionar.setEnabled(false);		
		btCliPesquisar.setEnabled(false);
		btObAdicionar.setEnabled(false);		
		txVencimento.setEnabled(false);
		cbxObra.setEnabled(false);
		table.setEnabled(false);
		btItemAdd.setEnabled(false);
		btItemDel.setEnabled(false);
		txTotal.setEnabled(false);
	}	

	@Override
	public void setCleanFields() {
		lbValData.setText("");
		lbValStatus.setText("");
		txVencimento.setText("");
		txCliente.setText("");
		txDocumento.setText("");
		txTelefone.setText("");
		txEmail.setText("");
		txCidade.setText("");
		txPessoa.setText("");		
		txObFone.setText("");
		txObResponsavel.setText("");
		lbValIdPedido.setText(" ");
		cbxObra.setModel(new JComboBox<>().getModel());
	}

	@Override
	public void setLockFk() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setUnLockFk() {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Incluir")) {
			incluir();

		} else if (e.getActionCommand().equals("Alterar")) {
			alterar();

		} else if (e.getActionCommand().equals("Excluir")) {
			excluir(); System.gc();

		} else if (e.getActionCommand().equals("Salvar")) {
			salvar(); System.gc();

		} else if (e.getActionCommand().equals("Cancelar")) {
			cancelar(); System.gc();

		} else if(e.getSource() == btCliPesquisar){
			observerSearchCliente(); System.gc();
			
		} else if(e.getSource() == btCliAdicionar){
			observerInsertCliente(); System.gc();
			
		} else if(e.getSource() == btObAdicionar){
		    observerInsertObra(); System.gc();
		    
		} else if(e.getSource() == btOrcFechar){
			fecharOrcamento();
		    
		} else if(e.getSource() == btOrcCancelar){
			cancelarOrcamento();
		    
		} else if(e.getSource() == cbxObra){
			setCbxObra(cbxObra.getSelectedIndex());
			
		} else if(e.getSource() == btItemAdd){			
			observerInsertProduto(); System.gc();			
			
		} else if(e.getSource() == btItemDel){
			
			if(tbSelectedRow() != -1){
				model.removeIten(tbSelectedRow());
			} else {
				JOptionPane.showMessageDialog(null, "Selecione uma linha!");
			}
		}
	
	}
	
	private void incluir(){
		insert = true;
				
		setCleanFields();
		setEnabledFields();
		crudBar.inclusao();		
		
		Date now = new Date();  
		Calendar cal = Calendar.getInstance();  
		cal.setTime(now);  
		cal.add(Calendar.DAY_OF_MONTH, 30); 
		
		Date venc = cal.getTime(); 
				
		lbValData.setText(DateUtils.formatarData(now));
		txVencimento.setText(DateUtils.formatarData(venc));
		lbValIdPedido.setText("NOVO");
		lbValStatus.setText("Aberto");
		
		if(idCliente != null) idCliente = null;
		if(idObra != null) idObra = null;
		if(idOrcamento != null) idOrcamento = null;
		
		model.limpar();
		btOrcFechar.setEnabled(false);
		btOrcCancelar.setEnabled(false);
		
		btCliAdicionar.requestFocusInWindow();
		
	}
	
	private void alterar(){
		if( new OrcamentoController().search(idOrcamento).getStatus() != 1){			
			if( new OrcamentoController().search(idOrcamento).getStatus() != 2){				
				insert = false;
				crudBar.inclusao();
				setEnabledFields();		
				
				btObAdicionar.setEnabled(true);
				if(idObra != null) { 
					cbxObra.setEnabled(true);			
				}
				btOrcFechar.setEnabled(false);
				btOrcCancelar.setEnabled(false);
				table.setEnabled(true);
				btItemAdd.setEnabled(true);
				btItemDel.setEnabled(true);
				
			} else {
				JOptionPane.showMessageDialog(null, "Este orçamento não pode ser editado, status cancelado!");
			}		
			
		} else {
			int excluir = JOptionPane.showConfirmDialog(null,
					"Este orçamento esta fechado, deseja efetuar o cancelamento?", "Pedido fechado!",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (excluir == JOptionPane.YES_OPTION) {
				cancelarOrcamento();				
			}
		}		
	}
	
	private void excluir(){
		if( new OrcamentoController().search(idOrcamento).getStatus() != 1){			
			if( new OrcamentoController().search(idOrcamento).getStatus() != 2){	
			int excluir = JOptionPane.showConfirmDialog(null,
					"Deseja realmente excluir os dados ?", "Pedido de Exclusão",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (excluir == JOptionPane.YES_OPTION) {
				new OrcamentoController().delete(idOrcamento);
				crudBar.incluir();
				setCleanFields();
				model.limpar();
				btOrcFechar.setEnabled(false);
				btOrcCancelar.setEnabled(false);				
			}
			} else {
				JOptionPane.showMessageDialog(null, "Este orçamento não pode ser excluido, status cancelado!");
			}		
			
		} else {
			JOptionPane.showMessageDialog(null, "Este orçamento não pode ser excluido, status fechado!");
		}	
	}
	
	private void salvar(){		
		
		Date data = DateUtils.str2Date(lbValData.getText());
		Date validade = DateUtils.str2Date(txVencimento.getText());
		String status = lbValStatus.getText();
		
		if (insert) {
			
			if(idCliente != null){
				if(model.getRowCount() > 0){
					idOrcamento = new OrcamentoController().save(data, status, validade, null, new BigDecimal(getTotal()), idCliente, idObra, model.getRows());
					lbValIdPedido.setText(idOrcamento.toString());
					setDisableFields();				
					crudBar.alterar();
					JOptionPane.showMessageDialog(null, "Orçamento cadastrado com sucesso");
					
				} else {
					JOptionPane.showMessageDialog(null, "O orçamento precisa de itens para ser salvo!");
				}				
				
			} else {				
				JOptionPane.showMessageDialog(null, "É nescessário selecionar ou inserir um cliente!");
			}			
				
		} else {			
			new OrcamentoController().alter(idOrcamento, status, validade, null, new BigDecimal(getTotal()), idCliente, idObra, model.getRows());
			
			setDisableFields();				
			crudBar.alterar();
			JOptionPane.showMessageDialog(null, "Orçamento " + idOrcamento + " alterado com sucesso");
		}
		setBtAcoes();
	}
	
	private void cancelar(){
		if (insert)
			insert = false;
		
		crudBar.incluir();
		setCleanFields();
		setDisableFields();
				
		model.limpar();
	}
	
	private void restoreFields(){
		Cliente c = new ClienteController().search(idCliente);
		
		if(c instanceof ClienteFisica){			
			txCliente.setText(((ClienteFisica) c).getNome());
			txDocumento.setText(((ClienteFisica) c).getCpf());
			txPessoa.setText("Fisica");
			
		} else {
			txCliente.setText(((ClienteJuridica) c).getRazao());			
			txDocumento.setText(((ClienteJuridica) c).getCnpj());
			txPessoa.setText("Jurídica");
		}
						
		txTelefone.setText(c.getTelefone());
		txEmail.setText(c.getEmail());
		txCidade.setText(c.getEndereco().getCidade().getNome());		
		
		if(!insert){
			Orcamento o = new OrcamentoController().search(idOrcamento);
			
			lbValIdPedido.setText(idOrcamento.toString());
			lbValData.setText(DateUtils.date2Str(o.getData()));
			txVencimento.setText(DateUtils.date2Str(o.getValidade()));			
			lbValStatus.setText(new OrcamentoController().getStatusList()[o.getStatus()]);	
			if(o.getObra() != null)	{
				setCbxObraAlter(o.getObra().getIdObra());	
			} else setCbxObra(0);		
		} else {			
			setCbxObra(0);		
		}		
	}
	public void setBtAcoes(){
		if(idOrcamento != null){
			int status = new OrcamentoController().search(idOrcamento).getStatus();
			
			if(status == 0){
				btOrcFechar.setEnabled(true);
				btOrcCancelar.setEnabled(false);
			} else if(status == 1) {	
				btOrcFechar.setEnabled(false);
				btOrcCancelar.setEnabled(true);			
			} else if(status == 2) {
				btOrcFechar.setEnabled(false);
				btOrcCancelar.setEnabled(false);
			} 
			
		} else {
			btOrcFechar.setEnabled(false);
			btOrcCancelar.setEnabled(false);
		}		
	}
	
	public void fecharOrcamento(){				
		if(model.getRowCount() > 0){
			if(new OrcamentoController().search(idOrcamento).getObra() == null){
				int excluir = JOptionPane.showConfirmDialog(null,
						"O orçamento precisa de uma obra!\nDeseja gerar uma padão com o endereço do cliente?", "Fechar orçamento!",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (excluir == JOptionPane.YES_OPTION) {
					new OrcamentoController().gerarObra(idOrcamento);
					setCbxObraAlter(0);
					fecharOrcamento();				
				}
			} else {
				new OrcamentoController().alter(idOrcamento, 1);
				lbValStatus.setText(new OrcamentoController().getStatusList()[new OrcamentoController().search(idOrcamento).getStatus()]);
				setDisableFields();				
				crudBar.alterar();
				setBtAcoes();
				JOptionPane.showMessageDialog(null, "Orçamento fechado!");
			}
			
		} else {
			JOptionPane.showMessageDialog(null, "O orçamento precisa de itens para ser fechado!");
		}		
	}
	
	public void cancelarOrcamento(){
		int excluir = JOptionPane.showConfirmDialog(null,
				"Deseja cancelar este orçamento? \nEsta opção não pode ser revertida!", "Cancelar orçamento!",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (excluir == JOptionPane.YES_OPTION) {
			new OrcamentoController().alter(idOrcamento, 2);
			lbValStatus.setText(new OrcamentoController().getStatusList()[new OrcamentoController().search(idOrcamento).getStatus()]);
			setDisableFields();				
			crudBar.alterar();
			setBtAcoes();
			JOptionPane.showMessageDialog(null, "Orçamento cancelado!");			
		}
		
	}
	
	private void setCbxObraAlter(Integer id){		
		
		int index = 0;		
		List<Obra> obrs = new ObraController().searchCliente(idCliente);		
		
		if(!obrs.isEmpty()){
			String[] obras = new String[obrs.size()];
			
			int i = 0;
			for (Obra obra : obrs) {
				obras[i] = obra.getNome();
				
				if(obra.getIdObra() == id){
					txObFone.setText(obra.getTelefone());
					txObResponsavel.setText(obra.getResponsavel());
					idObra = id;
					index = i;
				}
				i++;
			}
			
			cbxObra.setModel(new JComboBox<>(obras).getModel());
			cbxObra.setEnabled(true);
			cbxObra.setSelectedIndex(index);
		} else {
			cbxObra.setModel(new JComboBox<>().getModel());
			txObFone.setText("");
			txObResponsavel.setText("");
			cbxObra.setEnabled(false);
			idObra = null;
		}	
		
	}
	
	private void setCbxObra(int pos) {
		
		List<Obra> obrs = new ObraController().searchCliente(idCliente);
		
		if(!obrs.isEmpty()){
			String[] obras = new String[obrs.size()];
			
			int i = 0;
			for (Obra obra : obrs) {
				obras[i] = obra.getNome();
								
				if(i == pos){
					txObFone.setText(obra.getTelefone());
					txObResponsavel.setText(obra.getResponsavel());
					idObra = obra.getIdObra();
				}
				
				i++;
			}		
			cbxObra.setModel(new JComboBox<>(obras).getModel());
			cbxObra.setEnabled(true);
			cbxObra.setSelectedIndex(pos);
			
			
		} else {
			cbxObra.setModel(new JComboBox<>().getModel());
			txObFone.setText("");
			txObResponsavel.setText("");
			cbxObra.setEnabled(false);
			idObra = null;
		}			
	}
	
	private void observerSearchCliente() {
		java.awt.EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				ModalViewClientes searchClientes = new ModalViewClientes();			
				searchClientes.setModal(true);
				searchClientes.setVisible(true);
				
				if(searchClientes.getIdCli() != null){
					idCliente = searchClientes.getIdCli();
					idObra = null;
					restoreFields();
					setCbxObra(0);
					btObAdicionar.setEnabled(true);
					
					table.setEnabled(true);
					btItemAdd.setEnabled(true);
					btItemDel.setEnabled(true);
					
				} else {
					btObAdicionar.setEnabled(false);
				}							
			}
		});
	}
	
	private void observerInsertCliente() {
		java.awt.EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				ModalFormCliente formCliente = new ModalFormCliente();
				formCliente.setModal(true);
				formCliente.setVisible(true);
				if(formCliente.getIdCli() != null){
					idCliente = formCliente.getIdCli();
					idObra = null;
					restoreFields();
					setCbxObra(0);
					btObAdicionar.setEnabled(true);	
				
					table.setEnabled(true);
					btItemAdd.setEnabled(true);
					btItemDel.setEnabled(true);
					
				} else {
					btObAdicionar.setEnabled(false);
				}
								
			}
		});
	}	
	
	private void observerInsertObra() {
		java.awt.EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				ModalFormObra obra = new ModalFormObra();
				obra.setIdCli(idCliente);
				obra.setInsert(true);
				obra.setModal(true);
				obra.setVisible(true);	
				
				if(obra.getIdObra() != null){
					setCbxObraAlter(obra.getIdObra());
				}
								
			}
		});
	}
	
	private void observerInsertProduto() {
		java.awt.EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				ModalViewProdutos formProdutos = new ModalViewProdutos();
				
				formProdutos.setModal(true);
				formProdutos.setVisible(true);
				if(formProdutos.getRefProd() != null){					
					addItem(formProdutos.getRefProd());	
				}								
			}
		});
	}
	
	private void addItem(int refProd){
		
		if(!updateDoubleIten(refProd)){
			ItenOrcamento it = new ItenOrcamento();
			Produto p = new ProdutoController().search(refProd);
			
			it.setProduto(p);		
			it.setQuantidade(1);
			it.setPreco(p.getPrecoVenda());					
			
			model.addIten(it);
			table.changeSelection(table.getRowCount() -1, 3, false, false);
			table.requestFocus();
		} 		
	}
	
	private boolean updateDoubleIten(int refProd) {
		for (int i = 0; i < table.getRowCount(); i++) {
			
			if((int) table.getValueAt(i, 0) == refProd){
				int val = (int) table.getValueAt(i, 3) + 1;				
				table.setValueAt(val, i, 3);
				table.changeSelection(i, 3, false, false);
				table.requestFocus();
				return true;
			}
		}		
		
		return false;
	}
}

