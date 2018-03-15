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
				//lambdas are pretty cool.  It's like scheme. 
				.map(line-> line.replaceAll("\\r\\n|\\r|\\n", " ")) //replace all the possible newlines with spaces
				.collect(Collectors.toList()); //this fires off the job since all this is lazy loading
			Files.write(params.getOutFile(), replaced); //write the list to the output
		}catch (IOException e) {}
		
	}//end format()

}//end class
