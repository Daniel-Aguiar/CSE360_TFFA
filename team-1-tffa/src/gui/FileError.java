package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
class FileError extends JDialog{

	FileError(FileErrorType error){
		setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		setLayout(new GridBagLayout());
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		ImageIcon errorImage = new ImageIcon("src/gui/StockErrorImage.jpg", "Error image");
		
		JLabel message = new JLabel(errorImage, SwingConstants.LEFT);
		//message.setIconTextGap(10);
		switch(error) {
		case READ:
			message.setText("Invalid input file.");
			break;
			
		case WRITE:
			message.setText("Invalid output file.");
			break;
			
		case SAME_INPUT_OUTPUT:
			message.setText("Cannot overwrite input file.");
			break;
			
		case ERROR:
			message.setText("An error occurred.");
			break;
		}
		message.setFont(new Font(null, Font.BOLD, 12));
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(10,0,0,0);
		add(message, c);
				
		JButton close = new JButton("Ok");
		close.addActionListener(new CloseListener());
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(10,100,10,100);
		add(close, c);
		
		setResizable(false);
		pack();
		setVisible(true);
	}
	
	private class CloseListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent evt) {
			FileError.this.dispose();
		}
	}
}
