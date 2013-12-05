package br.edu.ifpr.comat.ui.components.dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EtchedBorder;

import br.edu.ifpr.comat.controller.ClienteController;
import br.edu.ifpr.comat.ui.components.panels.ComatJPanels;
import br.edu.ifpr.comat.ui.components.panels.impl.FormClienteFisica;
import br.edu.ifpr.comat.ui.components.panels.impl.FormClienteJuridica;
import br.edu.ifpr.comat.ui.utils.CheckEmptyFields;
import br.edu.ifpr.comat.util.CheckDocuments;
import br.edu.ifpr.comat.util.DateUtils;

public class ModalFormCliente extends JDialog implements ActionListener {

	private Integer idCli = null;

	private ComatJPanels currentPanel;
	private JPanel topPanel, contentPanel, buttonPane;
	private JButton btSalvar, btCancelar;

	private JLabel lblCadastroDate;
	private ButtonGroup btGrPessoa;
	private JRadioButton rbtFisica, rbtJuridica;
	private JComboBox<String> cbxStatus;
	
	public ModalFormCliente() {
		buildComponents();
	}

	private void buildComponents() {
		setResizable(false);
		setTitle("Cadastro de clientes");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 780, 480);

		getContentPane().setLayout(new BorderLayout());
		
		topPanel = new JPanel();
		topPanel.setBorder(new EtchedBorder());
		
		JLabel lbPessoa = new JLabel("Pessoa:");

		rbtFisica = new JRadioButton("Física");
		rbtJuridica = new JRadioButton("Jurídica");

		btGrPessoa = new ButtonGroup();
		btGrPessoa.add(rbtFisica);
		btGrPessoa.add(rbtJuridica);
		
		rbtFisica.addActionListener(this);
		rbtJuridica.addActionListener(this);
		rbtFisica.setSelected(true);

		JLabel lblCadastro = new JLabel("Data do cadastro:");
		
		new DateUtils();
		lblCadastroDate = new JLabel(DateUtils.formatarData(new Date()));

		JLabel lblStatus = new JLabel("Status:");
		cbxStatus = new JComboBox(new ClienteController().getClienteStatusVetString());
		cbxStatus.setEnabled(false);
		
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

		contentPanel = new JPanel();
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		currentPanel = new FormClienteFisica();
		contentPanel.add(BorderLayout.CENTER, currentPanel.getPanel());
		currentPanel.setEnabledFields();
				
		buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));

		btSalvar = new JButton("Salvar");
		btSalvar.addActionListener(this);
		buttonPane.add(btSalvar);		

		btCancelar = new JButton("Cancelar");
		btCancelar.addActionListener(this);
		buttonPane.add(btCancelar);		
		
		getContentPane().add(topPanel,BorderLayout.NORTH);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

	}

	private void salvar() {
		if (currentPanel.getPanel() instanceof FormClienteFisica) {
			FormClienteFisica cf = (FormClienteFisica) currentPanel
					.getPanel();

			Component ignoreComponents[] = { cf.getTxTelefone(), cf.getTxEmail(), cf.getTxSite(), cf.getTxComplemento(), cf.getTxBairro() };
			if (CheckEmptyFields.checkEmptyFields(currentPanel.getPanel(), ignoreComponents)) {

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
					String cidade = (String) cf.getCbxCidade()
							.getSelectedItem();

					String observacoes = cf.getTxObservacoes().getText().trim();

					if (site.isEmpty()) {
						site = null;
					}
					if(email.isEmpty()){
						email = null;
					}	
					if (complemento.isEmpty()) {
						complemento = null;
					}
					if (bairro.isEmpty()) {
						bairro = null;
					}
					if (observacoes.isEmpty()) {
						observacoes = null;
					}
					
					idCli = new ClienteController().save(dataCadastro,
					status, nome, cpf, rg, dataNasc, telefone,
					email, celular, site, cep, endereco, numero,
					complemento, bairro, estado, cidade,
					observacoes);						
												
					JOptionPane.showMessageDialog(null, "Cliente " + nome + " cadastrado com sucesso");
					dispose();			

				} else {
					cf.getTxCpf().setBackground(Color.cyan);
					JOptionPane.showMessageDialog(null,	"Verifique o valor informado em CPF");
				}

			} else {
				JOptionPane.showMessageDialog(null, "Verifique os campos obrigatórios");
			}

		} else {
			FormClienteJuridica cj = (FormClienteJuridica) currentPanel
					.getPanel();
			Component ignoreComponents[] = { cj.getTxFantasia(), cj.getTxFax(), cj.getTxEmail(), cj.getTxSite(), cj.getTxComplemento(), cj.getTxBairro() };

			if (CheckEmptyFields.checkEmptyFields(currentPanel.getPanel(), ignoreComponents)) {

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
					String cidade = (String) cj.getCbxCidade()
							.getSelectedItem();

					String observacoes = cj.getTxObservacoes().getText().trim();

					if (fantasia.isEmpty()) {
						fantasia = null;
					}										
					if (site.isEmpty()) {
						site = null;
					}
					if(email.isEmpty()){
						email = null;
					}	
					if (complemento.isEmpty()) {
						complemento = null;
					}
					if (bairro.isEmpty()) {
						bairro = null;
					}
					if (observacoes.isEmpty()) {
						observacoes = null;
					}		

					idCli = new ClienteController().save(dataCadastro,
					status, razao, fantasia, cnpj, inscricao, fax,
					email, site, telefone, cep, endereco, numero,
					complemento, bairro, estado, cidade,
					observacoes);
					
					JOptionPane.showMessageDialog(null, "Cliente " + razao + " cadastrado com sucesso");					
					dispose();

				} else {
					cj.getTxCnpj().setBackground(Color.cyan);
					JOptionPane.showMessageDialog(null, "Verifique o valor informado em CNPJ");
				}

			} else {
				JOptionPane.showMessageDialog(null, "Verifique os campos obrigatórios");
			}
		}

	}

	public Integer getIdCli() {
		return idCli;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == rbtFisica) {
			setCurrentPanel(new FormClienteFisica());

		} else if (e.getSource() == rbtJuridica) {
			setCurrentPanel(new FormClienteJuridica());

		} else if (e.getSource() == btCancelar) {
			dispose();

		} else if (e.getSource() == btSalvar) {
			salvar();

		}
	}
	
	public void setCurrentPanel(ComatJPanels set) {
		contentPanel.removeAll();
		currentPanel = set;
		contentPanel.add(BorderLayout.CENTER, currentPanel.getPanel());
		currentPanel.setEnabledFields();
		validate();
		repaint();
	}
}
