package gui;

import java.awt.*;
import java.net.URL;
import javax.swing.*;
import common.*;
import controller.*;

@SuppressWarnings("serial")
public class TFFAGui extends JFrame{
	private static boolean RESIZABLE = false;
	
	private FileIOPanel filePanel;
	private OptionsPanel opts;
	private FormatExitButtons bottomButtons;
	private StatsBox statsPanel;

	
	private void createFilePane(Container pane, GridBagConstraints arrangement) {
		filePanel = new FileIOPanel(new GridBagLayout());
		pane.add(filePanel, arrangement);
	}

	private void createOptionsPanel(Container pane, GridBagConstraints arrangement) {
		opts = new OptionsPanel(new GridBagLayout());
		pane.add(opts, arrangement);
	}

	private void createFormatAndExitButtons(Container pane, GridBagConstraints arrangement) {
		bottomButtons = new FormatExitButtons(new GridBagLayout(), this);
		pane.add(bottomButtons, arrangement);
	}

	private void createStatsBox(Container pane, GridBagConstraints arrangement) {
		statsPanel = new StatsBox(new GridBagLayout());
		pane.add(statsPanel, arrangement);
	}

	public TFFAGui() {
		getContentPane().setLayout(new GridBagLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(RESIZABLE);

		setIcon();
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_START;
		createFilePane(getContentPane(), c);

		JPanel bottomHalfOfFrame = new JPanel(new GridBagLayout());
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(25, 0, 60, 0);
		createOptionsPanel(bottomHalfOfFrame, c);

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
		c.insets = new Insets(0, 20, 0, 0);
		createStatsBox(bottomHalfOfFrame, c);

		c = new GridBagConstraints();
		c.gridy = 1;
		c.anchor = GridBagConstraints.LINE_START;
		getContentPane().add(bottomHalfOfFrame, c);

		setSize(675, 400);
		setTitle("Text File Formatter and Analizer");
		setIcon();
		setVisible(true);
	}
	
	public String getInputFileName() { return filePanel.getInputFile(); }
	
	public String getOutputFileName() { return filePanel.getOutputFile(); }
	
	public void addStats(Statistics stats) {
		statsPanel.setStats(stats);
	}
	
	public Options getOptions() {
		Options options = new Options();
		options.setJusty(opts.getJustification());
		options.setMaxLineLength(opts.getLineLength());
		options.setSpacing(opts.getSpacing());
		return options;
	}
	
	void startController() {
		Controller cont = Controller.getInstance();
		Capsule cap = cont.goFormat(this);
		addStats(cap.getStatistics());
	}
	
	String getLineLength() { return opts.getLineLengthString(); }
	
	private void setIcon() {
	    URL iconURL = getClass().getResource("Teapot.png");
	    
	    if(iconURL != null) {
	        this.setIconImage(Toolkit.getDefaultToolkit().getImage(iconURL));
	    }
	}//end setIcon
}


