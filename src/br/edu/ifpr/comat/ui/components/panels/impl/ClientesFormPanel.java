package br.edu.ifpr.comat.ui.components.panels.impl;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import br.edu.ifpr.comat.controller.ClienteController;
import br.edu.ifpr.comat.dao.ClienteDAO;
import br.edu.ifpr.comat.model.ClienteFisica;
import br.edu.ifpr.comat.ui.components.panels.ComatJPanels;
import br.edu.ifpr.comat.ui.components.toolbars.BaseToolBar;
import br.edu.ifpr.comat.util.DateUtils;

import javax.swing.border.EtchedBorder;

public class ClientesFormPanel extends JPanel implements ComatJPanels,
		ActionListener {
	
	private Boolean insert =  false;
	
	private JPanel headerPanel, topPanel, contentPanel;
	private ComatJPanels currentPanel;
	private BaseToolBar crudBar;

	private ButtonGroup btGrPessoa;
	private JRadioButton rbtFisica, rbtJuridica;
	private JLabel lblCadastroDate;
	private JComboBox cbxStatus;

	public ClientesFormPanel() {
		setLayout(new BorderLayout(0, 0));
		buildComponents();
	}

	@Override
	public void buildComponents() {
		headerPanel = new JPanel();
		contentPanel = new JPanel();
		topPanel = new JPanel();

		headerPanel.setLayout(new BorderLayout(0, 0));
		contentPanel.setLayout(new BorderLayout(0, 0));
		topPanel.setBorder(new EtchedBorder());

		crudBar = new BaseToolBar(this);
		crudBar.setFloatable(false);

		currentPanel = new ClientesFormFisicaPanel();
		headerPanel.add(BorderLayout.NORTH, crudBar);
		contentPanel.add(BorderLayout.CENTER, currentPanel.getPanel());

		headerPanel.add(BorderLayout.SOUTH, topPanel);

		JLabel lbPessoa = new JLabel("Pessoa:");

		rbtFisica = new JRadioButton("Física");
		rbtJuridica = new JRadioButton("Jurídica");
		rbtFisica.setSelected(true);

		btGrPessoa = new ButtonGroup();
		btGrPessoa.add(rbtFisica);
		btGrPessoa.add(rbtJuridica);

		rbtFisica.addActionListener(this);
		rbtJuridica.addActionListener(this);

		JLabel lblCadastro = new JLabel("Data do cadastro:");
		lblCadastroDate = new JLabel();

		cbxStatus = new JComboBox(new ClienteController().getStatusList());		

		GroupLayout gl_topPanel = new GroupLayout(topPanel);
		gl_topPanel.setHorizontalGroup(
			gl_topPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_topPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lbPessoa)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(rbtFisica)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(rbtJuridica)
					.addPreferredGap(ComponentPlacement.RELATED, 335, Short.MAX_VALUE)
					.addComponent(lblCadastro)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblCadastroDate, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
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
						.addComponent(lblCadastro)
						.addComponent(lblCadastroDate)
						.addComponent(cbxStatus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		topPanel.setLayout(gl_topPanel);

		add(BorderLayout.NORTH, headerPanel);
		add(BorderLayout.CENTER, contentPanel);

		// topPanel.setPreferredSize(new Dimension(760, 40));
	}

	@Override
	public JPanel getPanel() {
		return this;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == rbtFisica) {
			setCurrentPanel(new ClientesFormFisicaPanel());
		} else if (e.getSource() == rbtJuridica) {
			setCurrentPanel(new ClientesFormJuridicaPanel());
		} else if (e.getActionCommand().equals("Incluir")) {
			incluir();
		} else if (e.getActionCommand().equals("Alterar")) {
			insert = false;
			crudBar.alterar();
			currentPanel.setEnabledFields();			
		} else if (e.getActionCommand().equals("Excluir")) {
			int excluir = JOptionPane.showConfirmDialog(null,
                    "Deseja realmente excluir os dados ???",
                    "Pedido de Exclus�o",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
			if (excluir == JOptionPane.YES_OPTION) {
				crudBar.excluir();
				currentPanel.setCleanFields();				
			}
			
		} else if (e.getActionCommand().equals("Salvar")) {
			salvar();			
		} else if (e.getActionCommand().equals("Cancelar")) {	
			cancelar();			
		}
	}

	public void setCurrentPanel(ComatJPanels set) {
		contentPanel.removeAll();
		currentPanel = set;
		contentPanel.add(BorderLayout.CENTER, currentPanel.getPanel());
		validate();
		repaint();
		
	}
	
	private void incluir(){
		insert = true;
		crudBar.incluir();
		currentPanel.setEnabledFields();
		lblCadastroDate.setText(new DateUtils().formatarData(new Date()));		
	}

	private void salvar() {
		if (insert) {
			if (currentPanel.getPanel() instanceof ClientesFormFisicaPanel) {
				ClientesFormFisicaPanel cf = (ClientesFormFisicaPanel) currentPanel
						.getPanel();

				String cpf = cf.getTxCpf().getText();				
				cpf = cpf.replaceAll("[.-]", "");
				
			//	System.out.println(cpf);
				
//				long cpf 			= Long.parseLong(cpf);
//				long rg 			= Long.parseLong(cf.getTxRg().getText());
//				String nome 		= cf.getTxNome().getText();
//				Date dataNasc 		= DateUtils.str2Date(cf.getTxDataNasc().getText(),DateFormat.MEDIUM);
//				String celular 		= cf.getTxCelular().getText();
//				String status;
//				String email 		= cf.getTxEmail().getText();
//				String site 		= cf.getTxSite().getText();
//				String telefone 	= cf.getTxSite().getText();
//				String observacoes 	= cf.getTxObservacoes().getText();
//				Date dataCadastro 	= DateUtils.str2Date(lblCadastroDate.getText(),DateFormat.MEDIUM);
				
				
//				new ClienteController().salvar(cpf, rg, nome, dataNasc,
//				 celular, null, 0, email, site, telefone,
//				 observacoes, dataCadastro, null, null);

			} else {

			}
			
		} else {
			
			
		}
		insert = false;
	}
	private void cancelar(){
		crudBar.cancelar();
		currentPanel.setCleanFields();
		currentPanel.setDisableFields();
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
	
	

}
