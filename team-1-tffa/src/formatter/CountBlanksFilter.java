package formatter;

import java.nio.file.Path;

import common.Options;
import common.Statistics;


/**
 * This filter counts the blank lines, and sets blank line count in the returned stats object.
 * For the purposes of this filter a blank line is a line that consists of only whitespace 
 * and/or the CR/LF combo, a single <CR> or a single newline character. 
 * 
 * @author lance
 *
 */
public class CountBlanksFilter extends FormatFilter {

	
	public CountBlanksFilter(FilterParams params) {
		super(params);
	}


	@Override
	public void format() {
		
		params.getStats().setBlankLines(countBlankLines(params.getInFile()));
		
	}//end format()

	
	private int countBlankLines(Path theFile) {
		
		int count;
		
		//get a line 
		//read each character 
		//if a non-whitespace character exists in the line then it's not a blank line 
		//if a non-whitespace character was not found increment the blank line counter.
		//use a regex for this.
		
		
		
		
		return 0;
	}//end countBlankLines()
	
	
}//end class
