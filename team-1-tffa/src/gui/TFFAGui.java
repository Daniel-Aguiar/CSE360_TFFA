package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TFFAGui {
	private static boolean RESIZABLE = false;
	
	private FileIOPanel filePanel;
	private JustifyPanel justy;
	private FormatExitButtons bottomButtons;
	private StatsBox statsPanel;

	
	private void createFilePane(Container pane, GridBagConstraints arrangement) {
		filePanel = new FileIOPanel(new GridBagLayout());
		pane.add(filePanel, arrangement);
	}

	private void createJustifyButtons(Container pane, GridBagConstraints arrangement) {
		justy = new JustifyPanel(new GridBagLayout());
		pane.add(justy, arrangement);
	}

	private void createFormatAndExitButtons(Container pane, GridBagConstraints arrangement) {
		bottomButtons = new FormatExitButtons(new GridBagLayout());
		pane.add(bottomButtons, arrangement);
	}

	private void createStatsBox(Container pane, GridBagConstraints arrangement) {
		statsPanel = new StatsBox(new GridBagLayout());
		pane.add(statsPanel, arrangement);
	}

	public TFFAGui() {
		JFrame guiFrame = new JFrame();
		guiFrame.getContentPane().setLayout(new GridBagLayout());
		guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		guiFrame.setResizable(RESIZABLE);

		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_START;
		createFilePane(guiFrame.getContentPane(), c);

		JPanel bottomHalfOfFrame = new JPanel(new GridBagLayout());
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(25, 0, 60, 0);
		createJustifyButtons(bottomHalfOfFrame, c);

		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.anchor = GridBagConstraints.LINE_START;
		createFormatAndExitButtons(bottomHalfOfFrame, c);

		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.gridheight = 2;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(10, 58, 0, 0);
		createStatsBox(bottomHalfOfFrame, c);

		c = new GridBagConstraints();
		c.gridy = 1;
		c.anchor = GridBagConstraints.LINE_START;
		guiFrame.getContentPane().add(bottomHalfOfFrame, c);

		guiFrame.setSize(600, 350);
		guiFrame.setVisible(true);
	}
	
	public void setInputFile(String file) {
		
	}
	
	public void setOutputFile(String file) {
		
	}

	public static void main(String[] args){
		javax.swing.SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run(){ new TFFAGui(); }
		});
	}
}
