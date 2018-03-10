package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class FormatExitButtons extends JPanel{
	private JButton formatButton;
	private JButton exitButton;
	
	public FormatExitButtons(LayoutManager layout){
		setLayout(layout);
		
		formatButton = new JButton("Format");
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(0, 0, 0, 25);
		add(formatButton, c);

		exitButton = new JButton("Exit");
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.ipadx = 25;
		c.insets = new Insets(0, 25, 0, 25);
		add(exitButton, c);
	}
}