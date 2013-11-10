package br.edu.ifpr.comat.ui.util;

import javax.swing.text.*;

public class MaxLengthFields extends PlainDocument {	
	
	private int iMaxLength;

	public MaxLengthFields(int maxlen) {
		super();
		iMaxLength = maxlen;
	}

	public void insertString(int offset, String str, AttributeSet attr)
			throws BadLocationException {
		if (str == null)
			return;

		if (iMaxLength <= 0) {
			super.insertString(offset, str, attr);
			return;
		}

		int ilen = (getLength() + str.length());
		if (ilen <= iMaxLength)
			super.insertString(offset, str, attr);
		else {
			if (getLength() == iMaxLength)
				return;
			String newStr = str.substring(0, (iMaxLength - getLength()));

			super.insertString(offset, newStr, attr);
		}
	}

}
