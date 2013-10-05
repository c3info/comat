package br.edu.ifpr.comat.ui;

import javax.swing.JPanel;
import java.awt.Color;

public class FormClientesPanel implements ComatJPanels {
	private JPanel panel;

	public FormClientesPanel() {
		panel = new JPanel();
		panel.setBackground(Color.ORANGE);
	}

	@Override
	public JPanel getPanel() {
		return panel;
	}

}
