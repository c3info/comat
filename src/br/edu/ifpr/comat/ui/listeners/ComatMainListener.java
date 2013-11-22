package br.edu.ifpr.comat.ui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import br.edu.ifpr.comat.ui.ComatMainFrame;
import br.edu.ifpr.comat.ui.components.panels.impl.FormClientes;
import br.edu.ifpr.comat.ui.components.panels.impl.FormOrcamento;
import br.edu.ifpr.comat.ui.components.panels.impl.FormProduto;
import br.edu.ifpr.comat.ui.components.panels.impl.ViewClientes;
import br.edu.ifpr.comat.ui.components.panels.impl.ViewOrcamentos;
import br.edu.ifpr.comat.ui.components.panels.impl.ViewProdutos;

public class ComatMainListener implements ActionListener {
	private ComatMainFrame frame;

	public ComatMainListener(ComatMainFrame frame) {
		this.frame = frame;
		adicionaListener();
	}

	public void adicionaListener() {
		frame.getMniClientes().addActionListener(this);
		frame.getMniCliente().addActionListener(this);
		frame.getMniSair().addActionListener(this);
		frame.getMniProduto().addActionListener(this);
		frame.getMniProdutos().addActionListener(this);
		frame.getMniOrcamento().addActionListener(this);
		frame.getMniOrcamentos().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Clientes")) {
			clientesAction();
		} else if (e.getActionCommand().equals("Orçamentos")) {
			orcamentosAction();
		} else if (e.getActionCommand().equals("Produtos")) {
			produtosAction();
		} else if (e.getActionCommand().equals("Cliente")) {
			clienteAction();
		} else if (e.getActionCommand().equals("Orçamento")) {
			orcamentoAction();
		} else if (e.getActionCommand().equals("Produto")) {
			produtoAction();
		} else if (e.getActionCommand().equals("Sair")) {
			sairAction();
		}
	}

	private void clientesAction() {
		if (!(frame.getCurrentPanel() instanceof ViewClientes)) {
			frame.setCurrentPanel(new ViewClientes(frame));
		}
	}

	private void clienteAction() {
		if (!(frame.getCurrentPanel() instanceof FormClientes)) {
			frame.setCurrentPanel(new FormClientes());
		}
	}

	private void produtosAction() {
		if (!(frame.getCurrentPanel() instanceof ViewProdutos)) {
			frame.setCurrentPanel(new ViewProdutos(frame));
		}
	}

	private void produtoAction() {
		if (!(frame.getCurrentPanel() instanceof FormProduto)) {
			frame.setCurrentPanel(new FormProduto());
		}
	}

	private void orcamentosAction() {
		if (!(frame.getCurrentPanel() instanceof ViewOrcamentos)) {
			frame.setCurrentPanel(new ViewOrcamentos());
		}
	}

	private void orcamentoAction() {
		if (!(frame.getCurrentPanel() instanceof FormOrcamento)) {
			frame.setCurrentPanel(new FormOrcamento());
		}
	}

	private void sairAction() {
		System.exit(0);
	}

}
