package formatter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;

import common.Justification;

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
		if(params.getOpts().getJusty() != Justification.LEFT) {
			int lineLength = params.getOpts().getMaxLineLength();
			
			try (BufferedReader reader = Files.newBufferedReader(params.getInFile())) {
				try (BufferedWriter writer = Files.newBufferedWriter(params.getOutFile())) {
					
					String curLine = null;
					while ((curLine = reader.readLine()) != null) {
						if(curLine.length() < lineLength) {
							
							if(params.getOpts().getJusty() == Justification.RIGHT)
								writer.write(addToBeginning(curLine, lineLength));
							
							if(params.getOpts().getJusty() == Justification.BOTH)
								writer.write(addToMiddle(curLine, lineLength));
							
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
		}//end if-else tree 
	}//end format()
	
	
	//add a number of spaces to the beginning of the line
	private String addToBeginning(String curLine, int maxLineLen) {
		int numSpaces = maxLineLen - curLine.length();
		
		StringBuilder spaces = makeSpaces(numSpaces);
		
		spaces.append(curLine);
		spaces.append('\n');
		
		return spaces.toString();
	}//end addToBeginning()
	
	
	//add a number of spaces between words.
	private static String addToMiddle(String curLine, int maxLineLen) {

		String[] words = curLine.split(" ");
		int characters = countChars(words);
		int wordIdx = 0;
		
		while(characters < maxLineLen) {

			if(wordIdx >= words.length - 2) {
				wordIdx = 0;
			}
			else {
				++wordIdx;
			}
			
			//add a space after the ith word
			words[wordIdx] = words[wordIdx] + " ";
			
			characters = countChars(words);
		}//end while
		

		StringBuilder output = new StringBuilder();
		
		for(int i = 0; i < words.length; ++i) {
			output.append(words[i]);
		}
		
		return output.toString();
	}//end addToMiddle()

	
	//creates a StringBuilder containing a number of spaces. 
	private StringBuilder makeSpaces(int numSpaces ) {
		StringBuilder spaces = new StringBuilder();
		
		for(int i=0;i<numSpaces;++i) {
			spaces.append(' ');
		}//end for
		
		return spaces;
		
	}//end makeSpaces()
	
	
	private static int countChars(String[] words) {
		
		int characters = 0;
		
		for(int j = 0; j < words.length; ++j) {
			characters += words[j].length(); 
		}
		
		return characters;
	}//end countChars()
	
}//end class































