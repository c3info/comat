package br.edu.ifpr.comat.ui.components.panels.impl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.Date;
import java.util.Set;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import br.edu.ifpr.comat.controller.ClienteController;
import br.edu.ifpr.comat.dao.ClienteDAO;
import br.edu.ifpr.comat.model.ClienteFisica;
import br.edu.ifpr.comat.model.Endereco;
import br.edu.ifpr.comat.ui.components.panels.ComatJPanels;
import br.edu.ifpr.comat.ui.components.toolbars.BaseToolBar;
import br.edu.ifpr.comat.ui.utils.CheckDocuments;
import br.edu.ifpr.comat.ui.utils.CheckFields;
import br.edu.ifpr.comat.util.DateUtils;

import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

public class ClientesFormPanel extends JPanel implements ComatJPanels,
		ActionListener {
	private Boolean insert = false;
	
	private JPanel headerPanel, topPanel, contentPanel;
	private ComatJPanels currentPanel;
	private BaseToolBar crudBar;

	private ButtonGroup btGrPessoa;
	private JRadioButton rbtFisica, rbtJuridica;
	private JLabel lblCadastroDate;
	private JComboBox<String> cbxStatus;	

	public ClientesFormPanel() {
		setLayout(new BorderLayout(0, 0));
		buildComponents();		
		setDisableFields();
		crudBar.cancelar();
	}

	private void buildComponents() {
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
					.addPreferredGap(ComponentPlacement.RELATED, 271, Short.MAX_VALUE)
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
			alterar();
			
		} else if (e.getActionCommand().equals("Excluir")) {
			excluir();

		} else if (e.getActionCommand().equals("Salvar")) {
			salvar();
			
		} else if (e.getActionCommand().equals("Cancelar")) {
			cancelar();
		}
	}
	
	private void incluir() {
		insert = true;	
		
		crudBar.incluir();		
		setEnabledFields();
		currentPanel.setEnabledFields();		
		
		new DateUtils();
		lblCadastroDate.setText(DateUtils.formatarData(new Date()));
	}
	
	private void alterar() {
		insert = false;
		
		crudBar.alterar();
		setEnabledFields();
		currentPanel.setEnabledFields();
	}

	private void excluir() {
		int excluir = JOptionPane.showConfirmDialog(null,
				"Deseja realmente excluir os dados ???",
				"Pedido de Exclus�o", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		if (excluir == JOptionPane.YES_OPTION) {
			crudBar.excluir();
			currentPanel.setCleanFields();
		}
	}

	private void salvar() {	
				
		if (currentPanel.getPanel() instanceof ClientesFormFisicaPanel) {
			ClientesFormFisicaPanel cf = (ClientesFormFisicaPanel) currentPanel.getPanel();

			Component ignoreComponents[] = { cf.getTxTelefone(), cf.getTxEmail(), cf.getTxSite(), cf.getTxComplemento(), cf.getTxBairro() };
			if (CheckFields.checkEmptyFields(currentPanel.getPanel(),ignoreComponents)) {

				if (CheckDocuments.checkCPF(cf.getTxCpf().getText())) {

					int status = cbxStatus.getSelectedIndex();
					Date dataCadastro = DateUtils.str2Date(lblCadastroDate.getText(), DateFormat.MEDIUM);

					String nome = cf.getTxNome().getText().trim();

					String cpf = cf.getTxCpf().getText();
					String rg = cf.getTxRg().getText().trim();
					Date dataNasc = DateUtils.str2Date(cf.getTxDataNasc().getText(),	DateFormat.MEDIUM);

					String celular = cf.getTxCelular().getText().trim();
					String email = cf.getTxEmail().getText().trim();

					String telefone = cf.getTxTelefone().getText().trim();
					String site = cf.getTxSite().getText().trim();

					String cep = cf.getTxCep().getText();
					String endereco = cf.getTxEndereco().getText();
					int numero = Integer.parseInt(cf.getTxNumero().getText());
					String complemento = cf.getTxComplemento().getText();
					String bairro = cf.getTxBairro().getText();

					String estado = (String) cf.getCbxUf().getSelectedItem();
					String cidade = (String) cf.getCbxCidade().getSelectedItem();

					String observacoes = cf.getTxObservacoes().getText().trim();

					if (telefone.isEmpty()) {telefone = null;}
					if (email.isEmpty()) {email = null;}
					if (site.isEmpty()) {site = null;}
					if (complemento.isEmpty()) {complemento = null;}
					if (bairro.isEmpty()) {bairro = null;}
					if (observacoes.isEmpty()) {observacoes = null;}
					
					if (insert) {
						new ClienteController().salvar(dataCadastro, status, nome, cpf, rg, dataNasc,
								telefone, email, celular, site, cep, endereco, numero, complemento, bairro,
								estado, cidade, observacoes, null);	
						
						insert = false;	
						
						JOptionPane.showMessageDialog(null,
								"Cliente "+nome+" cadastrado com sucesso");	
					} else {
						// UPDATE IMPLEMENTAR;
						
						JOptionPane.showMessageDialog(null,
								"Cliente "+nome+" atualizado com sucesso");	
					}					
					setCleanFields();
					currentPanel.setCleanFields();	

				} else {
					cf.getTxCpf().setBackground(Color.cyan);
					JOptionPane.showMessageDialog(null,
							"Verifique o valor informado em CPF");
				}

			} else {
				JOptionPane.showMessageDialog(null,
						"Verifique os campos obrigatórios");
			}

		} else {
			ClientesFormJuridicaPanel cj = (ClientesFormJuridicaPanel) currentPanel.getPanel();
			Component ignoreComponents[] = { cj.getTxFantasia(), cj.getTxFax(), cj.getTxEmail(), cj.getTxSite(), cj.getTxComplemento(), cj.getTxBairro() };

			if (CheckFields.checkEmptyFields(currentPanel.getPanel(),ignoreComponents)) {
				
				if (CheckDocuments.checkCNPJ(cj.getTxCnpj().getText())) {
					
					int status = cbxStatus.getSelectedIndex();
					Date dataCadastro = DateUtils.str2Date(lblCadastroDate.getText(), DateFormat.MEDIUM);

					String razao = cj.getTxRazao().getText().trim();
					String fantasia = cj.getTxFantasia().getText().trim();

					String cnpj = cj.getTxCnpj().getText();
					String inscricao = cj.getTxInscricao().getText().trim();					

					String fax = cj.getTxFax().getText().trim();
					String email = cj.getTxEmail().getText().trim();

					String telefone = cj.getTxTelefone().getText().trim();
					String site = cj.getTxSite().getText().trim();

					String cep = cj.getTxCep().getText();
					String endereco = cj.getTxEndereco().getText();
					int numero = Integer.parseInt(cj.getTxNumero().getText());
					String complemento = cj.getTxComplemento().getText();
					String bairro = cj.getTxBairro().getText();

					String estado = (String) cj.getCbxUf().getSelectedItem();
					String cidade = (String) cj.getCbxCidade().getSelectedItem();

					String observacoes = cj.getTxObservacoes().getText().trim();

					if (fantasia.isEmpty()) {fantasia = null;}
					if (telefone.isEmpty()) {telefone = null;}
					if (fax.isEmpty()) { fax = null;}
					if (email.isEmpty()) {email = null;}
					if (site.isEmpty()) {site = null;}
					if (complemento.isEmpty()) {complemento = null;}
					if (bairro.isEmpty()) {bairro = null;}
					if (observacoes.isEmpty()) {observacoes = null;}
					
					if (insert) {
						
						new ClienteController().salvar(dataCadastro, status, razao, fantasia, cnpj, inscricao, 
								fax, email, site, telefone, cep, endereco, numero, complemento, bairro,
								estado, cidade, observacoes, null);					
						
						insert = false;	
						
						JOptionPane.showMessageDialog(null,
								"Cliente "+razao+" cadastrado com sucesso");	
					} else {
						// UPDATE IMPLEMENTAR;
						
						JOptionPane.showMessageDialog(null,
								"Cliente "+razao+" atualizado com sucesso");	
					}	
					
				} else {
					cj.getTxCnpj().setBackground(Color.cyan);
					JOptionPane.showMessageDialog(null,
							"Verifique o valor informado em CNPJ");				
				}		
				
			} else {
				JOptionPane.showMessageDialog(null,
						"Verifique os campos obrigatórios");
			}			
		}	
	}

	private void cancelar() {
		if(insert) insert = false;
		
		crudBar.cancelar();	
		setCleanFields();
		currentPanel.setCleanFields();					
		setDisableFields();	
		currentPanel.setDisableFields();
		
		CheckFields.restoreFields(currentPanel.getPanel());
	}

	public void setCurrentPanel(ComatJPanels set) {
		contentPanel.removeAll();
		currentPanel = set;
		contentPanel.add(BorderLayout.CENTER, currentPanel.getPanel());
		validate();
		repaint();
	}	

	@Override
	public void setEnabledFields() {
		cbxStatus.setEnabled(true);
		rbtFisica.setEnabled(false);
		rbtJuridica.setEnabled(false);
	}

	@Override
	public void setDisableFields() {
		cbxStatus.setEnabled(false);
		rbtFisica.setEnabled(true);
		rbtJuridica.setEnabled(true);
	}

	@Override
	public void setCleanFields() {
		lblCadastroDate.setText("");
	}
}
