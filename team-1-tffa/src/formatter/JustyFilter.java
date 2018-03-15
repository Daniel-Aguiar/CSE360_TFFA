package formatter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;

/**
 * This class assumes the file is already left justified.
 * @author lance
 *
 */
public class JustyFilter extends FormatFilter {

	
	public JustyFilter(FilterParams params) {
		super(params);
	}

	
	@Override
	public void format() {
		
		int lineLength = params.getOpts().getMaxLineLength();
		
		//only do anything if left justification is not set.
		if(!params.getOpts().getJusty().equalsIgnoreCase("left")) {
			try (BufferedReader reader = Files.newBufferedReader(params.getInFile())) {
				try (BufferedWriter writer = Files.newBufferedWriter(params.getOutFile())) {
					String curLine = null;
					while ((curLine = reader.readLine()) != null) {
						if(curLine.length() < lineLength) {
							int numSpaces = curLine.length() - lineLength;
							StringBuilder spaces = new StringBuilder();
							for(int i=0;i<numSpaces;++i) {
								spaces.append(' ');
							}//end for
							spaces.append(curLine);
							writer.write(spaces.toString());
						}//end line length check
					}//end while
				}catch (IOException e) {}
			} catch (IOException e) {}
		}//end if left check
		
		
	}//end format()
	
}//end class
