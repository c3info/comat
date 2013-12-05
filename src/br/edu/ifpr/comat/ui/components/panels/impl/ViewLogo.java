package br.edu.ifpr.comat.ui.components.panels.impl;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import br.edu.ifpr.comat.ui.components.panels.ComatJPanels;

public class ViewLogo extends JPanel implements ComatJPanels {	

	public ViewLogo() {
		setLayout(new BorderLayout(0, 0));

		JButton btLogo = new JButton("");
		btLogo.setPreferredSize(new Dimension(593, 121));
		btLogo.setIcon(new ImageIcon(ViewLogo.class.getResource("/br/edu/ifpr/comat/ui/images/logo.png")));
		btLogo.setContentAreaFilled(false);
		add(btLogo);
		buildComponents();

	}

	private void buildComponents() {

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

}
