package formatter;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * This filter removes all newline characters with a space.
 * This should work on Mac, PC, and Linux.
 * 
 * @author lance
 *
 */
public class RemoveCRFilter extends FormatFilter {

	
	public RemoveCRFilter(FilterParams params) {super(params);}

	
	@Override
	public void format() {

		try (Stream<String> lines = Files.lines(params.getInFile())) {
		   List<String> replaced = lines
		       .map(line-> line.replaceAll("\\r\\n|\\r|\\n", " "))
		       .collect(Collectors.toList());
		   Files.write(params.getOutFile(), replaced);
		}catch (IOException e) {
			  e.printStackTrace();
		}
		
	}//end format()

}//end class
