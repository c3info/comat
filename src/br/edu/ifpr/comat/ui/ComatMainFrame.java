package br.edu.ifpr.comat.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;

import br.edu.ifpr.comat.ui.components.panels.ComatJPanels;
import br.edu.ifpr.comat.ui.components.panels.impl.ViewLogo;

public class ComatMainFrame extends JFrame {
	private static final String SYSTEM_NAME = "COMAT - Controle de Orçamentos para Materiais de Construção";
	private static final int PREFERRED_WIDTH = 980;
	private static final int PREFERRED_HEIGHT = 740;

	private JPanel mainPanel, contentPanel, statusPanel;
	private ComatJPanels currentPanel = null;
	private ActionListener listener;

	private JMenuBar menuBar;
	private JMenu mnArquivo, mnCadastros;
	private JMenuItem mniClientes, mniCliente, mniProdutos, mniProduto, mniOrcamentos, mniOrcamento, mniSair;
	private JLabel status;

	public ComatMainFrame() {
		setTitle(SYSTEM_NAME);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, PREFERRED_WIDTH, PREFERRED_HEIGHT);
		setMinimumSize(new java.awt.Dimension(PREFERRED_WIDTH, PREFERRED_HEIGHT));

		buildComponents();
		listener = new ComatMainListener(this);

		// Define janela inicial
		setCurrentPanel(new ViewLogo());
		setContentPane(mainPanel);
		pack();
	}

	private void buildComponents() {
		// Panels
		mainPanel = new JPanel();
		mainPanel.setBackground(SystemColor.activeCaptionBorder);
		contentPanel = new JPanel();
		statusPanel = new JPanel();

		// Panels layouts
		mainPanel.setLayout(new BorderLayout(0, 0));
		contentPanel.setLayout(new BorderLayout(0, 0));
		statusPanel.setLayout(new BorderLayout(0, 0));

		// Panels Estilos
		mainPanel.setBorder(new EmptyBorder(1, 1, 1, 1));

		// Componentes
		menuBar = new JMenuBar();

		mnArquivo = new JMenu("Arquivo");
		mnArquivo.setMnemonic('R');
		menuBar.add(mnArquivo);

		mniClientes = new JMenuItem("Clientes");
		mniClientes.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.ALT_MASK));
		mniClientes.setIcon(new ImageIcon(ComatMainFrame.class.getResource("/br/edu/ifpr/comat/ui/images/folder.png")));
		mnArquivo.add(mniClientes);

		mniOrcamentos = new JMenuItem("Orçamentos");
		mniOrcamentos.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.ALT_MASK));
		mniOrcamentos.setIcon(new ImageIcon(ComatMainFrame.class.getResource("/br/edu/ifpr/comat/ui/images/folder.png")));
		mnArquivo.add(mniOrcamentos);

		mniProdutos = new JMenuItem("Produtos");
		mniProdutos.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.ALT_MASK));
		mniProdutos.setIcon(new ImageIcon(ComatMainFrame.class.getResource("/br/edu/ifpr/comat/ui/images/folder.png")));
		mnArquivo.add(mniProdutos);

		mnArquivo.add(new JSeparator());

		mniSair = new JMenuItem("Sair");
		mniSair.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
		mniSair.setIcon(new ImageIcon(ComatMainFrame.class.getResource("/br/edu/ifpr/comat/ui/images/shut_down.png")));
		mnArquivo.add(mniSair);

		mnCadastros = new JMenu("Cadastro");
		mnCadastros.setMnemonic('A');
		menuBar.add(mnCadastros);

		mniCliente = new JMenuItem("Cliente");
		mniCliente.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.ALT_MASK | InputEvent.SHIFT_MASK));
		mniCliente.setIcon(new ImageIcon(ComatMainFrame.class.getResource("/br/edu/ifpr/comat/ui/images/page.png")));
		mnCadastros.add(mniCliente);

		mniOrcamento = new JMenuItem("Orçamento");
		mniOrcamento.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.ALT_MASK | InputEvent.SHIFT_MASK));
		mniOrcamento.setIcon(new ImageIcon(ComatMainFrame.class.getResource("/br/edu/ifpr/comat/ui/images/page.png")));		
		mnCadastros.add(mniOrcamento);

		mniProduto = new JMenuItem("Produto");
		mniProduto.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.ALT_MASK | InputEvent.SHIFT_MASK));
		mniProduto.setIcon(new ImageIcon(ComatMainFrame.class.getResource("/br/edu/ifpr/comat/ui/images/page.png")));
		mnCadastros.add(mniProduto);

		status = new JLabel();
		status.setFont(new Font("Arial", Font.PLAIN, 11));
		status.setForeground(Color.DARK_GRAY);
		status.setPreferredSize(new Dimension(780, 22));
		setStatusbar(SYSTEM_NAME);

		JSeparator separator = new JSeparator(JSeparator.HORIZONTAL);
		separator.setBackground(SystemColor.activeCaptionBorder);
		statusPanel.add(BorderLayout.NORTH, separator);
		statusPanel.add(BorderLayout.SOUTH, status);

		mainPanel.add(BorderLayout.NORTH, menuBar);
		mainPanel.add(BorderLayout.CENTER, contentPanel);
		mainPanel.add(BorderLayout.SOUTH, statusPanel);
	}

	public void setCurrentPanel(ComatJPanels set) {
		if (!(currentPanel == null)) {
			contentPanel.removeAll();
			currentPanel = set;
			changePanel(set.getPanel());
			validate();
			repaint();
		} else {
			currentPanel = set;
			changePanel(set.getPanel());
		}
		System.gc();
	}

	private void changePanel(Component comp) {
		contentPanel.add(comp);
	}

	public ComatJPanels getCurrentPanel() {
		return currentPanel;
	}

	public JMenuItem getMniClientes() {
		return mniClientes;
	}

	public JMenuItem getMniCliente() {
		return mniCliente;
	}

	public JMenuItem getMniProdutos() {
		return mniProdutos;
	}

	public JMenuItem getMniProduto() {
		return mniProduto;
	}

	public JMenuItem getMniOrcamentos() {
		return mniOrcamentos;
	}

	public JMenuItem getMniOrcamento() {
		return mniOrcamento;
	}

	public JMenuItem getMniSair() {
		return mniSair;
	}

	public void setStatusbar(String messagem) {
		status.setText(" " + messagem);
	}	
}
