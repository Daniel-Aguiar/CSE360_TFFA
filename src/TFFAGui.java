import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class TFFAGui {
	private static boolean RESIZABLE = false;

	
	public static void createFilePane(Container pane, GridBagConstraints arrangement) {
		JPanel fileIOPanel = new JPanel(new GridBagLayout());

		JLabel inputLabel = new JLabel("Input File");
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.ipadx = 100;
		c.anchor = GridBagConstraints.LINE_START;
		fileIOPanel.add(inputLabel, c);

		/* Add some space in the middle column of this panel */
		c = new GridBagConstraints();
		c.ipadx = 150;
		c.gridx = 1;
		fileIOPanel.add(Box.createGlue(), c);

		JTextField inputField = new JTextField();
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		c.ipadx = 50;
		c.fill = GridBagConstraints.HORIZONTAL;
		fileIOPanel.add(inputField, c);

		JLabel outputLabel = new JLabel("Output File");
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(10, 0, 0, 0);
		c.anchor = GridBagConstraints.LINE_START;
		fileIOPanel.add(outputLabel, c);

		JTextField outputField = new JTextField();
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		fileIOPanel.add(outputField, c);

		JButton browseInputBtn = new JButton("Browse");
		c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 1;
		c.insets = new Insets(0, 25, 0, 0);
		c.anchor = GridBagConstraints.LINE_START;
		fileIOPanel.add(browseInputBtn, c);

		JButton browseOutputBtn = new JButton("Browse");
		c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 3;
		c.insets = new Insets(0, 25, 0, 0);
		c.anchor = GridBagConstraints.LINE_START;
		fileIOPanel.add(browseOutputBtn, c);

		pane.add(fileIOPanel, arrangement);
	}

	public static void createJustifyButtons(Container pane, GridBagConstraints arrangement) {
		JPanel justifyPanel = new JPanel(new GridBagLayout());
		justifyPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED)); /*Create lowered bevel border*/
		GridBagConstraints c = new GridBagConstraints();

		JRadioButton leftJusty = new JRadioButton("Left Justified");
		JRadioButton rightJusty = new JRadioButton("Right Justified");

		/* Group up the buttons */
		ButtonGroup justifyGroup = new ButtonGroup();
		justifyGroup.add(leftJusty);
		justifyGroup.add(rightJusty);

		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(7, 10, 7, 10);
		justifyPanel.add(leftJusty, c);
		c.gridx = 1;
		justifyPanel.add(rightJusty, c);

		pane.add(justifyPanel, arrangement);
	}

	public static void createFormatAndExitButtons(Container pane, GridBagConstraints arrangement) {
		JPanel formatExitButtons = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		JButton formatButton = new JButton("Format");
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(0, 0, 0, 25);
		formatExitButtons.add(formatButton, c);

		JButton exitButton = new JButton("Exit");
		c.gridx = 1;
		c.gridy = 0;
		c.ipadx = 25;
		c.insets = new Insets(0, 25, 0, 25);
		formatExitButtons.add(exitButton, c);

		pane.add(formatExitButtons, arrangement);
	}

	public static void createStatsBox(Container pane, GridBagConstraints arrangement) {
		JPanel statsBox = new JPanel(new GridBagLayout());

		JLabel statTitle = new JLabel("Statistics");
		statTitle.setFont(new Font(null, Font.BOLD, 14));
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_START;
		statsBox.add(statTitle, c);

		JLabel words = new JLabel("Words: ");
		words.setFont(new Font(null, Font.PLAIN, 12));
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(5, 0, 0, 0);
		c.anchor = GridBagConstraints.LINE_START;
		statsBox.add(words, c);

		JLabel totalLines = new JLabel("Total Lines:  ");
		totalLines.setFont(new Font(null, Font.PLAIN, 12));
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(5, 0, 0, 0);
		c.anchor = GridBagConstraints.LINE_START;
		statsBox.add(totalLines, c);

		JLabel blankLines = new JLabel("Blank Lines: ");
		blankLines.setFont(new Font(null, Font.PLAIN, 12));
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 3;
		c.insets = new Insets(5, 0, 0, 0);
		c.anchor = GridBagConstraints.LINE_START;
		statsBox.add(blankLines, c);

		JLabel avgWords = new JLabel("Average Words Per Line: ");
		avgWords.setFont(new Font(null, Font.PLAIN, 12));
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 4;
		c.insets = new Insets(5, 0, 0, 0);
		c.anchor = GridBagConstraints.LINE_START;
		statsBox.add(avgWords, c);

		JLabel avgLength = new JLabel("Average Line Length: ");
		avgLength.setFont(new Font(null, Font.PLAIN, 12));
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 5;
		c.insets = new Insets(5, 0, 0, 0);
		c.anchor = GridBagConstraints.LINE_START;
		statsBox.add(avgLength, c);

		statsBox.setBorder(BorderFactory.createEtchedBorder()); /*Create stats border*/
		pane.add(statsBox, arrangement);
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

	public static void main(String[] args){
		javax.swing.SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run(){ new TFFAGui(); }
		});
	}
}
