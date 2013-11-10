package br.edu.ifpr.comat.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import br.edu.ifpr.comat.ui.components.panels.ComatJPanels;
import br.edu.ifpr.comat.ui.components.panels.impl.FirstPanel;
import br.edu.ifpr.comat.ui.listeners.ComatMainListener;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;

import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.awt.SystemColor;
import javax.swing.ImageIcon;

public class ComatMainFrame extends JFrame {
	private static final String SYSTEM_NAME = "COMAT - Controle de Orçamentos para Materiais de Construção";
	private static final int PREFERRED_WIDTH = 960;
	private static final int PREFERRED_HEIGHT = 720;

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
		setCurrentPanel(new FirstPanel());
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
		mniSair.setIcon(new ImageIcon(ComatMainFrame.class.getResource("/br/edu/ifpr/comat/ui/images/shut_down.png")));
		mniSair.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,InputEvent.CTRL_MASK));
		mnArquivo.add(mniSair);

		mnCadastros = new JMenu("Cadastro");
		mnCadastros.setMnemonic('A');
		menuBar.add(mnCadastros);

		mniCliente = new JMenuItem("Cliente");
		mniCliente.setIcon(new ImageIcon(ComatMainFrame.class.getResource("/br/edu/ifpr/comat/ui/images/page.png")));
		mniCliente.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.ALT_MASK | InputEvent.SHIFT_MASK));
		mnCadastros.add(mniCliente);
		
		mniOrcamento = new JMenuItem("Orçamento");
		mniOrcamento.setIcon(new ImageIcon(ComatMainFrame.class.getResource("/br/edu/ifpr/comat/ui/images/page.png")));
		mniOrcamento.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.ALT_MASK | InputEvent.SHIFT_MASK));
		mnCadastros.add(mniOrcamento);
		
		mniProduto = new JMenuItem("Produto");
		mniProduto.setIcon(new ImageIcon(ComatMainFrame.class.getResource("/br/edu/ifpr/comat/ui/images/page.png")));
		mniProduto.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.ALT_MASK | InputEvent.SHIFT_MASK));
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

	public static void main(String[] args) {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
					.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(ComatMainFrame.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(ComatMainFrame.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(ComatMainFrame.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(ComatMainFrame.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new ComatMainFrame().setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
