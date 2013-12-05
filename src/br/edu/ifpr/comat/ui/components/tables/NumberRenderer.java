package br.edu.ifpr.comat.ui.components.tables;

import java.text.NumberFormat;

import javax.swing.SwingConstants;

public class NumberRenderer extends FormatRenderer {
	/*
	 * Use the specified number formatter and right align the text
	 */
	public NumberRenderer(NumberFormat formatter) {
		super(formatter);
		setHorizontalAlignment(SwingConstants.RIGHT);
	}

	/*
	 * Use the default currency formatter for the default locale
	 */
	public static NumberRenderer getCurrencyRenderer() {
		return new NumberRenderer(NumberFormat.getCurrencyInstance());
	}

	/*
	 * Use the default integer formatter for the default locale
	 */
	public static NumberRenderer getIntegerRenderer() {
		return new NumberRenderer(NumberFormat.getIntegerInstance());
	}

	/*
	 * Use the default percent formatter for the default locale
	 */
	public static NumberRenderer getPercentRenderer() {
		NumberFormat percentDisplayFormat = NumberFormat.getPercentInstance();
		percentDisplayFormat.setMinimumFractionDigits(2);
		return new NumberRenderer(percentDisplayFormat);
	}
	
	public static NumberRenderer getDecimalRenderer() {
		NumberFormat percentFormat = NumberFormat.getNumberInstance();
		percentFormat.setMinimumFractionDigits(2);

		return new NumberRenderer(percentFormat);
	}
}
