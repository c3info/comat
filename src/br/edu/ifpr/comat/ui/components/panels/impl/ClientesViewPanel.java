package br.edu.ifpr.comat.ui.components.panels.impl;

import javax.swing.JPanel;
import java.awt.Color;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.JComboBox;

import br.edu.ifpr.comat.ui.components.panels.ComatJPanels;


public class ClientesViewPanel extends JPanel implements ComatJPanels {

	public ClientesViewPanel() {
		buildComponents();
	}

	@Override
	public void buildComponents() {

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
