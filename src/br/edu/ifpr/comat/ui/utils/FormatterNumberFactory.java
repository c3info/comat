package br.edu.ifpr.comat.ui.utils;

import java.text.NumberFormat;
import java.text.ParseException;

import javax.swing.text.NumberFormatter;

public class FormatterNumberFactory {

	private NumberFormat amountDisplayFormat;
	private NumberFormat amountEditFormat;
	private NumberFormat percentDisplayFormat;
	private NumberFormat percentEditFormat;
	private NumberFormat paymentFormat;
	private NumberFormat decimalDisplayFormat;

	private NumberFormatter percentEditFormatter;

	public FormatterNumberFactory() {
		setUpFormats();

		percentEditFormatter = new NumberFormatter(percentEditFormat) {
			public String valueToString(Object o) throws ParseException {
				Number number = (Number) o;
				if (number != null) {
					double d = number.doubleValue() * 100.0;
					number = new Double(d);
				}
				return super.valueToString(number);
			}

			public Object stringToValue(String s) throws ParseException {
				Number number = (Number) super.stringToValue(s);
				if (number != null) {
					double d = number.doubleValue() / 100.0;
					number = new Double(d);
				}
				return number;
			}
		};
	}

	private void setUpFormats() {
		amountDisplayFormat = NumberFormat.getCurrencyInstance();
		amountDisplayFormat.setMinimumFractionDigits(2);
		amountEditFormat = NumberFormat.getNumberInstance();

		percentDisplayFormat = NumberFormat.getPercentInstance();
		percentDisplayFormat.setMinimumFractionDigits(2);
		percentEditFormat = NumberFormat.getNumberInstance();
		percentEditFormat.setMinimumFractionDigits(2);

		paymentFormat = NumberFormat.getCurrencyInstance();

		decimalDisplayFormat = NumberFormat.getNumberInstance();
		decimalDisplayFormat.setMinimumFractionDigits(3);

	}

	public NumberFormat getAmountDisplayFormat() {
		return amountDisplayFormat;
	}

	public NumberFormat getAmountEditFormat() {
		return amountEditFormat;
	}

	public NumberFormat getPercentDisplayFormat() {
		return percentDisplayFormat;
	}

	public NumberFormat getPercentEditFormat() {
		return percentEditFormat;
	}

	public NumberFormat getPaymentFormat() {
		return paymentFormat;
	}

	public NumberFormatter getPercentEditFormatter() {
		return percentEditFormatter;
	}

	public NumberFormat getDecimalDisplayFormat() {
		return decimalDisplayFormat;
	}
}