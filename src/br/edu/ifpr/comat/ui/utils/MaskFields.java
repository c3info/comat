package br.edu.ifpr.comat.ui.utils;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
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

	// public static JTextField DefinirTiposCaracteresETamanho(int tamanho,
	// String caracteres)
	//
	// {
	// try
	//
	// {
	// String quantidade = "";
	// for (int i = 0; tamanho > i; i++) {
	// quantidade = quantidade + "*";
	// }
	//
	// javax.swing.text.MaskFormatter
	//
	// nome = new javax.swing.text.MaskFormatter(quantidade);
	// nome.setValidCharacters(caracteres);
	//
	// return new javax.swing.JFormattedTextField(nome);
	// }
	//
	// catch (Exception e)
	//
	// {
	// JOptionPane.showMessageDialog(null, "Ocorreu um erro");
	// return new JTextField();
	// }
	//
	// }
}
