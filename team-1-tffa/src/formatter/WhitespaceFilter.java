package formatter;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Removes tabs and double spaces from the input file. Saves to a new output file.
 * @author Lance
 *
 */
public class WhitespaceFilter extends FormatFilter {

	public WhitespaceFilter(FilterParams params) {
		super(params);
	}

	@Override
	public void format() {
		
		
		//same code structure here from CountBlanksFilter 
		try (Stream<String> lines = Files.lines(params.getInFile())) {
		   List<String> replaced = lines
		       .map(line-> line.replaceAll("\\s{2,}", " ")) //regex to match 2 or more spaces and replace with 1
		       .map(line-> line.replaceAll("\\t", " ")) //replace tabs with spaces.
		       .collect(Collectors.toList());
			   
		   //remove the last space if there is one.
		   if (replaced.get(0).lastIndexOf(' ') == replaced.get(0).length() - 1)
			   replaced.set(0, replaced.get(0).substring(0,replaced.get(0).lastIndexOf(' ')));
			   
		   Files.write(params.getOutFile(), replaced.get(0).getBytes());
		}catch (IOException e) {
			  e.printStackTrace();
		}

	}//end format()

}//end class
