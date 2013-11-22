package br.edu.ifpr.comat.ui.components.dialogs;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import br.edu.ifpr.comat.controller.ContatoController;
import br.edu.ifpr.comat.model.Contato;
import br.edu.ifpr.comat.ui.utils.CheckFields;
import br.edu.ifpr.comat.ui.utils.MaxLengthFields;

public class FormContato extends JDialog implements ActionListener {

	private Integer idCli = null;
	private Integer idCon = null;
	
	private Boolean insert = false;

	private JPanel contentPanel, buttonPane;
	private JButton btSalvar, btCancelar;
	
	private JTextField txNome, txTelefone, txCelular, txEmail, txFuncao;

	public FormContato() {
		buildComponents();		
	}	
	
	public FormContato(int id) {
		this.idCon = id;
		buildComponents();
		restoreFields(id);
	}

	private void buildComponents() {
		setResizable(false);
		setTitle("Cadastro de Contatos");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 520, 300);

		getContentPane().setLayout(new BorderLayout());

		contentPanel = new JPanel();
		contentPanel.setBorder(new EtchedBorder());
		
		JLabel lbNome = new JLabel("Nome:");		
		txNome = new JTextField(10);		
		txNome.setDocument(new MaxLengthFields(45)); 
		
		JLabel lbTelefone = new JLabel("Telefone:");		
		txTelefone = new JTextField(10);		
		txTelefone.setDocument(new MaxLengthFields(12)); 
		
		JLabel lbCelular = new JLabel("Celular:");		
		txCelular = new JTextField(10);		
		txCelular.setDocument(new MaxLengthFields(12));
		
		JLabel lbEmail = new JLabel("e-mail:");		
		txEmail = new JTextField(10);		
		txEmail.setDocument(new MaxLengthFields(72));
		
		JLabel lbFuncao = new JLabel("Função:");		
		txFuncao = new JTextField(10);		
		txFuncao.setDocument(new MaxLengthFields(45));
		

		GroupLayout grContentPanel = new GroupLayout(contentPanel);
		grContentPanel.setHorizontalGroup(
			grContentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(grContentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(grContentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(grContentPanel.createSequentialGroup()
							.addComponent(lbNome)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txNome, GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(grContentPanel.createSequentialGroup()
							.addComponent(lbTelefone)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txTelefone, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lbCelular)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txCelular, GroupLayout.DEFAULT_SIZE, 200, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(grContentPanel.createSequentialGroup()						
							.addComponent(lbEmail)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txEmail, GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(grContentPanel.createSequentialGroup()
							.addComponent(lbFuncao)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txFuncao, GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE)
							.addContainerGap())))
		);
		grContentPanel.setVerticalGroup(
			grContentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(grContentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(grContentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbNome)
						.addComponent(txNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(grContentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbTelefone)
						.addComponent(txTelefone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbCelular)
						.addComponent(txCelular, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(grContentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbEmail)
						.addComponent(txEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(grContentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbFuncao)
						.addComponent(txFuncao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(111, Short.MAX_VALUE))
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
		Component ignoreComponents[] = {txTelefone, txEmail, txFuncao};
		if (CheckFields.checkEmptyFields(contentPanel, ignoreComponents)){
			
			String nome = txNome.getText().trim();
			String telefone = txTelefone.getText().trim();
			String celular = txCelular.getText().trim();
			String email = txEmail.getText().trim();
			String funcao = txFuncao.getText().trim();
							
			//if (telefone.isEmpty()) { telefone = null;}
			//if (email.isEmpty()) { email = null;}
			//if (funcao.isEmpty()) { funcao = null;}
			
			
			if (insert) {
				new ContatoController().save(idCli, nome, telefone, celular, email, funcao);
								
				JOptionPane.showMessageDialog(null, "Contato " + nome + " cadastrado com sucesso");
			} else {	
				new ContatoController().alter(idCon, nome, telefone, celular, email, funcao);
					
				JOptionPane.showMessageDialog(null, "Contato " + nome + " atualizado com sucesso");
			}
			
			dispose();
			
		} else {
			JOptionPane.showMessageDialog(null, "Verifique os campos obrigatórios");
		} 		
	}
	
	private void restoreFields(int id) {
		Contato con = new ContatoController().search(id);
		
		txNome.setText(con.getNome());
		txTelefone.setText(con.getTelefone());
		txCelular.setText(con.getCelular());
		txEmail.setText(con.getEmail());
		txFuncao.setText(con.getFuncao());				
		
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
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btCancelar) {			
			dispose();

		} else if (e.getSource() == btSalvar) {
			salvar();
			
		}
	}
}
