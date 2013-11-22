package br.edu.ifpr.comat.ui.components.panels.impl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
import br.edu.ifpr.comat.controller.ClienteFisicaController;
import br.edu.ifpr.comat.controller.ClienteJuridicaController;
import br.edu.ifpr.comat.model.Cliente;
import br.edu.ifpr.comat.model.ClienteFisica;
import br.edu.ifpr.comat.model.ClienteJuridica;
import br.edu.ifpr.comat.ui.components.panels.ComatJPanels;
import br.edu.ifpr.comat.ui.components.panels.tabbeds.TabViewContatos;
import br.edu.ifpr.comat.ui.components.panels.tabbeds.TabViewObras;
import br.edu.ifpr.comat.ui.components.toolbars.CrudToolBar;
import br.edu.ifpr.comat.ui.utils.CheckFields;
import br.edu.ifpr.comat.util.CheckDocuments;
import br.edu.ifpr.comat.util.DateUtils;
import javax.swing.border.EtchedBorder;
import javax.swing.JTabbedPane;

public class FormClientes extends JPanel implements ComatJPanels, ActionListener {
	
	private Boolean insert = false;
	private JPanel headerPanel, topPanel, gridPanel, contentPanel;
	private TabViewObras tabObras;
	private TabViewContatos tabContatos;
		
	private GridBagConstraints cons;

	private ComatJPanels currentPanel;
	private CrudToolBar crudBar;

	private ButtonGroup btGrPessoa;
	private JRadioButton rbtFisica, rbtJuridica;
	private JLabel lblCadastroDate;
	private JComboBox<String> cbxStatus;
	private JTabbedPane tabbedPane;	

	public FormClientes() {
		currentPanel = new FormClientesFisica();		
		buildComponents();
		rbtFisica.setSelected(true);
		setDisableFields();
		crudBar.incluir();	
	}

	public FormClientes(int id, int tipo) {
		
		switch (tipo) {
		case 1:
			currentPanel = new FormClientesFisica();			
			buildComponents();
			rbtFisica.setSelected(true);
			restoreFieldsFisica(id);
			break;

		case 2:
			currentPanel = new FormClientesJuridica();
			buildComponents();
			rbtJuridica.setSelected(true);			
			restoreFieldsJuridica(id);
			break;
		}

		recoverFieldsSuper(id);
		currentPanel.setLockFk();
		rbtFisica.setEnabled(false);
		rbtJuridica.setEnabled(false);
		cbxStatus.setEnabled(false);
		crudBar.alterar();		
		tabObras.setIdCliente(id);
		tabObras.loadModelTable();
		tabContatos.setIdCliente(id);
		tabContatos.loadModelTable();
	}

	private void buildComponents() {
		setLayout(new BorderLayout(0, 0));

		headerPanel = new JPanel();
		contentPanel = new JPanel();
		topPanel = new JPanel();
		gridPanel = new JPanel();

		headerPanel.setLayout(new BorderLayout(0, 0));
		contentPanel.setLayout(new BorderLayout(0, 0));
		gridPanel.setLayout(new GridBagLayout());
		
		topPanel.setBorder(new EtchedBorder());		

		crudBar = new CrudToolBar(this);
		crudBar.setFloatable(false);

		headerPanel.add(BorderLayout.NORTH, crudBar);
		headerPanel.add(BorderLayout.SOUTH, topPanel);
		contentPanel.add(BorderLayout.CENTER, currentPanel.getPanel());		

		JLabel lbPessoa = new JLabel("Pessoa:");

		rbtFisica = new JRadioButton("Física");
		rbtJuridica = new JRadioButton("Jurídica");

		btGrPessoa = new ButtonGroup();
		btGrPessoa.add(rbtFisica);
		btGrPessoa.add(rbtJuridica);

		rbtFisica.addActionListener(this);
		rbtJuridica.addActionListener(this);

		JLabel lblCadastro = new JLabel("Data do cadastro:");
		lblCadastroDate = new JLabel();

		JLabel lblStatus = new JLabel("Status:");
		cbxStatus = new JComboBox<String>(new ClienteController().getStatusList());

		GroupLayout grTopPanel = new GroupLayout(topPanel);
		grTopPanel.setHorizontalGroup(
			grTopPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(grTopPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lbPessoa)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(rbtFisica)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(rbtJuridica)
					.addPreferredGap(ComponentPlacement.RELATED, 217, Short.MAX_VALUE)
					.addComponent(lblCadastro)
					.addGap(6)
					.addComponent(lblCadastroDate, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
					.addGap(73)
					.addComponent(lblStatus)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cbxStatus, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		grTopPanel.setVerticalGroup(
			grTopPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(grTopPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(grTopPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbPessoa)
						.addComponent(rbtFisica)
						.addComponent(rbtJuridica)
						.addComponent(lblCadastroDate)
						.addComponent(cbxStatus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblStatus)
						.addComponent(lblCadastro))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		topPanel.setLayout(grTopPanel);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);		
		tabbedPane.setBorder(new EtchedBorder());		
	
		tabObras = new TabViewObras();
		tabContatos = new TabViewContatos();
		
		tabbedPane.addTab("Obras", null, tabObras, "Obras");
		tabbedPane.addTab("Contatos", null, tabContatos, "Contatos");		
		
		cons = new GridBagConstraints();		
		cons.fill = GridBagConstraints.BOTH; 		
		cons.gridx = 0;
		cons.gridy = 0;		
		gridPanel.add(contentPanel,cons);
			
		cons.weightx = 1;  
		cons.weighty = 1;
		cons.gridx = 0;
		cons.gridy = 1;
		
		gridPanel.add(tabbedPane,cons);	    
		
		add(BorderLayout.NORTH, headerPanel);
		add(BorderLayout.CENTER, gridPanel);		
	}

	@Override
	public JPanel getPanel() {
		return this;
	}

	public Boolean getInsert(){
		return insert;
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == rbtFisica) {
			setCurrentPanel(new FormClientesFisica());

		} else if (e.getSource() == rbtJuridica) {
			setCurrentPanel(new FormClientesJuridica());

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

		crudBar.inclusao();		
		setCleanFields();
		currentPanel.setCleanFields();		
		setEnabledFields();
		currentPanel.setEnabledFields();
		currentPanel.setUnLockFk();			

		new DateUtils();
		lblCadastroDate.setText(DateUtils.formatarData(new Date()));
		
		if(tabObras.getIdCliente() != null){
			tabObras.setIdCliente(null);
			tabObras.loadModelTable();			
		}
		
		tabObras.stop();
		
		if(tabContatos.getIdCliente() != null){
			tabContatos.setIdCliente(null);
			tabContatos.loadModelTable();			
		}
		
		tabContatos.stop();
	}

	private void alterar() {
		insert = false;

		crudBar.inclusao();		
		setEnabledFields();
		currentPanel.setEnabledFields();
		tabObras.start();
		tabContatos.start();
	}

	private void excluir() {
		int excluir = JOptionPane.showConfirmDialog(null,
				"Deseja realmente excluir os dados ?", "Pedido de Exclusão",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (excluir == JOptionPane.YES_OPTION) {
			
			if(currentPanel.getPanel() instanceof FormClientesFisica) {
				FormClientesFisica cf = (FormClientesFisica) currentPanel.getPanel();				
				new ClienteFisicaController().delete(cf.getTxCpf().getText());
			} else {
				FormClientesJuridica cj = (FormClientesJuridica) currentPanel.getPanel();
				new ClienteJuridicaController().delete(cj.getTxCnpj().getText());
			}
			
			crudBar.incluir();
			currentPanel.setCleanFields();
			
			tabObras.setIdCliente(null);
			tabObras.loadModelTable();
			tabObras.stop();
			
			tabContatos.setIdCliente(null);
			tabContatos.loadModelTable();
			tabContatos.stop();			
		}
	}

	private void salvar() {

		if (currentPanel.getPanel() instanceof FormClientesFisica) {
			FormClientesFisica cf = (FormClientesFisica) currentPanel.getPanel();

			Component ignoreComponents[] = { cf.getTxTelefone(),cf.getTxEmail(), cf.getTxSite(), cf.getTxComplemento(), cf.getTxBairro() };
			if (CheckFields.checkEmptyFields(currentPanel.getPanel(), ignoreComponents)) {

				if (CheckDocuments.checkCPF(cf.getTxCpf().getText())) {

					int status = cbxStatus.getSelectedIndex();
					Date dataCadastro = DateUtils.str2Date(lblCadastroDate.getText(), DateFormat.MEDIUM);

					String nome = cf.getTxNome().getText().trim();

					String cpf = cf.getTxCpf().getText();
					String rg = cf.getTxRg().getText().trim();
					Date dataNasc = DateUtils.str2Date(cf.getTxDataNasc().getText(), DateFormat.MEDIUM);

					String celular = cf.getTxCelular().getText().trim();
					String email = cf.getTxEmail().getText().trim();

					String telefone = cf.getTxTelefone().getText().trim();
					String site = cf.getTxSite().getText().trim();

					String cep = cf.getTxCep().getText().trim();
					String endereco = cf.getTxEndereco().getText().trim();
					int numero = Integer.parseInt(cf.getTxNumero().getText());
					String complemento = cf.getTxComplemento().getText().trim();
					String bairro = cf.getTxBairro().getText().trim();

					String estado = (String) cf.getCbxUf().getSelectedItem();
					String cidade = (String) cf.getCbxCidade().getSelectedItem();

					String observacoes = cf.getTxObservacoes().getText().trim();

					if (telefone.isEmpty()) { telefone = null; }
					if (email.isEmpty()) { email = null;}
					if (site.isEmpty()) { site = null;}
					if (complemento.isEmpty()) { complemento = null;}
					if (bairro.isEmpty()) { bairro = null;}
					if (observacoes.isEmpty()) { observacoes = null;}

					if (insert) {
						int id = new ClienteController().save(dataCadastro, status,
								nome, cpf, rg, dataNasc, telefone, email,
								celular, site, cep, endereco, numero,
								complemento, bairro, estado, cidade,
								observacoes);

						insert = false;								
						tabObras.setIdCliente(id);
						tabContatos.setIdCliente(id);

						JOptionPane.showMessageDialog(null, "Cliente " + nome + " cadastrado com sucesso");
					} else {						
						new ClienteController().alter(dataCadastro, status,
								nome, cpf, rg, dataNasc, telefone, email,
								celular, site, cep, endereco, numero,
								complemento, bairro, estado, cidade,
								observacoes, null);

						tabObras.stop();
						tabContatos.stop();
						
						JOptionPane.showMessageDialog(null, "Cliente " + nome + " atualizado com sucesso");
					}	
					
					setSalvarFields();

				} else {
					cf.getTxCpf().setBackground(Color.cyan); 
					JOptionPane.showMessageDialog(null, "Verifique o valor informado em CPF");
				}

			} else {
				JOptionPane.showMessageDialog(null, "Verifique os campos obrigatórios");
			} 

		} else {
			FormClientesJuridica cj = (FormClientesJuridica) currentPanel	.getPanel();
			Component ignoreComponents[] = { cj.getTxFantasia(), cj.getTxFax(),	cj.getTxEmail(), cj.getTxSite(), cj.getTxComplemento(),	cj.getTxBairro() };

			if (CheckFields.checkEmptyFields(currentPanel.getPanel(), ignoreComponents)) {

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

					if (fantasia.isEmpty()) { fantasia = null;}
					if (telefone.isEmpty()) { telefone = null;}
					if (fax.isEmpty()) { fax = null;}
					if (email.isEmpty()) { email = null;}
					if (site.isEmpty()) { site = null;}
					if (complemento.isEmpty()) { complemento = null;}
					if (bairro.isEmpty()) {bairro = null;}
					if (observacoes.isEmpty()) {observacoes = null;}

					if (insert) {

						int id = new ClienteController().save(dataCadastro, status,
								razao, fantasia, cnpj, inscricao, fax, email,
								site, telefone, cep, endereco, numero,
								complemento, bairro, estado, cidade,
								observacoes);

						insert = false;
						tabObras.setIdCliente(id);	
						tabContatos.setIdCliente(id);

						JOptionPane.showMessageDialog(null, "Cliente " + razao
								+ " cadastrado com sucesso");
					} else {
						new ClienteController().alter(dataCadastro, status, 
								razao, fantasia, cnpj, inscricao, fax, email, 
								site, telefone, cep, endereco, numero, 
								complemento, bairro, estado, cidade,
								observacoes, null);

						tabObras.stop();
						tabContatos.stop();
						
						JOptionPane.showMessageDialog(null, "Cliente " + razao
								+ " atualizado com sucesso");
					}
					setSalvarFields();
					

				} else {
					cj.getTxCnpj().setBackground(Color.cyan);
					JOptionPane.showMessageDialog(null,	"Verifique o valor informado em CNPJ");
				}

			} else {
				JOptionPane.showMessageDialog(null, "Verifique os campos obrigatórios");
			}
		}
	}
	
	private void setSalvarFields(){
		setDisableFields();
		currentPanel.setDisableFields();
		
		rbtFisica.setEnabled(false);
		rbtJuridica.setEnabled(false);
		cbxStatus.setEnabled(false);
		
		crudBar.alterar();		
	}

	private void cancelar() {
		if (insert)	
			insert = false;

		crudBar.incluir();
		setCleanFields();
		currentPanel.setCleanFields();
		setDisableFields();
		currentPanel.setDisableFields();

		CheckFields.restoreFields(currentPanel.getPanel());
		
		if(tabObras.getIdCliente() != null){
			tabObras.setIdCliente(null);
		}
		tabObras.loadModelTable();
		tabObras.stop();
		

		if(tabContatos.getIdCliente() != null){
			tabContatos.setIdCliente(null);
		}
		tabContatos.loadModelTable();
		tabContatos.stop();
	}
	
	private void recoverFieldsSuper(int id) {
		Cliente c = new ClienteController().search(id);
		lblCadastroDate.setText(DateUtils.formatarData(c.getDataCadastro()));	
		cbxStatus.setSelectedIndex(c.getStatus());
	}

	private void restoreFieldsFisica(int id) {
		FormClientesFisica cf = (FormClientesFisica) currentPanel.getPanel();
		ClienteFisica c = new ClienteFisicaController().search(id);
		
		cf.getTxNome().setText(c.getNome());
		cf.getTxCpf().setText(c.getCpf());
		cf.getTxRg().setText(c.getRg());
		cf.getTxDataNasc().setText((DateUtils.formatarData(c.getDataNasc())));
		cf.getTxTelefone().setText(c.getTelefone());
		cf.getTxEmail().setText(c.getEmail());
		cf.getTxSite().setText(c.getSite());
		cf.getTxCelular().setText(c.getCelular());
		cf.getTxCep().setText(c.getEndereco().getCep());
		cf.getTxEndereco().setText(c.getEndereco().getLogradouro());
		cf.getTxNumero().setText(c.getEndereco().getNumero().toString());
		cf.getTxComplemento().setText(c.getEndereco().getComplemento());
		cf.getTxBairro().setText(c.getEndereco().getBairro());
		cf.getCbxUf().setSelectedItem(c.getEndereco().getCidade().getEstado().getUf());
		cf.getCbxCidade().setSelectedItem(c.getEndereco().getCidade().getNome());
		cf.getTxObservacoes().setText(c.getObservacoes());
	}

	private void restoreFieldsJuridica(int id) {
		FormClientesJuridica cj = (FormClientesJuridica) currentPanel.getPanel();
		ClienteJuridica c = new ClienteJuridicaController().search(id);

		cj.getTxRazao().setText(c.getRazao());
		cj.getTxFantasia().setText(c.getFantasia());
		cj.getTxCnpj().setText(c.getCnpj());
		cj.getTxInscricao().setText(c.getInscricao());
		cj.getTxTelefone().setText(c.getTelefone());
		cj.getTxEmail().setText(c.getEmail());
		cj.getTxFax().setText(c.getFax());
		cj.getTxSite().setText(c.getSite());
		cj.getTxCep().setText(c.getEndereco().getCep());
		cj.getTxEndereco().setText(c.getEndereco().getLogradouro());
		cj.getTxNumero().setText(c.getEndereco().getNumero().toString());
		cj.getTxComplemento().setText(c.getEndereco().getComplemento());
		cj.getTxBairro().setText(c.getEndereco().getBairro());
		cj.getCbxUf().setSelectedItem(c.getEndereco().getCidade().getEstado());
		cj.getCbxCidade().setSelectedItem(c.getEndereco().getCidade().getNome());
		cj.getTxObservacoes().setText(c.getObservacoes());
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

	@Override
	public void setLockFk() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setUnLockFk() {
		// TODO Auto-generated method stub
		
	}	
	
}
