package gui;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.swing.*;

@SuppressWarnings("serial")
class FormatExitButtons extends JPanel{
	private final TFFAGui mainFrame;
	
	private JButton formatButton;
	private JButton exitButton;
	
	FormatExitButtons(LayoutManager layout, TFFAGui mainFrame){
		this.mainFrame = mainFrame;
		setLayout(layout);
		
		formatButton = new JButton("Format");
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(0, 0, 0, 25);
		add(formatButton, c);
		formatButton.addActionListener(new FormatButtonListener());

		exitButton = new JButton("Exit");
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.ipadx = 25;
		c.insets = new Insets(0, 25, 0, 25);
		add(exitButton, c);
		exitButton.addActionListener(new ExitButtonListener());		
	}
	
	private class FormatButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent evt) {
			File inputFile = new File(mainFrame.getInputFileName());
			if (!inputFile.canRead()) {
				new FileError(FileErrorType.READ);
				return;
			}
			
			try {
				if (!Files.probeContentType(inputFile.toPath()).equals("text/plain")) {
					new FileError(FileErrorType.READ);
					return;
				}
			} catch (IOException e) {
				new FileError(FileErrorType.ERROR);
				return;
			}
			
			File outputFile = new File(mainFrame.getOutputFileName());
			if (!outputFile.exists()) {
				String fileType;
				
				try {
					outputFile.createNewFile();
					fileType = Files.probeContentType(outputFile.toPath());
				} catch (IOException e) {
					new FileError(FileErrorType.ERROR);
					return;
				}
				
				if (!fileType.equals("text/plain")) {
					new FileError(FileErrorType.WRITE);
					return;
				}
			}
			
			if (!outputFile.canWrite()) {
				new FileError(FileErrorType.WRITE);
				return;
			} else if (outputFile.equals(inputFile)) {
				new FileError(FileErrorType.SAME_INPUT_OUTPUT);
				return;
			}
			
			mainFrame.startController();
		}
	}
	
	private class ExitButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent evt) {
			System.exit(0);
		}
	}
}