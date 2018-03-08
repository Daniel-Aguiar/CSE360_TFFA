package common;

import java.nio.file.Path;

public class Options {

	//**************************************** Private Methods
	private Path inputFile;
	private Path outputFile;
	
	
	//**************************************** Getters and Setters
	public Path getInputFile() {
		return inputFile;
	}
	public void setInputFile(Path inputFile) {
		this.inputFile = inputFile;
	}
	public Path getOutputFile() {
		return outputFile;
	}
	public void setOutputFile(Path outputFile) {
		this.outputFile = outputFile;
	}
	
	
	
	
	//these two functions are needed if you ever want to do things like put objects of this class into a container.
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((inputFile == null) ? 0 : inputFile.hashCode());
		result = prime * result + ((outputFile == null) ? 0 : outputFile.hashCode());
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
		if (inputFile == null) {
			if (other.inputFile != null)
				return false;
		} else if (!inputFile.equals(other.inputFile))
			return false;
		if (outputFile == null) {
			if (other.outputFile != null)
				return false;
		} else if (!outputFile.equals(other.outputFile))
			return false;
		return true;
	}
	
	
	
	
}//end class
