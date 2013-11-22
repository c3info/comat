package br.edu.ifpr.comat.ui.components;

import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Contatos extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Create the panel.
	 */
	public Contatos() {
		
		JPanel form = new JPanel();
		JPanel table = new JPanel();
		
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,form, table);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		table.add(lblNewLabel_1);
		setLayout(new BorderLayout(0, 0));
		
		splitPane.setContinuousLayout(true); 
        splitPane.setOneTouchExpandable(true); 
        form.setMinimumSize(new Dimension(200, 200));
        
        JLabel lblNome = new JLabel("Nome:");
        
        textField = new JTextField();
        textField.setColumns(10);
        
        JLabel lblTelefone = new JLabel("Telefone:");
        
        textField_1 = new JTextField();
        textField_1.setColumns(10);
        
        JLabel lblCelular = new JLabel("Celular:");
        
        textField_2 = new JTextField();
        textField_2.setColumns(10);
        
        JLabel lblEmail = new JLabel("e-mail: ");
        
        textField_3 = new JTextField();
        textField_3.setColumns(10);
        
        JLabel lblFuno = new JLabel("Função:");
        
        textField_4 = new JTextField();
        textField_4.setColumns(10);
        GroupLayout gl_form = new GroupLayout(form);
        gl_form.setHorizontalGroup(
        	gl_form.createParallelGroup(Alignment.TRAILING)
        		.addGroup(gl_form.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(gl_form.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_form.createSequentialGroup()
        					.addComponent(lblNome)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(textField, GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE))
        				.addGroup(gl_form.createSequentialGroup()
        					.addComponent(lblTelefone)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(lblCelular)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        				.addGroup(gl_form.createSequentialGroup()
        					.addComponent(lblEmail)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        				.addGroup(gl_form.createSequentialGroup()
        					.addComponent(lblFuno)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
        			.addContainerGap())
        );
        gl_form.setVerticalGroup(
        	gl_form.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_form.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(gl_form.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblNome)
        				.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addGroup(gl_form.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblTelefone)
        				.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lblCelular)
        				.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addGroup(gl_form.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblEmail)
        				.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addGroup(gl_form.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblFuno)
        				.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addContainerGap(180, Short.MAX_VALUE))
        );
        form.setLayout(gl_form);
 
        splitPane.setDividerLocation(400); 
		add(splitPane);

	}
}
