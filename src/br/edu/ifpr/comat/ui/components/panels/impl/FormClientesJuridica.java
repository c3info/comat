package br.edu.ifpr.comat.ui.components.panels.impl;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EtchedBorder;

import br.edu.ifpr.comat.controller.CidadeController;
import br.edu.ifpr.comat.controller.EstadoController;
import br.edu.ifpr.comat.ui.components.panels.ComatJPanels;
import br.edu.ifpr.comat.ui.utils.MaskFields;
import br.edu.ifpr.comat.ui.utils.MaxLengthFields;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFormattedTextField;

public class FormClientesJuridica extends JPanel implements ComatJPanels, ActionListener {
	private JTextField txRazao, txFantasia, txInscricao, txTelefone, txFax,
			txEmail, txSite,  txEndereco, txNumero, txComplemento,
			txBairro;
	private JFormattedTextField txCnpj, txCep;
	private JTextArea txObservacoes;	
	private JComboBox<String> cbxUf, cbxCidade;
	private String[] estados, cidades;
	
	public FormClientesJuridica() {
		setBorder(new EtchedBorder());
		buildComponents();
		setDisableFields();
	}
		
	private void buildComponents() {
		
		JLabel lblRazao = new JLabel("Razão social:");
		txRazao = new JTextField(10);
		txRazao.setDocument(new MaxLengthFields(72));
		
		JLabel lblFantasia = new JLabel("Fantasia:");		
		txFantasia = new JTextField(10);
		txFantasia.setDocument(new MaxLengthFields(72));

		JLabel lblCnpj = new JLabel("CNPJ:");
		txCnpj = new JFormattedTextField(MaskFields.marcarate("##.###.###/####-##"));	

		JLabel lblInscricao = new JLabel("Inscrição Est.:");
		txInscricao = new JTextField(10);	
		txInscricao.setDocument(new MaxLengthFields(12));

		JLabel lblTelefone = new JLabel("Telefone:");
		txTelefone = new JTextField(10);
		txTelefone.setDocument(new MaxLengthFields(12)); 

		JLabel lblFax = new JLabel("Fax:");
		txFax = new JTextField(10);
		txFax.setDocument(new MaxLengthFields(12)); 
		
		JLabel lblEmail = new JLabel("e-mail:");
		txEmail = new JTextField(10);
		txEmail.setDocument(new MaxLengthFields(72));

		JLabel lblSite = new JLabel("Site:");
		txSite = new JTextField(10);
		txSite.setDocument(new MaxLengthFields(72));

		JLabel lblCep = new JLabel("CEP:");
		txCep = new JFormattedTextField(MaskFields.marcarate("##.###-###"));		

		JLabel lblEndereo = new JLabel("Endereço:");
		txEndereco = new JTextField(10);
		txEndereco.setDocument(new MaxLengthFields(45));		

		JLabel lblNmero = new JLabel("Número:");
		txNumero = new JTextField(10);		

		JLabel lblComplemento = new JLabel("Complemento:");
		txComplemento = new JTextField(10);	
		txComplemento.setDocument(new MaxLengthFields(45));

		JLabel lblBairro = new JLabel("Bairro:");
		txBairro = new JTextField(10);
		txBairro.setDocument(new MaxLengthFields(45));		

		JLabel lblUf = new JLabel("UF:");		
		estados = new EstadoController().getEstadosStringVet();			
		cbxUf = new JComboBox<String>(estados);
		cbxUf.setSelectedItem("PR");		
		cbxUf.addActionListener(this);
		
		JLabel lblCidade = new JLabel("Cidade:");		
		cidades = new CidadeController().getCidadesPorEstado("PR");			
		cbxCidade = new JComboBox<String>(cidades);					

		JLabel lblObservacoes = new JLabel("Observações:");
		txObservacoes = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(txObservacoes);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 756, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblRazao)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txRazao, GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(lblFantasia)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txFantasia, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
							.addGap(6))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblCnpj)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txCnpj, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblInscricao)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txInscricao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblTelefone)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txTelefone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblEmail)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txEmail, GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE)
							.addGap(6))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblFax)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txFax, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblSite)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txSite, GroupLayout.DEFAULT_SIZE, 604, Short.MAX_VALUE)
							.addGap(6))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblCep)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txCep, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblEndereo)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txEndereco, GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(lblNmero)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txNumero, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
							.addGap(6))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblComplemento)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txComplemento, GroupLayout.PREFERRED_SIZE, 200, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(lblBairro)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txBairro, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblUf)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cbxUf, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblCidade)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cbxCidade, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
							.addGap(6))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblObservacoes)
							.addContainerGap(699, Short.MAX_VALUE))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRazao)
						.addComponent(txRazao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblFantasia)
						.addComponent(txFantasia, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCnpj)
						.addComponent(txCnpj, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblInscricao)
						.addComponent(txInscricao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTelefone)
						.addComponent(txTelefone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEmail)
						.addComponent(txEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFax)
						.addComponent(txFax, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSite)
						.addComponent(txSite, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCep)
						.addComponent(txCep, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEndereo)
						.addComponent(txEndereco, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNmero)
						.addComponent(txNumero, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblComplemento)
						.addComponent(txComplemento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblBairro)
						.addComponent(txBairro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblUf)
						.addComponent(cbxUf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCidade)
						.addComponent(cbxCidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblObservacoes)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);

		scrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		setLayout(groupLayout);

	}

	@Override
	public JPanel getPanel() {
		return this;
	}	
	
	public JTextField getTxRazao() {
		return txRazao;
	}

	public JTextField getTxFantasia() {
		return txFantasia;
	}

	public JFormattedTextField getTxCnpj() {
		return txCnpj;
	}

	public JTextField getTxInscricao() {
		return txInscricao;
	}

	public JTextField getTxTelefone() {
		return txTelefone;
	}

	public JTextField getTxEmail() {
		return txEmail;
	}
	
	public JTextField getTxFax() {
		return txFax;
	}

	public JTextField getTxSite() {
		return txSite;
	}

	public JFormattedTextField getTxCep() {
		return txCep;
	}
	
	public JTextField getTxEndereco() {
		return txEndereco;
	}

	public JTextField getTxNumero() {
		return txNumero;
	}

	public JTextField getTxComplemento() {
		return txComplemento;
	}

	public JTextField getTxBairro() {
		return txBairro;
	}	

	public JComboBox<String> getCbxUf() {
		return cbxUf;
	}

	public JComboBox<String> getCbxCidade() {
		return cbxCidade;
	}	
	
	private void setCbxCidade(String estado) {
		String[] cidades = new CidadeController().getCidadesPorEstado(estado);
		cbxCidade.setModel(new JComboBox<>(cidades).getModel());
		
		cbxCidade.setSelectedIndex(0);
	}
	
	public JTextArea getTxObservacoes() {
		return txObservacoes;
	}

	@Override
	public void setEnabledFields() {
		txRazao.setEnabled(true);
		txFantasia.setEnabled(true);
		txInscricao.setEnabled(true);
		txCnpj.setEnabled(true);		
		txTelefone.setEnabled(true);
		txFax.setEnabled(true);		
		txEmail.setEnabled(true);
		txSite.setEnabled(true);
		txCep.setEnabled(true);
		txEndereco.setEnabled(true);
		txNumero.setEnabled(true);
		txComplemento.setEnabled(true);
		txBairro.setEnabled(true);
		cbxCidade.setEnabled(true);
		cbxUf.setEnabled(true);
		txObservacoes.setEnabled(true);
		txRazao.requestFocusInWindow();	
	}

	@Override
	public void setDisableFields() {
		txRazao.setEnabled(false);
		txFantasia.setEnabled(false);
		txInscricao.setEnabled(false);
		txCnpj.setEnabled(false);		
		txTelefone.setEnabled(false);
		txFax.setEnabled(false);		
		txEmail.setEnabled(false);
		txSite.setEnabled(false);
		txCep.setEnabled(false);
		txEndereco.setEnabled(false);
		txNumero.setEnabled(false);
		txComplemento.setEnabled(false);
		txBairro.setEnabled(false);
		cbxCidade.setEnabled(false);
		cbxUf.setEnabled(false);
		txObservacoes.setEnabled(false);		
	}

	@Override
	public void setCleanFields() {
		txRazao.setText("");
		txFantasia.setText("");
		txInscricao.setText("");
		txCnpj.setText("");		
		txTelefone.setText("");
		txFax.setText("");		
		txEmail.setText("");
		txSite.setText("");
		txCep.setText("");
		txEndereco.setText("");
		txNumero.setText("");
		txComplemento.setText("");
		txBairro.setText("");		
		txObservacoes.setText(""); 		
	}
	
	@Override
	public void setLockFk() {
		txCnpj.setEditable(false);		
	}

	@Override
	public void setUnLockFk() {
		txCnpj.setEditable(true);	
	}	

	@Override
	public void actionPerformed(ActionEvent e) {
		JComboBox cb = (JComboBox) e.getSource();
		String estado = (String) cb.getSelectedItem();
		setCbxCidade(estado);
	}
}
