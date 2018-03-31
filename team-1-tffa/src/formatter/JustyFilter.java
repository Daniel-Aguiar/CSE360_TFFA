package formatter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;


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
		
		iJustify justifier = JustyFactory.getJustifier(params.getOpts().getJusty());  
		
		try (BufferedReader reader = Files.newBufferedReader(params.getInFile())) {
			try (BufferedWriter writer = Files.newBufferedWriter(params.getOutFile())) {
				String curLine = null;
					
				List<String> lines = new ArrayList<>();
				
				//put the lines into a list
				//this allow for finding the last line which does not get a \n
				for(int i = 0;(curLine = reader.readLine())!= null; ++i) {
					lines.add(i, curLine);
				}
				
				for(int i = 0; i < lines.size(); ++i) {
					curLine = justifier.justify(lines.get(i), params.getOpts().getMaxLineLength());
					
					if(i < lines.size() - 1) { //if it's not the last line
						curLine = curLine + '\n';
					}
					writer.write(curLine);
				}
			} catch (IOException e) {}
		} catch (IOException e) {}
	}//end format()
	
	
}//end class































