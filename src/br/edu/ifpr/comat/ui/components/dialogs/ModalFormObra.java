package br.edu.ifpr.comat.ui.components.dialogs;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EtchedBorder;

import br.edu.ifpr.comat.controller.CidadeController;
import br.edu.ifpr.comat.controller.EstadoController;
import br.edu.ifpr.comat.controller.ObraController;
import br.edu.ifpr.comat.model.Obra;
import br.edu.ifpr.comat.ui.utils.CheckEmptyFields;
import br.edu.ifpr.comat.ui.utils.MaskFields;
import br.edu.ifpr.comat.ui.utils.MaxLengthFields;

public class ModalFormObra extends JDialog implements ActionListener {

	private Integer idCli = null;
	private Integer idObra = null;

	private Boolean insert = false;

	private JPanel contentPanel, buttonPane;
	private JButton btSalvar, btCancelar;

	private JTextField txNome, txTelefone, txResponsavel, txEndereco, txNumero,
			txComplemento, txBairro;
	private JFormattedTextField txCep;

	JComboBox<String> cbxUf, cbxCidade;
	private String[] estados, cidades;

	public ModalFormObra() {
		buildComponents();
	}

	public ModalFormObra(int idObra) {
		this.idObra = idObra;
		buildComponents();
		restoreFields(idObra);
	}

	private void buildComponents() {
		setResizable(false);
		setTitle("Cadastro de Obras");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 520, 300);

		getContentPane().setLayout(new BorderLayout());

		contentPanel = new JPanel();
		contentPanel.setBorder(new EtchedBorder());

		JLabel lbNomeApelido = new JLabel("Nome / Apelido:");
		txNome = new JTextField(10);
		txNome.setDocument(new MaxLengthFields(45));

		JLabel lbTelefone = new JLabel("Telefone:");
		txTelefone = new JTextField(10);
		txTelefone.setDocument(new MaxLengthFields(12));

		JLabel lbResponsavel = new JLabel("Responsável:");
		txResponsavel = new JTextField(10);
		txResponsavel.setDocument(new MaxLengthFields(72));

		JLabel lbCep = new JLabel("Cep:");
		txCep = new JFormattedTextField(MaskFields.marcarate("##.###-###"));

		JLabel lbEndereo = new JLabel("Endereço:");
		txEndereco = new JTextField(10);
		txEndereco.setDocument(new MaxLengthFields(45));

		JLabel lbNumero = new JLabel("N.:");
		txNumero = new JTextField(10);

		JLabel lbComplemento = new JLabel("Complemento:");
		txComplemento = new JTextField(10);
		txComplemento.setDocument(new MaxLengthFields(45));

		JLabel lbBairro = new JLabel("Bairro:");
		txBairro = new JTextField(10);
		txBairro.setDocument(new MaxLengthFields(45));

		JLabel lbUf = new JLabel("UF:");
		estados = new EstadoController().getEstadosStringVet();
		cbxUf = new JComboBox(estados);
		cbxUf.setSelectedItem("PR");
		cbxUf.addActionListener(this);

		JLabel lbCidade = new JLabel("Cidade:");
		cidades = new CidadeController().getCidadesVetString("PR");
		cbxCidade = new JComboBox(cidades);

		GroupLayout grContentPanel = new GroupLayout(contentPanel);
		grContentPanel.setHorizontalGroup(
			grContentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(grContentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(grContentPanel.createParallelGroup(Alignment.LEADING)						
							.addGroup(grContentPanel.createSequentialGroup()
							.addComponent(lbNomeApelido)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txNome, GroupLayout.PREFERRED_SIZE, 300, Short.MAX_VALUE)
							.addGap(6))							
						.addGroup(grContentPanel.createSequentialGroup()
							.addComponent(lbTelefone)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txTelefone, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lbResponsavel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txResponsavel, GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
							.addGap(6))							
						.addGroup(grContentPanel.createSequentialGroup()
							.addComponent(lbCep)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txCep, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lbEndereo)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txEndereco, GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(lbNumero)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txNumero, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
							.addGap(6))							
						.addGroup(grContentPanel.createSequentialGroup()
							.addComponent(lbComplemento)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txComplemento, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
							.addGap(6))							
						.addGroup(grContentPanel.createSequentialGroup()
							.addComponent(lbBairro)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txBairro, GroupLayout.PREFERRED_SIZE, 120, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(lbUf)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cbxUf, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lbCidade)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cbxCidade, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
							.addGap(6))
				)
			)
		);
		grContentPanel.setVerticalGroup(
			grContentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(grContentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(grContentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbNomeApelido)
						.addComponent(txNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(grContentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbTelefone)
						.addComponent(txTelefone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbResponsavel)
						.addComponent(txResponsavel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(grContentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txCep, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbEndereo)
						.addComponent(txEndereco, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbNumero)
						.addComponent(txNumero, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbCep))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(grContentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbComplemento)
						.addComponent(txComplemento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(grContentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbBairro)
						.addComponent(txBairro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbUf)
						.addComponent(cbxUf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbCidade)
						.addComponent(cbxCidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(80, Short.MAX_VALUE))
		);
		contentPanel.setLayout(grContentPanel);

		buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));

		btSalvar = new JButton("Salvar");
		btSalvar.addActionListener(this);
		buttonPane.add(btSalvar);
		getRootPane().setDefaultButton(btSalvar);

		btCancelar = new JButton("Cancelar");
		btCancelar.addActionListener(this);
		buttonPane.add(btCancelar);

		getContentPane().add(contentPanel, BorderLayout.CENTER);
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

	}

	private void salvar() {
		Component ignoreComponents[] = { txTelefone, txResponsavel,
				txComplemento, txBairro };
		if (CheckEmptyFields.checkEmptyFields(contentPanel, ignoreComponents)) {

			String nome = txNome.getText().trim();
			String telefone = txTelefone.getText().trim();
			String responsavel = txResponsavel.getText().trim();

			String cep = txCep.getText();
			String endereco = txEndereco.getText().trim();
			int numero = Integer.parseInt(txNumero.getText());
			String complemento = txComplemento.getText().trim();
			String bairro = txBairro.getText().trim();

			String estado = (String) cbxUf.getSelectedItem();
			String cidade = (String) cbxCidade.getSelectedItem();

			if (complemento.isEmpty()) { complemento = null;}
			if (bairro.isEmpty()) {	bairro = null;}

			if (insert) {
				idObra = new ObraController().save(idCli, nome, telefone, responsavel,
						cep, endereco, numero, complemento, bairro, estado,
						cidade);

				//JOptionPane.showMessageDialog(null, "Obra " + nome + " cadastrada com sucesso");
			} else {
				new ObraController().alter(idObra, nome, telefone, responsavel,
						cep, endereco, numero, complemento, bairro, estado,
						cidade);

				//JOptionPane.showMessageDialog(null, "Obra " + nome + " atualizada com sucesso");
			}

			dispose();

		} else {
			JOptionPane.showMessageDialog(null,
					"Verifique os campos obrigatórios");
		}

	}

	private void restoreFields(int id) {
		Obra o = new ObraController().search(id);
		txNome.setText(o.getNome());
		txTelefone.setText(o.getTelefone());
		txResponsavel.setText(o.getResponsavel());

		txCep.setText(o.getEndereco().getCep());
		txEndereco.setText(o.getEndereco().getLogradouro());
		txNumero.setText(o.getEndereco().getNumero().toString());
		txComplemento.setText(o.getEndereco().getComplemento());
		txBairro.setText(o.getEndereco().getBairro());
		cbxUf.setSelectedItem(o.getEndereco().getCidade().getEstado().getUf());
		cbxCidade.setSelectedItem(o.getEndereco().getCidade().getNome());
	}

	public void setIdCli(Integer id) {
		this.idCli = id;
	}

	public Boolean getInsert() {
		return insert;
	}

	public void setInsert(Boolean insert) {
		this.insert = insert;
	}

	private void setCbxCidade(String estado) {
		String[] cidades = new CidadeController().getCidadesVetString(estado);
		cbxCidade.setModel(new JComboBox<>(cidades).getModel());
	}
	
	public Integer getIdObra() {
		return idObra;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cbxUf) {
			JComboBox cb = (JComboBox) e.getSource();
			String estado = (String) cb.getSelectedItem();
			setCbxCidade(estado);
		} else if (e.getSource() == btCancelar) {
			dispose();

		} else if (e.getSource() == btSalvar) {
			salvar();

		}
	}
}
