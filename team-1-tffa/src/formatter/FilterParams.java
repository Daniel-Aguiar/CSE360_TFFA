package formatter;

import java.nio.file.Path;

import common.Options;
import common.Statistics;

public class FilterParams {

	private Path inFile;
	private Path outFile;
	private Statistics stats;
	private Options opts;
	
	public Path getInFile() {
		return inFile;
	}
	public void setInFile(Path inFile) {
		this.inFile = inFile;
	}
	public Path getOutFile() {
		return outFile;
	}
	public void setOutFile(Path outFile) {
		this.outFile = outFile;
	}
	public Statistics getStats() {
		return stats;
	}
	public void setStats(Statistics stats) {
		this.stats = stats;
	}
	public Options getOpts() {
		return opts;
	}
	public void setOpts(Options opts) {
		this.opts = opts;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((inFile == null) ? 0 : inFile.hashCode());
		result = prime * result + ((opts == null) ? 0 : opts.hashCode());
		result = prime * result + ((outFile == null) ? 0 : outFile.hashCode());
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
		FilterParams other = (FilterParams) obj;
		if (inFile == null) {
			if (other.inFile != null)
				return false;
		} else if (!inFile.equals(other.inFile))
			return false;
		if (opts == null) {
			if (other.opts != null)
				return false;
		} else if (!opts.equals(other.opts))
			return false;
		if (outFile == null) {
			if (other.outFile != null)
				return false;
		} else if (!outFile.equals(other.outFile))
			return false;
		if (stats == null) {
			if (other.stats != null)
				return false;
		} else if (!stats.equals(other.stats))
			return false;
		return true;
	}
	
	
	
	
}//end class
