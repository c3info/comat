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

import br.edu.ifpr.comat.ui.components.panels.ComatJPanels;

public class ClientesFormJuridicaPanel extends JPanel implements ComatJPanels {
	private JTextField txRazao, txCnpj, txInscricao, txTelefone, txFax,
			txEmail, txSite, txCep, txEndereco, txNumero, txComplemento,
			txBairro, txCidade;
	private JTextArea txObservacoes;
	private JTextField txFantasia;

	public ClientesFormJuridicaPanel() {

		setBorder(new EtchedBorder());
		buildComponents();
	}

	@Override
	public void buildComponents() {
		JLabel lblRazao = new JLabel("Raz\u00E3o social:");

		txRazao = new JTextField();
		txRazao.setColumns(10);

		JLabel lblCnpj = new JLabel("CNPJ:");

		txCnpj = new JTextField();
		txCnpj.setColumns(10);

		JLabel lblInscricao = new JLabel("Inscri\u00E7\u00E3o Est.:");

		txInscricao = new JTextField();
		txInscricao.setColumns(10);

		JLabel lblTelefone = new JLabel("Telefone:");

		txTelefone = new JTextField();
		txTelefone.setColumns(10);

		JLabel lblFax = new JLabel("Fax:");

		txFax = new JTextField();
		txFax.setColumns(10);

		JLabel lblEmail = new JLabel("e-mail:");

		txEmail = new JTextField();
		txEmail.setColumns(10);

		JLabel lblSite = new JLabel("Site:");

		txSite = new JTextField();
		txSite.setColumns(10);

		JLabel lblCep = new JLabel("CEP:");

		txCep = new JTextField();
		txCep.setColumns(10);

		JLabel lblEndereo = new JLabel("Endere\u00E7o:");

		txEndereco = new JTextField();
		txEndereco.setColumns(10);

		JLabel lblNmero = new JLabel("N\u00FAmero:");

		txNumero = new JTextField();
		txNumero.setColumns(10);

		JLabel lblComplemento = new JLabel("Complemento:");

		txComplemento = new JTextField();
		txComplemento.setColumns(10);

		JLabel lblBairro = new JLabel("Bairro:");

		txBairro = new JTextField();
		txBairro.setColumns(10);

		JLabel lblCidade = new JLabel("Cidade:");

		txCidade = new JTextField();
		txCidade.setColumns(10);

		JLabel lblUf = new JLabel("UF:");

		JComboBox cbxUf = new JComboBox();

		JLabel lblObservaes = new JLabel("Observa\u00E7\u00F5es:");

		txObservacoes = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(txObservacoes);
		
		JLabel lblFantasia = new JLabel("Fantasia:");
		
		txFantasia = new JTextField();
		txFantasia.setColumns(10);

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
							.addComponent(txCnpj, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
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
							.addComponent(txCep, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
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
							.addComponent(lblCidade)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txCidade, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblUf)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cbxUf, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
							.addGap(34))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblObservaes)
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
						.addComponent(lblCidade)
						.addComponent(txCidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblUf)
						.addComponent(cbxUf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblObservaes)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(134, Short.MAX_VALUE))
		);

		scrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		setLayout(groupLayout);

	}

	@Override
	public JPanel getPanel() {
		return this;
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
