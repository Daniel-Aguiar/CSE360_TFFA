package formatter;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


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
		
		int count = 0;
	
		try (BufferedReader reader = Files.newBufferedReader(theFile)){
			String curLine;
			while ((curLine = reader.readLine()) != null) {
				  if (curLine.trim().isEmpty()) {
				    count++;
				  }
				}//end while

		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		return count;
	}//end countBlankLines()
	
	
}//end class
