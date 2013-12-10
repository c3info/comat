package br.edu.ifpr.comat.ui.utils;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class OnlyNumberFields extends PlainDocument {

	private int maxlength;

	public OnlyNumberFields() {
	}

	public OnlyNumberFields(int maxlength) {
		super();
		this.maxlength = maxlength;
	}

	public void insertString(int offs, String str, AttributeSet a) {
		try {
			Long.parseLong(str);
			//Integer.parseInt(str);
		} catch (NumberFormatException ex) {
			str = "";
		}
		try {
			boolean fixedLengh = (!((getLength() + str.length()) > maxlength));
			if (maxlength == 0 || fixedLengh)
				super.insertString(offs, str, a);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}

}
