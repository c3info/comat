package br.edu.ifpr.comat.ui.components.panels.impl;

import javax.swing.JPanel;

import br.edu.ifpr.comat.controller.ClienteController;
import br.edu.ifpr.comat.ui.components.panels.ComatJPanels;
import br.edu.ifpr.comat.ui.utils.MaskFields;
import br.edu.ifpr.comat.ui.utils.MaxLengthFields;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EtchedBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JFormattedTextField;

public class ClientesFormFisicaPanel extends JPanel implements ComatJPanels {
	private JTextField txNome, txRg, txTelefone, txCelular,
			txEmail, txSite, txEndereco, txNumero, txComplemento,
			txBairro, txCidade;
	private JFormattedTextField txCep, txDataNasc, txCpf;
	private JTextArea txObservacoes;
	private JComboBox cbxUf;

	public ClientesFormFisicaPanel() {
		setBorder(new EtchedBorder());
		buildComponents();
		setDisableFields();
	}

	@Override
	public void buildComponents() {
		
		JLabel lblNome = new JLabel("Nome:");
		txNome = new JTextField(10);
		txNome.setDocument(new MaxLengthFields(72)); 

		JLabel lblCpf = new JLabel("CPF:");
		txCpf = new JFormattedTextField();	
		txCpf = new JFormattedTextField(MaskFields.Mascara("###.###.###-##"));

		JLabel lblRg = new JLabel("RG:");
		txRg = new JTextField(10);		

		JLabel lblDataNasc = new JLabel("Data de nascimento:");
		txDataNasc = new JFormattedTextField();	
		txDataNasc = new JFormattedTextField(MaskFields.Mascara("##/##/####"));

		JLabel lblTelefone = new JLabel("Telefone:");
		txTelefone = new JTextField(10);		

		JLabel lblCelular = new JLabel("Celular:");
		txCelular = new JTextField(10);

		JLabel lblEmail = new JLabel("e-mail:");
		txEmail = new JTextField(10);
		txEmail.setDocument(new MaxLengthFields(72));

		JLabel lblSite = new JLabel("Site:");
		txSite = new JTextField(10);
		txSite.setDocument(new MaxLengthFields(72));

		JLabel lblCep = new JLabel("CEP:");
		txCep = new JFormattedTextField(MaskFields.Mascara("##.###-###"));		

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

		JLabel lblCidade = new JLabel("Cidade:");
		txCidade = new JTextField(10);		

		JLabel lblUf = new JLabel("UF:");

		cbxUf = new JComboBox(new ClienteController().getEstados());

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
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 829, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNome)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txNome, GroupLayout.DEFAULT_SIZE, 798, Short.MAX_VALUE)
							.addGap(6))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblCpf)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txCpf, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblRg)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txRg, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblDataNasc)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txDataNasc, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblTelefone)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txTelefone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblEmail)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txEmail, GroupLayout.DEFAULT_SIZE, 643, Short.MAX_VALUE)
							.addGap(6))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblCelular)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txCelular, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblSite)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txSite, GroupLayout.DEFAULT_SIZE, 662, Short.MAX_VALUE)
							.addGap(6))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblCep)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txCep, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblEndereo)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txEndereco, GroupLayout.DEFAULT_SIZE, 506, Short.MAX_VALUE)
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
							.addComponent(lblCidade)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txCidade, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblUf)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cbxUf, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
							.addGap(34))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblObservacoes)
							.addContainerGap(679, Short.MAX_VALUE))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNome)
						.addComponent(txNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCpf)
						.addComponent(txCpf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRg)
						.addComponent(txRg, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDataNasc)
						.addComponent(txDataNasc, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTelefone)
						.addComponent(txTelefone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEmail)
						.addComponent(txEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCelular)
						.addComponent(txCelular, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
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
						.addComponent(lblCidade)
						.addComponent(txCidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblUf)
						.addComponent(cbxUf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblObservacoes)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(274, Short.MAX_VALUE))
		);

		scrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		setLayout(groupLayout);	
	}	

	public JTextField getTxNome() {
		return txNome;
	}

	public JTextField getTxRg() {
		return txRg;
	}

	public JTextField getTxTelefone() {
		return txTelefone;
	}

	public JTextField getTxCelular() {
		return txCelular;
	}

	public JTextField getTxEmail() {
		return txEmail;
	}

	public JTextField getTxSite() {
		return txSite;
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

	public JTextField getTxCidade() {
		return txCidade;
	}

	public JFormattedTextField getTxCep() {
		return txCep;
	}

	public JFormattedTextField getTxDataNasc() {
		return txDataNasc;
	}

	public JFormattedTextField getTxCpf() {
		return txCpf;
	}

	public JTextArea getTxObservacoes() {
		return txObservacoes;
	}

	@Override
	public JPanel getPanel() {
		return this;
	}	

	@Override
	public void setEnabledFields() {
		txNome.setEnabled(true);
		txCpf.setEnabled(true);
		txRg.setEnabled(true);
		txDataNasc.setEnabled(true);
		txTelefone.setEnabled(true);
		txCelular.setEnabled(true);
		txEmail.setEnabled(true);
		txSite.setEnabled(true);
		txCep.setEnabled(true);
		txEndereco.setEnabled(true);
		txNumero.setEnabled(true);
		txComplemento.setEnabled(true);
		txBairro.setEnabled(true);
		txCidade.setEnabled(true);
		cbxUf.setEnabled(true);
		txObservacoes.setEnabled(true);
		txNome.requestFocusInWindow();
	}
	
	@Override
	public void setDisableFields() {
		txNome.setEnabled(false);
		txCpf.setEnabled(false);
		txRg.setEnabled(false);
		txDataNasc.setEnabled(false);
		txTelefone.setEnabled(false);
		txCelular.setEnabled(false);
		txEmail.setEnabled(false);
		txSite.setEnabled(false);
		txCep.setEnabled(false);
		txEndereco.setEnabled(false);
		txNumero.setEnabled(false);
		txComplemento.setEnabled(false);
		txBairro.setEnabled(false);
		txCidade.setEnabled(false);
		cbxUf.setEnabled(false);
		txObservacoes.setEnabled(false);
	}
	
	@Override
	public void setCleanFields() {
		txNome.setText("");
		txCpf.setText("");
		txRg.setText("");
		txDataNasc.setText("");
		txTelefone.setText("");
		txCelular.setText("");
		txEmail.setText("");
		txSite.setText("");
		txCep.setText("");
		txEndereco.setText("");
		txNumero.setText("");
		txComplemento.setText("");
		txBairro.setText("");
		txCidade.setText("");
		//txObservacoes.setText("");
	}
		
}
