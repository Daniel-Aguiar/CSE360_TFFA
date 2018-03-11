package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class FileError extends JDialog{
	public FileError(String errorType){
		setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		setLayout(new GridBagLayout());
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		JLabel message = null;
		if (errorType.equals("read"))
			message = new JLabel("Invalid input file.");
		else if (errorType.equals("write"))
			message = new JLabel("Invalid output file.");
		else if (errorType.equals("writeInput"))
			message = new JLabel("Cannot overwrite input file.");
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
