package formatter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

public class LineSpaceFilter extends FormatFilter {

	public LineSpaceFilter(FilterParams params) {
		super(params);
	}
	
	@Override
	public void format() {
		//only have to do something if the spacing is greater than 1.
		if(params.getOpts().getSpacing() > 1) {
			try (BufferedWriter writer = Files.newBufferedWriter(params.getOutFile())) {
				//this may throw a file io error
				List<String> lines = Files.readAllLines(params.getInFile());
				
				//get an array of the right size
				char[] breaks = new char[params.getOpts().getSpacing()];
				
				//put in as many breaks as needed
				Arrays.fill(breaks, '\n');
				
				String tmp; 
				
				//do not do this to the last line.
				for(int i = 0; i < lines.size() - 1; ++i) {
					tmp = lines.get(i) + new String(breaks);
					lines.set(i, tmp);
					writer.write(lines.get(i));
				}
				writer.write(lines.get(lines.size()-1));
				//write the last line
				
			} catch (IOException e) {}
		}else {//nothing done so just copy the input file to the output file.
			try {
				Files.copy(params.getInFile(), params.getOutFile(), java.nio.file.StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}//end top if-else
		
	
	}//end formtat()
}//end class
