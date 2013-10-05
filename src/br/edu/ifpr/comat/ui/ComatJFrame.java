package br.edu.ifpr.comat.ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javassist.expr.Instanceof;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import java.awt.Dimension;

public class ComatJFrame extends JFrame implements ActionListener {

	private static final int PREFERRED_WIDTH = 800;
	private static final int PREFERRED_HEIGHT = 600;

	private JPanel mainPane, toolsPane, statsPane;
	private ComatJPanels currentPanel = null;

	private JMenuBar menuBar;
	private JMenu mnArquivo, mnCadastros;
	private JMenuItem mniClientes, mniCliente, mniSair;
	private JToolBar toolsBar;
	private JButton btTeste;
	private JSeparator separator;
	private JLabel status;

	public ComatJFrame() {
		setTitle("Comat");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(PREFERRED_WIDTH, PREFERRED_HEIGHT);

		// Instancias
		mainPane = new JPanel();
		mainPane.setBorder(new EmptyBorder(1, 1, 1, 1));
		toolsPane = new JPanel();
		toolsPane.setBorder(new EtchedBorder());
		statsPane = new JPanel();
		statsPane.setPreferredSize(new Dimension(10, 26));		
		statsPane.setBorder(new EtchedBorder());

		// Define layouts
		mainPane.setLayout(new BorderLayout(0, 0));
		toolsPane.setLayout(new GridLayout(2, 1));
		statsPane.setLayout(new BorderLayout(0, 0));

		// Adiciona componentes
		menuBar = new JMenuBar();
		toolsPane.add(menuBar);

		mnArquivo = new JMenu("Arquivo");
		mnArquivo.setMnemonic('A');
		menuBar.add(mnArquivo);

		mniClientes = new JMenuItem("Clientes");
		mniClientes.addActionListener(this);
		mnArquivo.add(mniClientes);

		separator = new JSeparator();
		mnArquivo.add(separator);

		mniSair = new JMenuItem("Sair");
		mniSair.addActionListener(this);
		mnArquivo.add(mniSair);

		mnCadastros = new JMenu("Cadastros");
		menuBar.add(mnCadastros);

		mniCliente = new JMenuItem("Cliente");
		mniCliente.addActionListener(this);
		mnCadastros.add(mniCliente);

		toolsBar = new JToolBar();
		toolsPane.add(toolsBar);

		btTeste = new JButton("Incio");
		btTeste.addActionListener(this);
		toolsBar.add(btTeste);

		status = new JLabel();
		status.setForeground(Color.DARK_GRAY);
		setStatusbar("Comat");
		statsPane.add(status);

		// Define tela inicial
		setPanel(new FirstPanel());
//		mainPane.add(BorderLayout.NORTH, toolsPane);
//		mainPane.add(BorderLayout.CENTER, new FirstPanel().getPanel());
//		mainPane.add(BorderLayout.SOUTH, statsPane);
//		setContentPane(mainPane);
	}

	public void setPanel(ComatJPanels set) {

		if (!(currentPanel == null)) {
			currentPanel = set;
			mainPane.removeAll();
			addPanels(set.getPanel());
			invalidate();
			validate();
			repaint();

		} else {
			currentPanel = set;
			addPanels(set.getPanel());
		}
	}

	public void addPanels(Component comp) {
		mainPane.add(BorderLayout.NORTH, toolsPane);
		mainPane.add(BorderLayout.CENTER, comp);
		mainPane.add(BorderLayout.SOUTH, statsPane);
		setContentPane(mainPane);
	}

	public void setStatusbar(String messagem) {
		status.setText(" " + messagem);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mniClientes) {
			ComatJFrame.this.mniClientesActionPerformed(e);
		} else if (e.getSource() == mniCliente) {
			ComatJFrame.this.mniClienteActionPerformed(e);
		} else if (e.getSource() == mniSair) {
			ComatJFrame.this.mniSairActionPerformed(e);
		} else if (e.getSource() == btTeste) {
			ComatJFrame.this.btTesteActionPerformed(e);
		}
	}

	private void mniClientesActionPerformed(ActionEvent evt) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				if (!(currentPanel instanceof ViewClientesPanel)) {
					setPanel(new ViewClientesPanel());
				}
				;
			}
		});
	}

	private void mniClienteActionPerformed(ActionEvent evt) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				if (!(currentPanel instanceof FormClientesPanel)) {
					setPanel(new FormClientesPanel());
				}
				;
			}
		});
	}

	private void btTesteActionPerformed(ActionEvent evt) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				if (!(currentPanel instanceof FirstPanel)) {
					setPanel(new FirstPanel());
				}
				;
			}
		});
	}

	private void mniSairActionPerformed(ActionEvent evt) {
		System.exit(0);
	}

	public static void main(String[] args) {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
					.getInstalledLookAndFeels()) {
				if ("Windows".equals(info.getName())) {
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
					ComatJFrame frame = new ComatJFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
