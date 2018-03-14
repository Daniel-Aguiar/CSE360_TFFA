package common;

import java.nio.file.Path;
import java.nio.file.Paths;

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

    public void setOutputFile(String filename) {
		this.outputFile = Paths.get(filename);
    }

    public void setInputFile(Path obj) {
        this.inputFile = obj;
    }

    public void setInputFile(String filename) {
		this.outputFile = Paths.get(filename);
    }

    public void setStatistics(Statistics obj) {
        this.stats = obj;
    }

    public void setOptions(Options obj) {
        this.opts = obj;
    }

    
    /* Lance's Code */
	//these two functions are needed if you ever want to do things like put objects of this class into a container.
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((inputFile == null) ? 0 : inputFile.hashCode());
		result = prime * result + ((stats == null) ? 0 : stats.hashCode());
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
		if (stats == null) {
			if (other.stats != null)
				return false;
		} else if (!stats.equals(other.stats))
			return false;
		return true;
	}

}//end class

