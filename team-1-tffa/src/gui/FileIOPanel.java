package gui;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.*;

@SuppressWarnings("serial")
class FileIOPanel extends JPanel {
	private JFileChooser fileChooser;

	private JLabel inputLabel;
	private JLabel outputLabel;
	private JButton browseInputBtn;
	private JButton browseOutputBtn;
	private JTextField inputField;
	private JTextField outputField;

	FileIOPanel(LayoutManager layout) {
		setLayout(layout);

		inputLabel = new JLabel("Input File");
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.ipadx = 100;
		c.anchor = GridBagConstraints.LINE_START;
		add(inputLabel, c);

		/* Add some space in the middle column of this panel */
		c = new GridBagConstraints();
		c.ipadx = 150;
		c.gridx = 1;
		add(Box.createGlue(), c);

		inputField = new JTextField();
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		c.ipadx = 50;
		c.fill = GridBagConstraints.HORIZONTAL;
		add(inputField, c);

		outputLabel = new JLabel("Output File");
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(10, 0, 0, 0);
		c.anchor = GridBagConstraints.LINE_START;
		add(outputLabel, c);

		outputField = new JTextField();
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		add(outputField, c);

		browseInputBtn = new JButton("Browse");
		c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 1;
		c.insets = new Insets(0, 25, 0, 0);
		c.anchor = GridBagConstraints.LINE_START;
		browseInputBtn.addActionListener(new InputListener());
		add(browseInputBtn, c);

		browseOutputBtn = new JButton("Browse");
		c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 3;
		c.insets = new Insets(0, 25, 0, 0);
		c.anchor = GridBagConstraints.LINE_START;
		browseOutputBtn.addActionListener(new OutputListener());
		add(browseOutputBtn, c);
	}

	private class InputListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent evt) {
			if (fileChooser == null) {
				fileChooser = new JFileChooser();
				fileChooser.setFileFilter(new FileNameExtensionFilter("Text files", "txt"));
				fileChooser.setAcceptAllFileFilterUsed(false);
			}

			int fileSelectVal = fileChooser.showDialog(browseInputBtn.getParent().getParent(), "Select");

			if (fileSelectVal == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				ErrorType errorType = Error.hasFileErrorInput(file, getOutputFile());
				
				if (errorType != ErrorType.NONE) {
					Error.showErrorMessage(errorType, FileIOPanel.this.getParent());
				}
				else
					setInputFile(file.getPath());
			}
		}
	}

	private class OutputListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent evt) {
			if (fileChooser == null) {
				fileChooser = new JFileChooser();
				fileChooser.setFileFilter(new FileNameExtensionFilter("Text files", "txt"));
				fileChooser.setAcceptAllFileFilterUsed(false);
			}

			int fileSelectVal = fileChooser.showDialog(browseOutputBtn.getParent().getParent(), "Select");

			if (fileSelectVal == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				ErrorType errorType = Error.hasFileErrorOutput(file, getInputFile());
				
				if (errorType != ErrorType.NONE) {
					Error.showErrorMessage(errorType, FileIOPanel.this.getParent());
					setOutputFile("");
				}
				else
					setOutputFile(file.getPath());
			}
		}
	}

	void setInputFile(String file) {
		inputField.setText(file);
	}

	String getInputFile() {
		return inputField.getText();
	}

	void setOutputFile(String file) {
		outputField.setText(file);
	}

	String getOutputFile() {
		return outputField.getText();
	}
}
