package br.edu.ifpr.comat.ui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.edu.ifpr.comat.ui.ComatJFrame;
import br.edu.ifpr.comat.ui.components.panels.impl.ClientesFormPanel;
import br.edu.ifpr.comat.ui.components.panels.impl.ClientesViewPanel;
import br.edu.ifpr.comat.ui.components.panels.impl.FirstPanel;

public class ComatListener implements ActionListener {
	private ComatJFrame frame;

	public ComatListener(ComatJFrame frame) {
		this.frame = frame;
		adicionaListener();
	}

	public void adicionaListener() {
		frame.getMniClientes().addActionListener(this);
		frame.getMniCliente().addActionListener(this);
		frame.getMniSair().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Clientes")) {
			clientesAction();
		} else if (e.getActionCommand().equals("Cliente")) {
			clienteAction();
		} else if (e.getActionCommand().equals("Sair")) {
			sairAction();
		}
	}

	private void clientesAction() {
		if (!(frame.getCurrentPanel() instanceof ClientesViewPanel)) {
			frame.setCurrentPanel(new ClientesViewPanel());
		}
	}

	private void clienteAction() {
		if (!(frame.getCurrentPanel() instanceof ClientesFormPanel)) {
			frame.setCurrentPanel(new ClientesFormPanel());
		}
	}

	private void sairAction() {
		System.exit(0);
	}

}
