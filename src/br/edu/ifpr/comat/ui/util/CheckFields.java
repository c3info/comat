package br.edu.ifpr.comat.ui.util;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CheckFields {

	public static Boolean checkEmptyFields(JPanel panel, Component comps[]) {
		boolean check = true;
		Color c = new JTextField().getBackground();

		Component components[] = panel.getComponents();

		for (int i = 0; i < components.length; i++) {
			

			if (components[i] instanceof JTextField
					&& !in_array(comps, components[i])) {

				if (((JTextField) components[i]).getText().isEmpty()) {
					((JTextField) components[i]).setBackground(Color.cyan);
					check = false;
				} else {

					((JTextField) components[i]).setBackground(c);
				}
			}

			if (components[i] instanceof JFormattedTextField) {
				if ((((JFormattedTextField) components[i]).getText())
						.startsWith(" ")) {
					((JFormattedTextField) components[i])
							.setBackground(Color.cyan);
					check = false;
				} else {
					((JTextField) components[i]).setBackground(c);
				}

			}
		}
		return check;
	}

	public static void restoreFields(JPanel panel) {
		Component components[] = panel.getComponents();
		Color c = new JTextField().getBackground();

		for (int i = 0; i < components.length; i++) {

			if (components[i] instanceof JTextField) {
				if (((JTextField) components[i]).getBackground() == Color.cyan) {
					((JTextField) components[i]).setBackground(c);
				}
			}

			if (components[i] instanceof JFormattedTextField) {
				if (((JFormattedTextField) components[i]).getBackground() == Color.cyan) {
					((JFormattedTextField) components[i]).setBackground(c);

				} else {
					((JTextField) components[i]).setBackground(c);
				}
			}
		}

	}

	public static boolean in_array(Component components[], Component component) {
		for (int i = 0; i < components.length; i++) {
			if (components[i].equals(component)) {
				return true;
			}
		}
		return false;
	}

}
