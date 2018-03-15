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
		
		
		
		//only do anything if right justification is set.
		if(params.getOpts().getJusty().equalsIgnoreCase("right")) {
			int lineLength = params.getOpts().getMaxLineLength();
			
			try (BufferedReader reader = Files.newBufferedReader(params.getInFile())) {
				try (BufferedWriter writer = Files.newBufferedWriter(params.getOutFile())) {
					
					String curLine = null;
					while ((curLine = reader.readLine()) != null) {
						if(curLine.length() < lineLength) {
							
							int numSpaces = lineLength - curLine.length();
							
							StringBuilder spaces = new StringBuilder();
							
							for(int i=0;i<numSpaces;++i) {
								spaces.append(' ');
							}//end for
							
							spaces.append(curLine);
							spaces.append('\n');
							writer.write(spaces.toString());
							
						} else {
							curLine = curLine + '\n';
							writer.write(curLine);//end line length check
						}
					}//end while
				}catch (IOException e) {}
			} catch (IOException e) {}
		} else { //copy the input file to the output file since we didn't do anything.
			try {
				Files.copy(params.getInFile(), params.getOutFile(), java.nio.file.StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
	}//end format()
	
}//end class
