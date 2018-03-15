package gui;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
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
			FileErrorType error;
			
			File inputFile = new File(mainFrame.getInputFileName());
			FileErrorType inputErrorType = FileError.hasFileErrorInput(inputFile, mainFrame.getOutputFileName());
			File outputFile = new File(mainFrame.getOutputFileName());
			FileErrorType outputErrorType = FileError.hasFileErrorOutput(outputFile, mainFrame.getInputFileName());
			
			if (inputErrorType != FileErrorType.NONE || outputErrorType != FileErrorType.NONE) {
				error = (inputErrorType != FileErrorType.NONE) ? inputErrorType : outputErrorType;
				FileError.showErrorMessage(error, mainFrame);
			} else
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