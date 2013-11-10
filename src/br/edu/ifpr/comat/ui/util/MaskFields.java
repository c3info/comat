package br.edu.ifpr.comat.ui.util;

import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;

public class MaskFields {

	public static MaskFormatter Mascara(String masc) {

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
