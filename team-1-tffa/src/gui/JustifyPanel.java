package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

@SuppressWarnings("serial")
public class JustifyPanel extends JPanel{
	private String justification;
	
	private JRadioButton leftJusty;
	private JRadioButton rightJusty;
	private ButtonGroup justifyGroup;
		
	public JustifyPanel(LayoutManager layout){
		setLayout(layout);
		setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));

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
		
		JustyListener justListen = new JustyListener();
		
		leftJusty.addActionListener(justListen);
		rightJusty.addActionListener(justListen);
		
		leftJusty.doClick(); /*Left Justification should be selected by default*/
	}
	
	private class JustyListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent evt) {
			ButtonModel leftModel = leftJusty.getModel();
			
			if (justifyGroup.isSelected(leftModel))
				justification = "left";
			else
				justification = "right";
		}
	}
	
	public String getJustification() {
		return justification;
	}
}