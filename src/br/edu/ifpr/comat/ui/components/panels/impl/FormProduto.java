package br.edu.ifpr.comat.ui.components.panels.impl;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EtchedBorder;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

import br.edu.ifpr.comat.controller.CategoriaController;
import br.edu.ifpr.comat.controller.ProdutoController;
import br.edu.ifpr.comat.model.Produto;
import br.edu.ifpr.comat.ui.components.dialogs.ModalFormCategoria;
import br.edu.ifpr.comat.ui.components.panels.ComatJPanels;
import br.edu.ifpr.comat.ui.components.panels.tabbeds.TabViewProdutosRel;
import br.edu.ifpr.comat.ui.components.toolbars.CrudToolBar;
import br.edu.ifpr.comat.ui.utils.CheckEmptyFields;
import br.edu.ifpr.comat.ui.utils.FormatterNumberFactory;
import br.edu.ifpr.comat.ui.utils.MaxLengthFields;
import br.edu.ifpr.comat.ui.utils.OnlyNumberFields;

public class FormProduto extends JPanel implements ComatJPanels, ActionListener {

	private Boolean insert = false;
	private JPanel headerPanel, topPanel, gridPanel, contentPanel;
	private JTabbedPane tabbedPane;
	private TabViewProdutosRel tabProdutosRel;

	private GridBagConstraints cons;

	private CrudToolBar crudBar;
	private JComboBox cbxCategoria, cbxStatus, cbxUnidade;

	private JButton btNewCategoria;
	private JTextArea txDescricao;
	private JTextField txNome, txCodBar, txCodFabricante, txMarca, txQuantidade;
	private JFormattedTextField txReferencia, txPeso, txCusto, txVenda, txDesconto;

	private FormatterNumberFactory formater;

	public FormProduto() {
		buildComponents();
		setDisableFields();
		crudBar.incluir();
	}

	public FormProduto(int ref) {
		buildComponents();
		setDisableFields();
		restoreFields(ref);

		setLockFk();
		cbxStatus.setEnabled(false);
		cbxCategoria.setEnabled(false);
		crudBar.alterar();
	}

	private void buildComponents() {
		setLayout(new BorderLayout(0, 0));
		formater = new FormatterNumberFactory();

		headerPanel = new JPanel();
		topPanel = new JPanel();
		contentPanel = new JPanel();
		gridPanel = new JPanel();

		headerPanel.setLayout(new BorderLayout(0, 0));
		gridPanel.setLayout(new GridBagLayout());

		topPanel.setBorder(new EtchedBorder());

		crudBar = new CrudToolBar(this);
		crudBar.setFloatable(false);

		headerPanel.add(BorderLayout.NORTH, crudBar);
		headerPanel.add(BorderLayout.SOUTH, topPanel);

		JLabel lblReferncia = new JLabel("Referência:");
		txReferencia = new JFormattedTextField();
		txReferencia.setDocument(new OnlyNumberFields(12));

		JLabel lblCategoria = new JLabel("Categoria:");
		cbxCategoria = new JComboBox( new CategoriaController().getCategoriasVetString());
		cbxCategoria.addItem("Qualquer");
		cbxCategoria.setSelectedItem("Qualquer");

		btNewCategoria = new JButton("");
		btNewCategoria.setToolTipText("Administrar Categorias");
		btNewCategoria.setIcon(new ImageIcon(FormProduto.class.getResource("/br/edu/ifpr/comat/ui/images/add.png")));
		btNewCategoria.addActionListener(this);

		JLabel lblStatus = new JLabel("Status:");
		cbxStatus = new JComboBox(new ProdutoController().getStatusList());

		GroupLayout grTopPanel = new GroupLayout(topPanel);
		grTopPanel.setHorizontalGroup(
			grTopPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(grTopPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblReferncia)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(txReferencia, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 260, Short.MAX_VALUE)
					.addComponent(lblCategoria)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cbxCategoria, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btNewCategoria)
					.addGap(18)
					.addComponent(lblStatus)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cbxStatus, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		grTopPanel.setVerticalGroup(
			grTopPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(grTopPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(grTopPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblReferncia)
						.addComponent(txReferencia, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cbxStatus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblStatus)
						.addComponent(cbxCategoria, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCategoria)
						.addComponent(btNewCategoria, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		topPanel.setLayout(grTopPanel);

		JLabel lblNome = new JLabel("Nome:");
		txNome = new JTextField(10);
		txNome.setDocument(new MaxLengthFields(72));

		JLabel lblCodBarras = new JLabel("Cod. Barras:");
		txCodBar = new JTextField(10);
		txCodBar.setDocument(new OnlyNumberFields(13));

		JLabel lblCodFabricante = new JLabel("Cod. Fabricante:");
		txCodFabricante = new JTextField(10);
		txCodFabricante.setDocument(new MaxLengthFields(12));

		JLabel lblMarca = new JLabel("Marca:");
		txMarca = new JTextField(10);
		txMarca.setDocument(new MaxLengthFields(45));

		JLabel lblUnidade = new JLabel("Unidade:");
		cbxUnidade = new JComboBox(new ProdutoController().getUnidadesList());

		JLabel lblPeso = new JLabel("Peso:");
		txPeso = new JFormattedTextField(new DefaultFormatterFactory(
				new NumberFormatter(formater.getDecimalDisplayFormat()),
				new NumberFormatter(formater.getDecimalDisplayFormat()),
				new NumberFormatter(formater.getDecimalDisplayFormat())));
		txPeso.setColumns(10);

		JLabel lblQuantidade = new JLabel("Quantidade:");
		txQuantidade = new JTextField();
		txQuantidade.setColumns(10);

		JLabel lblPCusto = new JLabel("P. Custo:");
		txCusto = new JFormattedTextField(new DefaultFormatterFactory(
				new NumberFormatter(formater.getAmountDisplayFormat()),
				new NumberFormatter(formater.getAmountDisplayFormat()),
				new NumberFormatter(formater.getAmountEditFormat())));
		txCusto.setColumns(10);

		JLabel lblPVenda = new JLabel("P. Venda:");
		txVenda = new JFormattedTextField(new DefaultFormatterFactory(
				new NumberFormatter(formater.getAmountDisplayFormat()),
				new NumberFormatter(formater.getAmountDisplayFormat()),
				new NumberFormatter(formater.getAmountEditFormat())));
		txVenda.setColumns(10);

		JLabel lblDesconto = new JLabel("Desconto max.:");
		txDesconto = new JFormattedTextField(new DefaultFormatterFactory(
				new NumberFormatter(formater.getPercentDisplayFormat()),
				new NumberFormatter(formater.getPercentDisplayFormat()),
				formater.getPercentEditFormatter()));
		txDesconto.setColumns(10);

		JLabel lblDescricao = new JLabel("Descrição:");
		txDescricao = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(txDescricao);

		GroupLayout gcontentPanel = new GroupLayout(contentPanel);
		gcontentPanel.setHorizontalGroup(
			gcontentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gcontentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gcontentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gcontentPanel.createSequentialGroup()
							.addComponent(lblNome)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txNome, GroupLayout.DEFAULT_SIZE, 808, Short.MAX_VALUE)
							.addGap(6))
						.addGroup(gcontentPanel.createSequentialGroup()
							.addComponent(lblCodBarras)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txCodBar, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblCodFabricante)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txCodFabricante, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblMarca)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txMarca, GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gcontentPanel.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 839, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gcontentPanel.createSequentialGroup()
							.addGroup(gcontentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblUnidade)
								.addComponent(lblDescricao))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cbxUnidade, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblPeso)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txPeso, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblQuantidade)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txQuantidade, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblPCusto)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txCusto, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblPVenda)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txVenda, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblDesconto)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txDesconto, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE))))
		);
		gcontentPanel.setVerticalGroup(
			gcontentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gcontentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gcontentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNome)
						.addComponent(txNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gcontentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCodBarras)
						.addComponent(txCodBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCodFabricante)
						.addComponent(txCodFabricante, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMarca)
						.addComponent(txMarca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gcontentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUnidade)
						.addComponent(cbxUnidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPeso)
						.addComponent(txPeso, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblQuantidade)
						.addComponent(txQuantidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPCusto)
						.addComponent(txCusto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPVenda)
						.addComponent(txVenda, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDesconto)
						.addComponent(txDesconto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblDescricao)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gcontentPanel);
		contentPanel.setBorder(new EtchedBorder());

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(new EtchedBorder());

		tabProdutosRel = new TabViewProdutosRel();

		tabbedPane.addTab("Produtos Relacionados", null, tabProdutosRel,
				"Produtos");

		cons = new GridBagConstraints();
		cons.fill = GridBagConstraints.BOTH;
		cons.gridx = 0;
		cons.gridy = 0;
		gridPanel.add(contentPanel, cons);

		cons.fill = GridBagConstraints.BOTH;
		cons.weightx = 1;
		cons.weighty = 1;
		cons.gridx = 0;
		cons.gridy = 1;
		gridPanel.add(tabbedPane, cons);		

		add(BorderLayout.NORTH, headerPanel);
		add(BorderLayout.CENTER, gridPanel);

	}

	private void reloadCategorias() {
		String[] categorias = new CategoriaController().getCategoriasVetString();
		cbxCategoria.setModel(new JComboBox<>(categorias).getModel());
	}

	private void restoreFields(int ref) {
		Produto p = new ProdutoController().search(ref);

		cbxStatus.setSelectedIndex(p.getStatus());
		cbxCategoria.setSelectedItem(p.getCategoria().getNomeCategoria());
		txReferencia.setText(p.getRefProduto().toString());
		txNome.setText(p.getNome());
		txCodBar.setText(p.getCodBarra());
		txCodFabricante.setText(p.getCodFabricante());
		txMarca.setText(p.getMarca());
		cbxUnidade.setSelectedItem(p.getUnidade());
		txPeso.setText(p.getPeso().toString());
		txQuantidade.setText(p.getQuantidade().toString());
		txCusto.setText(NumberFormat.getCurrencyInstance().format(p.getPrecoCusto()));
		txVenda.setText(NumberFormat.getCurrencyInstance().format(p.getPrecoVenda()));

		NumberFormat num = NumberFormat.getPercentInstance();
		num.setMinimumFractionDigits(2);
		txDesconto.setText(num.format(p.getDescontoMax() / 100));
		txDescricao.setText(p.getDescricao());
	}

	@Override
	public JPanel getPanel() {
		return this;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Incluir")) {
			incluir();

		} else if (e.getActionCommand().equals("Alterar")) {
			alterar();

		} else if (e.getActionCommand().equals("Excluir")) {
			excluir();

		} else if (e.getActionCommand().equals("Salvar")) {
			salvar();

		} else if (e.getActionCommand().equals("Cancelar")) {
			cancelar();

		} else if (e.getSource() == btNewCategoria) {
			observerCategorias();
		}
	}

	private void observerCategorias() {
		java.awt.EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				ModalFormCategoria categorias = new ModalFormCategoria();
				categorias.setModal(true);
				categorias.setVisible(true);
				String valor = categorias.retornavalor();
				if (valor != null) {
					reloadCategorias();
					cbxCategoria.setSelectedItem(valor);
				} else {
					reloadCategorias();
					cbxCategoria.addItem("Qualquer");
					cbxCategoria.setSelectedItem("Qualquer");
				}
				categorias = null;
			}
		});
	}

	private void incluir() {
		insert = true;

		crudBar.inclusao();
		setCleanFields();
		setEnabledFields();
		setUnLockFk();

		txReferencia.requestFocusInWindow();
	}

	private void alterar() {
		insert = false;

		crudBar.inclusao();
		setEnabledFields();
		setLockFk();

		txNome.requestFocusInWindow();
	}

	private void excluir() {
		int excluir = JOptionPane.showConfirmDialog(null,
				"Deseja realmente excluir os dados ?", "Pedido de Exclusão",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (excluir == JOptionPane.YES_OPTION) {
			new ProdutoController().delete(Integer.parseInt(txReferencia.getText()));
			crudBar.incluir();
			setCleanFields();
		}

	}

	private void salvar() {

		Component ignoreComponents[] = { txCodBar, txCodFabricante,	txDescricao, txCusto, txVenda, txDesconto, txQuantidade, txMarca, txPeso };
		Component emptyComponentes[] = {};
		if (CheckEmptyFields.checkEmptyFields(topPanel, emptyComponentes) & CheckEmptyFields.checkEmptyFields(contentPanel, ignoreComponents)) {
			if (!cbxCategoria.getSelectedItem().equals("Qualquer")) {
				int refProduto = Integer.parseInt(txReferencia.getText());
				String codBarra = txCodBar.getText().trim();
				String codFabricante = txCodFabricante.getText().trim();
				String nome = txNome.getText().trim();
				String descricao = txDescricao.getText().trim();
				String unidade = (String) cbxUnidade.getSelectedItem();

				Double precoCusto = 0.00d;
				if (!txCusto.getText().isEmpty()) {
					precoCusto = Double.parseDouble(((txVenda.getText().replace(".", "")).replace("R$", "")).replace(",","."));
				}

				Double precoVenda = 0.00d;
				if (!txVenda.getText().isEmpty()) {
					precoVenda = Double.parseDouble(((txVenda.getText().replace(".", "")).replace("R$", "")).replace(",","."));
				}

				Double descontoMax = 0.00d;
				if (!txDesconto.getText().isEmpty()) {
					descontoMax = Double.parseDouble((txDesconto.getText().replaceAll(",", ".").replace("%", "")).trim());
				}

				Integer quantidade = 0;
				if (!txQuantidade.getText().isEmpty()) {
					quantidade = Integer.parseInt(txQuantidade.getText());
				}

				Double peso = 0.00d;
				if (!txPeso.getText().isEmpty()) {
					peso = Double.parseDouble((txPeso.getText().replaceAll(",",".")).trim());
				}

				int status = cbxStatus.getSelectedIndex();
				String marca = txMarca.getText().trim();
				String categoria = (String) cbxCategoria.getSelectedItem();

				if (codBarra.isEmpty()) codBarra = null;
				if (codFabricante.isEmpty()) codFabricante = null;
				
				if (insert) {
					new ProdutoController().save(refProduto, codBarra,
							codFabricante, nome, descricao, unidade,
							precoCusto, precoVenda, descontoMax, quantidade,
							status, marca, peso, categoria);

					insert = false;
					setDisableFields();
					cbxStatus.setEnabled(false);
					crudBar.alterar();
					JOptionPane.showMessageDialog(null, "Produto" + nome + " cadastrado com sucesso");
				} else {

					new ProdutoController().alter(refProduto, codBarra,
							codFabricante, nome, descricao, unidade,
							precoCusto, precoVenda, descontoMax, quantidade,
							status, marca, peso, categoria);
					setDisableFields();
					cbxStatus.setEnabled(false);
					crudBar.alterar();
					JOptionPane.showMessageDialog(null, "Produto " + nome + " atualizado com sucesso");
				}

			} else {
				JOptionPane.showMessageDialog(null, "Selecione uma categoria!");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Verifique os campos obrigatórios");
		}
	}

	private void cancelar() {
		if (insert)
			insert = false;

		crudBar.incluir();
		setCleanFields();
		setDisableFields();
		CheckEmptyFields.restoreFields(topPanel);
		CheckEmptyFields.restoreFields(contentPanel);
		cbxCategoria.setSelectedItem("Qualquer");
		cbxStatus.setSelectedIndex(0);
	}

	@Override
	public void setEnabledFields() {
		cbxCategoria.setEnabled(true);
		cbxStatus.setEnabled(true);
		cbxUnidade.setEnabled(true);
		txDescricao.setEnabled(true);
		txNome.setEnabled(true);
		txCodBar.setEnabled(true);
		txCodFabricante.setEnabled(true);
		txMarca.setEnabled(true);
		txReferencia.setEnabled(true);
		txPeso.setEnabled(true);
		txQuantidade.setEnabled(true);
		txCusto.setEnabled(true);
		txVenda.setEnabled(true);
		txDesconto.setEnabled(true);
		btNewCategoria.setEnabled(true);
	}

	@Override
	public void setDisableFields() {
		cbxCategoria.setEnabled(false);
		cbxStatus.setEnabled(false);
		cbxUnidade.setEnabled(false);
		txDescricao.setEnabled(false);
		txNome.setEnabled(false);
		txCodBar.setEnabled(false);
		txCodFabricante.setEnabled(false);
		txMarca.setEnabled(false);
		txReferencia.setEnabled(false);
		txPeso.setEnabled(false);
		txQuantidade.setEnabled(false);
		txCusto.setEnabled(false);
		txVenda.setEnabled(false);
		txDesconto.setEnabled(false);
		btNewCategoria.setEnabled(false);
	}

	@Override
	public void setCleanFields() {
		txDescricao.setText("");
		txNome.setText("");
		txCodBar.setText("");
		txCodFabricante.setText("");
		txMarca.setText("");
		txReferencia.setText("");
		txPeso.setText("");
		txQuantidade.setText("");
		txCusto.setText("");
		txVenda.setText("");
		txDesconto.setText("");

	}

	@Override
	public void setLockFk() {
		txReferencia.setEditable(false);
	}

	@Override
	public void setUnLockFk() {
		txReferencia.setEditable(true);
	}
}
