package br.edu.ifpr.comat.ui;

import javax.swing.JPanel;
import java.awt.Color;

public class ViewClientesPanel implements ComatJPanels {
	private JPanel panel;

	public ViewClientesPanel() {
		panel = new JPanel();
		panel.setBackground(Color.GREEN);
	}

	@Override
	public JPanel getPanel() {
		return panel;
	}

}
