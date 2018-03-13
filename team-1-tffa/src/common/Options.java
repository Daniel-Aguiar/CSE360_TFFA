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

	/* hashCode() and equals() for data class */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((justy == null) ? 0 : justy.hashCode());
		result = prime * result + maxLineLength;
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
		Options other = (Options) obj;
		if (justy == null) {
			if (other.justy != null)
				return false;
		} else if (!justy.equals(other.justy))
			return false;
		if (maxLineLength != other.maxLineLength)
			return false;
		return true;
	}
	

}//end Options class
