package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

import common.Justification;

@SuppressWarnings("serial")
class OptionsPanel extends JPanel{
	private Justification justification;
	private int space;
	
	private JRadioButton leftJusty;
	private JRadioButton rightJusty;
	private JRadioButton bothJusty;
	private ButtonGroup justifyGroup;
	private JLabel lineLengthLabel;
	private JLabel spacingLabel;
	private JTextField lineLengthTextField;
	JComboBox<String> spacingOptions;
		
	OptionsPanel(LayoutManager layout){
		setLayout(layout);
		setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));

		leftJusty = new JRadioButton("Left Justified");
		rightJusty = new JRadioButton("Right Justified");
		bothJusty = new JRadioButton("Right and Left Justified");

		/* Group up the buttons */
		justifyGroup = new ButtonGroup();
		justifyGroup.add(leftJusty);
		justifyGroup.add(rightJusty);
		justifyGroup.add(bothJusty);

		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_START;
		add(leftJusty, c);
		c.gridy = 1;
		add(rightJusty, c);
		c.gridy = 2;
		add(bothJusty, c);
		
		lineLengthLabel = new JLabel("Line Length");
		c = new GridBagConstraints();
		c.gridx = 1;
		c.insets = new Insets(0,10,0,5);
		c.anchor = GridBagConstraints.PAGE_END;
		add(lineLengthLabel, c);
		
		lineLengthTextField = new JTextField("80", 3);
		c = new GridBagConstraints();
		c.gridx = 2;
		c.insets = new Insets(10,5,0,5);
		c.anchor = GridBagConstraints.LINE_START;
		add(lineLengthTextField, c);
		//lineLengthTextField.addKeyListener(new LineLengthListener());
		
		spacingLabel = new JLabel("Spacing");
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.insets = new Insets(5,10,0,0);
		c.anchor = GridBagConstraints.PAGE_END;
		add(spacingLabel, c);
		
		spacingOptions = new JComboBox<String>();
		spacingOptions.addItem("Single-spaced");
		spacingOptions.addItem("Double-spaced");
		c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 1;
		c.insets = new Insets(10,0,0,5);
		c.anchor = GridBagConstraints.PAGE_END;
		add(spacingOptions, c);
		spacingOptions.addActionListener(new SpacingListener());
		
		JustyListener justListen = new JustyListener();
		
		leftJusty.addActionListener(justListen);
		rightJusty.addActionListener(justListen);
		bothJusty.addActionListener(justListen);
		
		leftJusty.doClick(); /*Left Justification should be selected by default*/
	}
	
	private class JustyListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent evt) {			
			if (justifyGroup.isSelected(leftJusty.getModel())) 
				justification = Justification.LEFT;
			else if (justifyGroup.isSelected(rightJusty.getModel()))
				justification = Justification.RIGHT;
			else if (justifyGroup.isSelected(bothJusty.getModel()))
				justification = Justification.BOTH;
			
			System.out.println("In opts panel: " + getJustification());
		}
	}
	
	/*private class LineLengthListener implements KeyListener{
		@Override
		public void keyReleased(KeyEvent e) {
			String lineLengthString = lineLengthTextField.getText();
			if (!Character.isDigit(e.getKeyChar())) {
				if (!isControlKey(e) && lineLengthString.length() != 0)
					lineLengthTextField.setText(lineLengthString.substring(0,lineLengthString.length()-1));
			} else {
				lineLengthInt = Integer.parseInt(lineLengthTextField.getText());
			}
		}
		
		@Override
		public void keyPressed(KeyEvent e) {}
		
		@Override
		public void keyTyped(KeyEvent e) {}
		
		boolean isControlKey(KeyEvent e) {
			boolean retVal = false;
			int keyCode = e.getKeyCode();
			if (keyCode == KeyEvent.VK_LEFT) retVal = true;
			else if (keyCode == KeyEvent.VK_UP) retVal = true;
			else if (keyCode == KeyEvent.VK_DOWN) retVal = true;
			else if (keyCode == KeyEvent.VK_RIGHT) retVal = true;
			else if (keyCode == KeyEvent.VK_BACK_SPACE) retVal = true;
			else if (keyCode == KeyEvent.VK_DELETE) retVal = true;
			else if (keyCode == KeyEvent.VK_CONTROL) retVal = true;
			else if (keyCode == KeyEvent.VK_SHIFT) retVal = true;
			else if (keyCode == KeyEvent.VK_ALT) retVal = true;
			else if (keyCode == KeyEvent.VK_ENTER) retVal = true;
			else if (keyCode == KeyEvent.VK_CAPS_LOCK) retVal = true;
			else if (keyCode == KeyEvent.VK_ESCAPE) retVal = true;
			return retVal;
		}
	} */
	
	//This might set the space to be the previously selected option
	//depending on what the "action" is (button press, or selection
	//from the drop down, or both)
	private class SpacingListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			int spaceOptionInt = spacingOptions.getSelectedIndex();
			
			switch(spaceOptionInt) {
			case 0:
				space = 1;
				break;
			case 1:
				space = 2;
				break;
			}
		}
	}
	
	Justification getJustification() {
		return justification;
	}
	
	int getLineLength() { return Integer.parseInt(getLineLengthString()); }
	
	int getSpacing() { return space; }
	
	String getLineLengthString() { return lineLengthTextField.getText(); }
}