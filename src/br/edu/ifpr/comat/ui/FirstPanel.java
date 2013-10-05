package br.edu.ifpr.comat.ui;

import java.awt.Color;
import javax.swing.JPanel;

public class FirstPanel implements ComatJPanels {
	private JPanel panel;

	public FirstPanel() {
		panel = new JPanel();
		panel.setBackground(Color.CYAN);
	}

	@Override
	public JPanel getPanel() {
		return panel;
	}

}
