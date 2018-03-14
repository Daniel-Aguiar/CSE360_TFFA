package formatter;

import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Stream;

public class LineLengthFilter extends FormatFilter {

	
	public LineLengthFilter(FilterParams params) {
		super(params);
	}
	

	@Override
	public void format() {
		
		try (Stream<String> lines = Files.lines(params.getInFile())) {
			
			//get X # of chars
			//rewind to the previous space
			//exchange the space with a \n
			//write line to file
			
		}catch (IOException e) {
			  e.printStackTrace();
		}
		
	}//end format()

	
	
	private char[] readChars() {
		return null;
	}
	
	
}//end class
