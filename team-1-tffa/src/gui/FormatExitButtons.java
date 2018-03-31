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
			boolean readyToExecute = true;
			
			ErrorType error;			
			File inputFile = new File(mainFrame.getInputFileName());
			ErrorType inputErrorType = Error.hasFileErrorInput(inputFile, mainFrame.getOutputFileName());
			File outputFile = new File(mainFrame.getOutputFileName());
			ErrorType outputErrorType = Error.hasFileErrorOutput(outputFile, mainFrame.getInputFileName());
			
			if (inputErrorType != ErrorType.NONE || outputErrorType != ErrorType.NONE) {
				readyToExecute = false;
				error = (inputErrorType != ErrorType.NONE) ? inputErrorType : outputErrorType;
				Error.showErrorMessage(error, mainFrame);
			}
			
			ErrorType lineLengthError = Error.hasLineLengthError(mainFrame.getLineLength());
			
			if (lineLengthError != ErrorType.NONE) {
				readyToExecute = false;
				Error.showErrorMessage(lineLengthError, mainFrame);
			}
			
			if (readyToExecute)
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