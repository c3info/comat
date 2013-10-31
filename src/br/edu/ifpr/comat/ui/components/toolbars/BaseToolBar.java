package br.edu.ifpr.comat.ui.components.toolbars;

import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

public class BaseToolBar extends JToolBar {

	private Map<String, JButton> buttons;
	private ActionListener listener;

	public BaseToolBar(ActionListener listener) {
		this.buttons = new HashMap<String, JButton>();
		this.listener = listener;
		loadDefaultButtons();		
	}

	public Map<String, JButton> getButtons() {
		return buttons;
	}

	public BaseToolBar addButton(String text, String iconPath) {
		JButton button = buidButton(text, iconPath);
		buttons.put(text.toLowerCase(), button);
		add(button);
		return this;
	}

	protected void loadDefaultButtons() {
		this.addButton("Incluir", "/br/edu/ifpr/comat/ui/images/add.png")
				.addButton("Alterar", "/br/edu/ifpr/comat/ui/images/pencil.png")
				.addButton("Excluir", "/br/edu/ifpr/comat/ui/images/delete.png")
				.addButton("Salvar", "/br/edu/ifpr/comat/ui/images/disk.png")
				.addButton("Cancelar","/br/edu/ifpr/comat/ui/images/cancel.png");
	}

	protected JButton buidButton(String text, String iconPath) {
		JButton button = new JButton();
		button.setText(text);
		button.setToolTipText(text);
		button.setIcon(new ImageIcon(getClass().getResource(iconPath)));
		button.setHorizontalTextPosition(SwingConstants.RIGHT);
		button.setVerticalTextPosition(SwingConstants.BOTTOM);
		button.addActionListener(this.listener);
		return button;
	}

	public void incluir() {
		getButtons().get("incluir").setEnabled(false);
		getButtons().get("alterar").setEnabled(false);
		getButtons().get("excluir").setEnabled(false);
		getButtons().get("salvar").setEnabled(true);
		getButtons().get("cancelar").setEnabled(true);
	}

	public void alterar() {
		getButtons().get("incluir").setEnabled(false);
		getButtons().get("alterar").setEnabled(false);
		getButtons().get("excluir").setEnabled(false);
		getButtons().get("salvar").setEnabled(true);
		getButtons().get("cancelar").setEnabled(true);
	}

	public void excluir() {
		getButtons().get("incluir").setEnabled(true);
		getButtons().get("alterar").setEnabled(false);
		getButtons().get("excluir").setEnabled(false);
		getButtons().get("salvar").setEnabled(false);
		getButtons().get("cancelar").setEnabled(false);
	}

	public void salvar() {
		getButtons().get("incluir").setEnabled(true);
		getButtons().get("alterar").setEnabled(false);
		getButtons().get("excluir").setEnabled(false);
		getButtons().get("salvar").setEnabled(false);
		getButtons().get("cancelar").setEnabled(false);
	}

	public void cancelar() {
		getButtons().get("incluir").setEnabled(true);
		getButtons().get("alterar").setEnabled(false);
		getButtons().get("excluir").setEnabled(false);
		getButtons().get("salvar").setEnabled(false);
		getButtons().get("cancelar").setEnabled(false);
	}
}
