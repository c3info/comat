package br.edu.ifpr.comat.ui.components.panels.impl;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EtchedBorder;
import javax.swing.JComboBox;

import br.edu.ifpr.comat.controller.ClienteController;
import br.edu.ifpr.comat.ui.components.panels.ComatJPanels;
import java.awt.ScrollPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ClientesViewPanel extends JPanel implements ComatJPanels,
		ActionListener {
	
	private JPanel topPanel, bottomPanel;
	private JScrollPane contentPanel;

	private ButtonGroup btGrPessoa;

	private JComboBox<String> cbxStatus;
	private JTable table;

	public ClientesViewPanel() {
		setLayout(new BorderLayout(0, 0));
		buildComponents();
	}

	private void buildComponents() {
		topPanel = new JPanel();
		contentPanel = new JScrollPane();
		bottomPanel = new JPanel();
		

		topPanel.setBorder(new EtchedBorder());

		btGrPessoa = new ButtonGroup();

		cbxStatus = new JComboBox(new ClienteController().getStatusList());
		
		JLabel lblStatjs = new JLabel("Status:");

		GroupLayout gl_topPanel = new GroupLayout(topPanel);
		gl_topPanel.setHorizontalGroup(
			gl_topPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_topPanel.createSequentialGroup()
					.addContainerGap(286, Short.MAX_VALUE)
					.addComponent(lblStatjs)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(cbxStatus, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_topPanel.setVerticalGroup(
			gl_topPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_topPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_topPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(cbxStatus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblStatjs))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		topPanel.setLayout(gl_topPanel);

		add(BorderLayout.NORTH, topPanel);
		add(BorderLayout.CENTER, contentPanel);
		
		table = new JTable();
		contentPanel.setViewportView(table);
		add(BorderLayout.SOUTH, bottomPanel);
		
		table.setModel(new javax.swing.table.DefaultTableModel(
		            new Object [][] {
		                {null, null, null, null},
		                {null, null, null, null},
		                {null, null, null, null},
		                {null, null, null, null}
		            },
		            new String [] {
		                "Nome / Razão", "Documento ", "Title 3", "Title 4"
		            }
		        ));
		// topPanel.setPreferredSize(new Dimension(760, 40));
	}

	@Override
	public JPanel getPanel() {
		return this;
	}

	public void actionPerformed(ActionEvent e) {
	}

	@Override
	public void setEnabledFields() {
		cbxStatus.setEnabled(true);
	}

	@Override
	public void setDisableFields() {
		cbxStatus.setEnabled(false);
	}

	@Override
	public void setCleanFields() {

	}
}
