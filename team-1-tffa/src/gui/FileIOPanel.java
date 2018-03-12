package gui;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.swing.*;
import javax.swing.filechooser.*;

@SuppressWarnings("serial")
public class FileIOPanel extends JPanel{
	private String inputFile;
	private String outputFile;
	private JFileChooser fileChooser;
	
	private JLabel inputLabel;
	private JLabel outputLabel;
	private JButton browseInputBtn;
	private JButton browseOutputBtn;
	private JTextField inputField;
	private JTextField outputField;
		
	public FileIOPanel(LayoutManager layout){
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
	
	private class InputListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent evt) {
			if (fileChooser == null) {
				fileChooser = new JFileChooser();
				fileChooser.setFileFilter(new FileNameExtensionFilter("Text files", "txt"));
			}
			
			int fileSelectVal = fileChooser.showDialog(browseInputBtn.getParent().getParent(), "Select");
			
			if (fileSelectVal == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				String fileType;
				
				try {
					fileType = Files.probeContentType(file.toPath());
				} catch(IOException e) {
					new FileError("read");
					return;
				}
				
				if (!file.canRead() || !fileType.equals("text/plain"))
					new FileError("read");
				else 
					setInputFile(file.getPath());
				
				if (inputFile.equals(outputFile)) {
					new FileError("writeInput");
					setInputFile("");
				}
				
				inputField.setText(inputFile);
			}
		}
	}
	
	private class OutputListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent evt) {
			if (fileChooser == null) {
				fileChooser = new JFileChooser();
				fileChooser.setFileFilter(new FileNameExtensionFilter("Text files", "txt"));
			}
			
			int fileSelectVal = fileChooser.showDialog(browseOutputBtn.getParent().getParent(), "Select");
			
			if (fileSelectVal == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				String fileType;
				
				try {
					fileType = Files.probeContentType(file.toPath());
				} catch(IOException e) {
					new FileError("write");
					return;
				}
				
				if (!file.canWrite() || !fileType.equals("text/plain"))
					new FileError("write");
				else
					setOutputFile(file.getPath());
				
				if (outputFile.equals(inputFile)) {
					new FileError("writeInput");
					setOutputFile("");
				}
				
				outputField.setText(outputFile);
			}
		}
	}
	
	public void setInputFile(String file) {
		inputFile = file;
	}
	
	public String getInputFile() { return inputFile; }
	
	public void setOutputFile(String file) {
		outputFile = file;
	}
	
	public String getOutputFile() { return outputFile; }
}
