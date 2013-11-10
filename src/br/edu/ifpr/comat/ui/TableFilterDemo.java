package br.edu.ifpr.comat.ui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.table.*;

class TableSortFilterDemo extends JFrame {
	JLabel lblStatus;

	TableSortFilterDemo(String title) {
		// Assign title to the frame window's title bar.

		super(title);

		// Tell application to automatically exit when the user selects the
		// Close
		// menu item from the frame window's system menu.

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// Create the table's model and a table based on this model. Also set
		// the selection mode to allow only a single row to be selected.

		TableModel model = createTableModel();
		final JTable table = new JTable(model);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// Attach a list selection listener to the table's selection model, to
		// be notified whenever the user selects a row. Use this opportunity to
		// output the view and model indices on the status label.

		ListSelectionListener lsl;
		lsl = new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent lse) {
				int index = table.getSelectedRow();
				if (index != -1) {
					String status;
					status = "View index = " + index + ", Model index = ";
					status += table.convertRowIndexToModel(index);
					lblStatus.setText(status);
				}
			}
		};
		table.getSelectionModel().addListSelectionListener(lsl);

		// Create and install the table's row sorter.

		final TableRowSorter sorter;
		sorter = new TableRowSorter(model);
		table.setRowSorter(sorter);

		// Place the table in a JScrollPane object to allow the table to be
		// vertically scrolled and display scrollbars, as necessary.

		JScrollPane jsp = new JScrollPane(table);
		jsp.setPreferredSize(new Dimension(300, 100));

		// Fill northern region of GUI with scrollpane.

		getContentPane().add(jsp, BorderLayout.NORTH);

		// Create and populate a panel for establishing row filter components.

		JPanel pnlFilter = new JPanel();

		pnlFilter.add(new JLabel("Filter expression:"));

		final JTextField txtFilter = new JTextField(20);
		pnlFilter.add(txtFilter);

		JButton btnSetFilter = new JButton("Set Filter");
		ActionListener al;
		al = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Install a new row filter.

				String expr = txtFilter.getText();
				sorter.setRowFilter(RowFilter.regexFilter(expr));

				// Unsort the view.

				sorter.setSortKeys(null);
			}
		};
		btnSetFilter.addActionListener(al);
		pnlFilter.add(btnSetFilter);

		// Fill center region of GUI with filter panel.

		getContentPane().add(pnlFilter, BorderLayout.CENTER);

		// Create a status label for presenting the view and model indices for
		// the selected row.

		lblStatus = new JLabel(" ", JLabel.CENTER);

		// Fill southern region of GUI with status label.

		getContentPane().add(lblStatus, BorderLayout.SOUTH);

		// Wrap an empty border around the GUI for aesthetic purposes.

		Border border = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		getRootPane().setBorder(border);

		// Resize the GUI to its preferred size.

		pack();

		// Display GUI and start the AWT's event-dispatching thread.

		setVisible(true);
	}

	TableModel createTableModel() {
		// Create a model consisting of 12 rows by 2 columns.

		DefaultTableModel model = new DefaultTableModel(12, 2);

		// Assign column identifiers (headers) to the columns.

		String[] columnTitles = { "Month", "Days", };

		model.setColumnIdentifiers(columnTitles);

		// Populate all cells in the model.

		String[] months = { "January", "February", "March", "April", "May",
				"June", "July", "August", "September", "October", "November",
				"December" };

		String[] days = { "31", "28 (29 on leap year)", "31", "30", "31", "30",
				"31", "31", "30", "31", "30", "31" };

		int nrows = model.getRowCount();
		int ncols = model.getColumnCount();

		for (int i = 0; i < nrows; i++) {
			model.setValueAt(months[i], i, 0);
			model.setValueAt(days[i], i, 1);
		}

		return model;
	}

	public static void main(String[] args) {
		Runnable r = new Runnable() {
			public void run() {
				new TableSortFilterDemo("Table Sorting and " + "Filtering Demo");
			}
		};
		EventQueue.invokeLater(r);
	}
}