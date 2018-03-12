package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class FileError extends JDialog{
	public FileError(FileErrorType error){
		setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		setLayout(new GridBagLayout());
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		JLabel message = null;
		switch(error) {
		case READ:
			message = new JLabel("Invalid input file.");
			break;
			
		case WRITE:
			message = new JLabel("Invalid output file.");
			break;
			
		case SAME_INPUT_OUTPUT:
			message = new JLabel("Cannot overwrite input file.");
			break;
		}
		message.setFont(new Font(null, Font.BOLD, 12));
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(10,0,0,0);
		add(message, c);
		
		JButton close = new JButton("Ok");
		close.addActionListener(new CloseListener());
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(10,100,10,100);
		add(close, c);
		
		setResizable(false);
		setSize(200,100);
		setVisible(true);
	}
	
	private class CloseListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent evt) {
			FileError.this.dispose();
		}
	}
}
