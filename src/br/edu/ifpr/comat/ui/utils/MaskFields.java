package br.edu.ifpr.comat.ui.utils;

import java.text.NumberFormat;

import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;

public class MaskFields {
	
	public static MaskFormatter marcarate(String masc) {

		MaskFormatter mascara = new MaskFormatter();
		try {
			mascara.setMask(masc);
			mascara.setPlaceholderCharacter(' ');
		} catch (Exception excecao) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erro");
		}
		return mascara;
	}	
}
