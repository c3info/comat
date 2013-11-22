package resources;

import java.beans.PropertyChangeListener;
import java.text.NumberFormat;

import javax.swing.JFormattedTextField;

import java.awt.*;
import javax.swing.*;
import javax.swing.text.*;

import java.beans.PropertyChangeEvent;

import java.text.*;

public class FormatterFactoryDemo extends JPanel {
	
	// Fields for data entry
	private JFormattedTextField amountField;
	private JFormattedTextField rateField;
	private JFormattedTextField numPeriodsField;

	// Formats to format and parse numbers
	private NumberFormat amountDisplayFormat;
	private NumberFormat amountEditFormat;
	private NumberFormat percentDisplayFormat;
	private NumberFormat percentEditFormat;
	private NumberFormat paymentFormat;

	public FormatterFactoryDemo() {
		super(new BorderLayout());
		setUpFormats();

		// Create the text fields and set them up.
		amountField = new JFormattedTextField(new DefaultFormatterFactory(
				new NumberFormatter(amountDisplayFormat), new NumberFormatter(
						amountDisplayFormat), new NumberFormatter(
						amountEditFormat)));

		amountField.setColumns(10);

		NumberFormatter percentEditFormatter = new NumberFormatter(
				percentEditFormat) {
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
		rateField = new JFormattedTextField(new DefaultFormatterFactory(
				new NumberFormatter(percentDisplayFormat), new NumberFormatter(
						percentDisplayFormat), percentEditFormatter));

		rateField.setColumns(10);

		numPeriodsField = new JFormattedTextField();
		numPeriodsField.setColumns(10);

		// Layout the text fields in a panel.
		JPanel fieldPane = new JPanel(new GridLayout(0, 1));
		fieldPane.add(amountField);
		fieldPane.add(rateField);
		fieldPane.add(numPeriodsField);

		// Put the panels in this panel, labels on left,
		// text fields on right.
		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		add(fieldPane, BorderLayout.LINE_END);
	}

	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event dispatch thread.
	 */
	private static void createAndShowGUI() {
		// Create and set up the window.
		JFrame frame = new JFrame("FormatterFactoryDemo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Add contents to the window.
		frame.getContentPane().add(new FormatterFactoryDemo());

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		// Schedule a job for the event dispatch thread:
		// creating and showing this application's GUI.
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				// Turn off metal's use of bold fonts
				UIManager.put("swing.boldMetal", Boolean.FALSE);
				createAndShowGUI();
			}
		});
	}

	// Create and set up number formats. These objects also
	// parse numbers input by user.
	private void setUpFormats() {
		amountDisplayFormat = NumberFormat.getCurrencyInstance();
		amountDisplayFormat.setMinimumFractionDigits(2);
		amountEditFormat = NumberFormat.getNumberInstance();

		percentDisplayFormat = NumberFormat.getPercentInstance();
		percentDisplayFormat.setMinimumFractionDigits(2);
		percentEditFormat = NumberFormat.getNumberInstance();
		percentEditFormat.setMinimumFractionDigits(2);

		paymentFormat = NumberFormat.getCurrencyInstance();
	}
}