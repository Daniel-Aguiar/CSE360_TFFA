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
		try (Stream<String> lines = Files.lines(params.getInFile())) {
			   List<String> replaced = lines
			       .map(line-> line.replaceAll("\\t|  ", " "))
			       .collect(Collectors.toList());
			   Files.write(params.getOutFile(), replaced);
			}catch (IOException e) {
				  e.printStackTrace();
			}

	}//end format()

}//end class
