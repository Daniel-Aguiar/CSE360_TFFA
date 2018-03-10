package gui

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class JustifyPanel extends JPanel{
	private JRadioButton leftJusty;
	private JRadioButton rightJusty;
	private ButtonGroup justifyGroup;
		
	public JustifyPanel(LayoutManager layout){
		setLayout(layout);
		setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED)); /*Create lowered bevel border*/

		leftJusty = new JRadioButton("Left Justified");
		rightJusty = new JRadioButton("Right Justified");

		/* Group up the buttons */
		justifyGroup = new ButtonGroup();
		justifyGroup.add(leftJusty);
		justifyGroup.add(rightJusty);

		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(7, 10, 7, 10);
		add(leftJusty, c);
		c.gridx = 1;
		add(rightJusty, c);
	}
}