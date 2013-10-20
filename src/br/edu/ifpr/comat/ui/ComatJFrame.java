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
import br.edu.ifpr.comat.ui.listeners.ComatListener;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;

import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.awt.SystemColor;
import javax.swing.border.EtchedBorder;

public class ComatJFrame extends JFrame {
	private static final String SYSTEM_NAME = "COMAT - Controle de Orçamentos para Materiais de Construção";
	private static final int PREFERRED_WIDTH = 960;
	private static final int PREFERRED_HEIGHT = 720;

	private JPanel mainPanel, contentPanel, statusPanel;
	private ComatJPanels currentPanel = null;
	private ActionListener listener;

	private JMenuBar menuBar;
	private JMenu mnArquivo, mnCadastros;
	private JMenuItem mniClientes, mniCliente, mniSair;
	private JLabel status;

	public ComatJFrame() {
		setTitle(SYSTEM_NAME);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, PREFERRED_WIDTH, PREFERRED_HEIGHT);
		setMinimumSize(new java.awt.Dimension(PREFERRED_WIDTH, PREFERRED_HEIGHT));

		buildComponents();
		listener = new ComatListener(this);

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
		mnArquivo.setMnemonic('A');
		menuBar.add(mnArquivo);

		mniClientes = new JMenuItem("Clientes");
		mnArquivo.add(mniClientes);

		mnArquivo.add(new JSeparator());

		mniSair = new JMenuItem("Sair");
		mniSair.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
				InputEvent.CTRL_MASK));
		mnArquivo.add(mniSair);

		mnCadastros = new JMenu("Cadastros");
		mnCadastros.setMnemonic('C');
		menuBar.add(mnCadastros);

		mniCliente = new JMenuItem("Cliente");
		mnCadastros.add(mniCliente);

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
			java.util.logging.Logger.getLogger(ComatJFrame.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(ComatJFrame.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(ComatJFrame.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(ComatJFrame.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new ComatJFrame().setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
