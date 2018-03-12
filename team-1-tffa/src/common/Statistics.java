package common;

public class Statistics {

	/* Private Members */
	private int totalWords;
	private int totalLines;
	private int blankLines;
	private double avgWpL;
	private double avgLL;

	// No - arg constructor sets all private members to 0
	public Statistics() {
		totalWords = 0;
		totalLines = 0;
		blankLines = 0;
		avgWpL = 0;
		avgLL = 0
	}

	/* Setters */
	public void setTotalWords(int arg) {
		totalWords = arg;
	}

	public void setTotalLines(int arg) {
		totalLines = arg;
	}

	public void setBlankLines(int arg) {
		blankLines = arg;
	}

	public void setAvgWpL(double arg) {
		avgWpL = arg;
	}

	public void setAvgLL(double arg) {
		avgLL = arg;
	}

	/* Getters */
	public int getTotalWords() {
		return totalWords;
	}

	public int getTotalLines() {
		return totalLines;
	}

	public int getBlankLines() {
		return blankLines;
	}

	public double getAvgWpL() {
		return avgWpL;
	}

	public double getAvgLL() {
		return avgLL;
	}

}//end class
