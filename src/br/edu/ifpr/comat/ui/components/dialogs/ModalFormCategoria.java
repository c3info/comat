package br.edu.ifpr.comat.ui.components.dialogs;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;

import br.edu.ifpr.comat.controller.CategoriaController;
import br.edu.ifpr.comat.controller.ProdutoController;
import br.edu.ifpr.comat.ui.components.tables.TbModelCategoria;
import br.edu.ifpr.comat.ui.components.toolbars.CrudToolBar;
import br.edu.ifpr.comat.ui.utils.CheckEmptyFields;
import br.edu.ifpr.comat.ui.utils.MaxLengthFields;

public class ModalFormCategoria extends JDialog implements ActionListener {

	private Integer id = null;
	private Boolean insert = false;

	private JPanel topPanel, contentPanel, buttonPane;
	private CrudToolBar crudBar;
	private JTextField txCategoria;
	private JButton btRecuperar, btCancelar;
	private JScrollPane scrollPane;
	private JTable table;

	public ModalFormCategoria() {
		buildComponents();
		setDisableFields();
		crudBar.incluir();
		btRecuperar.setEnabled(false);
	}

	private void buildComponents() {
		setResizable(false);
		setTitle("Administrar Categorias");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 520, 300);
		getContentPane().setLayout(new BorderLayout());

		topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());

		crudBar = new CrudToolBar(this);
		crudBar.setFloatable(false);

		contentPanel = new JPanel();
		contentPanel.setBorder(new EtchedBorder());

		JLabel lblNewLabel = new JLabel("Categoria:");
		txCategoria = new JTextField();
		txCategoria.setDocument(new MaxLengthFields(72));

		topPanel.add(crudBar, BorderLayout.NORTH);
		topPanel.add(contentPanel, BorderLayout.CENTER);

		GroupLayout grInsertPanel = new GroupLayout(contentPanel);
		grInsertPanel.setHorizontalGroup(
			grInsertPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(grInsertPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(txCategoria, GroupLayout.PREFERRED_SIZE, 427, Short.MAX_VALUE)
					.addGap(12))
		);
		grInsertPanel.setVerticalGroup(
			grInsertPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(grInsertPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(grInsertPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(txCategoria, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		contentPanel.setLayout(grInsertPanel);

		getContentPane().add(topPanel, BorderLayout.NORTH);

		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setShowHorizontalLines(true);
		table.setRowHeight(28);
		loadModelTable();

		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				JTable tb = (JTable) me.getSource();
				Point p = me.getPoint();
				int row = tb.rowAtPoint(p);
				if (me.getClickCount() == 2) {		

					id = (Integer) tb.getValueAt(row, 0);
					String nome = (String) tb.getValueAt(row, 1);

					txCategoria.setText(nome);
					crudBar.alterar();
					btRecuperar.setEnabled(true);
				} 
			}
		});

		scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);

		getContentPane().add(scrollPane, BorderLayout.CENTER);

		buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));

		btRecuperar = new JButton("Recuperar");
		btRecuperar.addActionListener(this);
		buttonPane.add(btRecuperar);

		btCancelar = new JButton("Cancelar");
		btCancelar.addActionListener(this);
		buttonPane.add(btCancelar);

		getContentPane().add(buttonPane, BorderLayout.SOUTH);

	}

	public void loadModelTable() {
		table.setModel(new TbModelCategoria(new CategoriaController().search()));
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(0).setPreferredWidth(0);
		table.getColumnModel().getColumn(1).setPreferredWidth(410);
	}

	private void incluir() {
		insert = true;
		crudBar.inclusao();
		setEnabledFields();
		setCleanFields();
		btRecuperar.setEnabled(false);
		if (id != null)
			id = null;
	}

	private void alterar() {
		insert = false;
		crudBar.inclusao();
		setEnabledFields();
		btRecuperar.setEnabled(false);
	}

	private void excluir() {
		if(new ProdutoController().checkDbByCategoria(id)){
			int excluir = JOptionPane.showConfirmDialog(null,
					"Deseja rexcluir a categoria ?", "Pedido de Exclusão",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (excluir == JOptionPane.YES_OPTION) {
				new CategoriaController().delete(id);
				crudBar.incluir();
				setCleanFields();
				setDisableFields();
				loadModelTable();
				btRecuperar.setEnabled(false);
			}
		} else {
			JOptionPane.showMessageDialog(null,	"Esta categoria não pode ser excluida,\npossui produtos relacionados.");
		}
		
	}

	private void salvar() {
		Component emptyComponentes[] = {};
		if (CheckEmptyFields.checkEmptyFields(contentPanel, emptyComponentes)) {
			String nome = txCategoria.getText();

			if (insert) {
				new CategoriaController().save(nome);
				insert = false;
			} else {
				new CategoriaController().alter(id, nome);
				id = null;
			}

			setCleanFields();
			setDisableFields();
			crudBar.incluir();
			loadModelTable();

		} else {
			JOptionPane.showMessageDialog(null,	"Verifique os campos obrigatórios");
		}
	}

	private void cancelar() {
		if (insert)
			insert = false;

		crudBar.incluir();
		setCleanFields();
		setDisableFields();
		CheckEmptyFields.restoreFields(contentPanel);
	}

	public void setEnabledFields() {
		txCategoria.setEnabled(true);
		txCategoria.requestFocusInWindow();
	}

	public void setDisableFields() {
		txCategoria.setEnabled(false);
	}

	public void setCleanFields() {
		txCategoria.setText("");
	}

	public String retornavalor() {
		if (id != null) {
			String categoria = null;

			int selectedRow = -1;
			selectedRow = table.getSelectedRow();

			if (selectedRow >= 0) {
				categoria = (String) table.getValueAt(selectedRow, 1);
			}
			return categoria;
		} else {
			return null;
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btCancelar) {
			dispose();

		} else if (e.getSource() == btRecuperar) {
			dispose();
			
		} else if (e.getActionCommand().equals("Incluir")) {
			incluir();

		} else if (e.getActionCommand().equals("Alterar")) {
			alterar();

		} else if (e.getActionCommand().equals("Excluir")) {
			excluir();

		} else if (e.getActionCommand().equals("Salvar")) {
			salvar();

		} else if (e.getActionCommand().equals("Cancelar")) {
			cancelar();
		}
	}
}
