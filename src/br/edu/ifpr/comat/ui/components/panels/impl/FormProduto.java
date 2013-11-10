package br.edu.ifpr.comat.ui.components.panels.impl;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import br.edu.ifpr.comat.ui.components.panels.ComatJPanels;
import br.edu.ifpr.comat.ui.components.toolbars.CrudToolBar;

public class FormProduto extends JPanel implements ComatJPanels, ActionListener {
	private Boolean insert = false;
	private JPanel contentPanel;

	private CrudToolBar crudBar;

	public FormProduto() {
		buildComponents();
	}

	private void buildComponents() {
		setLayout(new BorderLayout(0, 0));

		contentPanel = new JPanel();

		crudBar = new CrudToolBar(this);
		crudBar.setFloatable(false);

		add(BorderLayout.NORTH, crudBar);
		add(BorderLayout.CENTER, contentPanel);

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

	@Override
	public void setLockFk() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setUnLockFk() {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

}
