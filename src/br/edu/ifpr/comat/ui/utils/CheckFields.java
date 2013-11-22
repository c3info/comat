package br.edu.ifpr.comat.ui.utils;

import java.awt.Color;
import java.awt.Component;
import java.awt.SystemColor;

import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CheckFields {

	public static Boolean checkEmptyFields(JPanel panel, Component comps[]) {
		boolean check = true;
		Color c = new JTextField().getBackground();
		Color cf = new JFormattedTextField().getBackground();

		Component components[] = panel.getComponents();

		for (int i = 0; i < components.length; i++) {
			

			if (components[i] instanceof JTextField
					&& !in_array(comps, components[i])) {

				if (((JTextField) components[i]).getText().isEmpty()) {
					((JTextField) components[i]).setBackground(SystemColor.inactiveCaption);
					check = false;
				} else {

					((JTextField) components[i]).setBackground(c);
				}
			} 
			
			if (components[i] instanceof JFormattedTextField && !in_array(comps, components[i])) {				
				if ((((JFormattedTextField) components[i]).getText()).startsWith(" ") || ((JTextField) components[i]).getText().isEmpty()) {
					((JFormattedTextField) components[i]).setBackground(SystemColor.inactiveCaption);
					check = false;
				} else {
					((JTextField) components[i]).setBackground(cf);
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
				if (((JTextField) components[i]).getBackground() == SystemColor.inactiveCaption) {
					((JTextField) components[i]).setBackground(c);
				}
			}

			if (components[i] instanceof JFormattedTextField) {
				if (((JFormattedTextField) components[i]).getBackground() == SystemColor.inactiveCaption) {
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
