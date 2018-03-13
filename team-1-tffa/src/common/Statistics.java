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
		avgLL = 0;
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

	/* hashCode() and equals() for data class */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(avgLL);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(avgWpL);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + blankLines;
		result = prime * result + totalLines;
		result = prime * result + totalWords;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Statistics other = (Statistics) obj;
		if (Double.doubleToLongBits(avgLL) != Double.doubleToLongBits(other.avgLL))
			return false;
		if (Double.doubleToLongBits(avgWpL) != Double.doubleToLongBits(other.avgWpL))
			return false;
		if (blankLines != other.blankLines)
			return false;
		if (totalLines != other.totalLines)
			return false;
		if (totalWords != other.totalWords)
			return false;
		return true;
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
