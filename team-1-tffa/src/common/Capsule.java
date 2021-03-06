package common;

import java.nio.file.Path;

public class Capsule {

    /* Private Members */
    private Path inputFile;
    private Path outputFile;
    private Statistics stats;
    private Options opts;

    /* No-arg constructor */
    public Capsule() {
		stats = new Statistics();
    } 

    /* Getters */
    public Path getInputFile() {
        return this.inputFile;
    }

    public Path getOutputFile() {
        return this.outputFile;
    }

    public Statistics getStatistics() {
        return this.stats;
    }

    public Options getOptions() {
        return this.opts;
    }

    /* Setters */
    public void setOutputFile(Path obj) {
        this.outputFile = obj;
    }

    public void setInputFile(Path obj) {
        this.inputFile = obj;
    }

    public void setStatistics(Statistics obj) {
        this.stats = obj;
    }

    public void setOptions(Options obj) {
        this.opts = obj;
    }

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
		Capsule other = (Capsule) obj;
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
