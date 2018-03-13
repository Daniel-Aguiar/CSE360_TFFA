package common;

public class Options {

	private String justy;
	private int maxLineLength;

	//No-arg constructor
	public Options() {}

	/* Setters */
	public void setJusty(String justy) {
		this.justy = justy;
	}

	public void setMaxLineLength(int linelength) {
		this.maxLineLength = linelength;
	}

	/* Getters */
	public String getJusty() {
		return this.justy;
	}

	public int getMaxLineLength() {
		return this.maxLineLength;
	}

}//end Options class
