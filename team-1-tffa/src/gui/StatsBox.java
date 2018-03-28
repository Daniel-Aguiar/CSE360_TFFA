package gui;

import java.awt.*;
import javax.swing.*;
import common.*;

@SuppressWarnings("serial")
class StatsBox extends JPanel{
	private JLabel statTitle;
	private JLabel words;
	private JLabel spaces;
	private JLabel totalLines;
	private JLabel blankLines;
	private JLabel avgWords;
	private JLabel avgLength;

	StatsBox(LayoutManager layout){
		setLayout(layout);
		setBorder(BorderFactory.createEtchedBorder());

		statTitle = new JLabel("Statistics");
		statTitle.setFont(new Font(null, Font.BOLD, 14));
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_START;
		add(statTitle, c);

		words = new JLabel("Words: ");
		words.setFont(new Font(null, Font.PLAIN, 12));
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(5, 0, 0, 0);
		c.anchor = GridBagConstraints.LINE_START;
		add(words, c);

		spaces = new JLabel("Spaces: ");
		spaces.setFont(new Font(null, Font.PLAIN, 12));
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(5, 0, 0, 0);
		c.anchor = GridBagConstraints.LINE_START;
		add(spaces, c);

		totalLines = new JLabel("Total Lines:  ");
		totalLines.setFont(new Font(null, Font.PLAIN, 12));
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 3;
		c.insets = new Insets(5, 0, 0, 0);
		c.anchor = GridBagConstraints.LINE_START;
		add(totalLines, c);

		blankLines = new JLabel("Blank Lines: ");
		blankLines.setFont(new Font(null, Font.PLAIN, 12));
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 4;
		c.insets = new Insets(5, 0, 0, 0);
		c.anchor = GridBagConstraints.LINE_START;
		add(blankLines, c);

		avgWords = new JLabel("Average Words Per Line: ");
		avgWords.setFont(new Font(null, Font.PLAIN, 12));
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 5;
		c.insets = new Insets(5, 0, 0, 0);
		c.anchor = GridBagConstraints.LINE_START;
		add(avgWords, c);

		avgLength = new JLabel("Average Line Length: ");
		avgLength.setFont(new Font(null, Font.PLAIN, 12));
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 6;
		c.insets = new Insets(5, 0, 0, 0);
		c.anchor = GridBagConstraints.LINE_START;
		add(avgLength, c);
	}

	void setStats(Statistics stats) {
		words.setText("me");
		spaces.setText("me");
		totalLines.setText("me");
		blankLines.setText("me");
		avgWords.setText("me");
		avgLength.setText("me");
	}
}
