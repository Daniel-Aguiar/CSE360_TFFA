package gui;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;

@SuppressWarnings("serial")
public class FormatExitButtons extends JPanel{
	private final TFFAGui mainFrame;
	
	private JButton formatButton;
	private JButton exitButton;
	
	public FormatExitButtons(LayoutManager layout, TFFAGui mainFrame){
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
			
			//TODO: Remember to create a new output file if one doesn't exist.
			
			File outputFile = new File(mainFrame.getOutputFileName());
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