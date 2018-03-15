package formatter;

import java.io.IOException;
import java.nio.file.Files;


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

		byte[] bytes = null;
		
		try {
			bytes = Files.readAllBytes(params.getInFile());
			
			for(int i=0;i<bytes.length;++i) {
				if(bytes[i] == '\r' || bytes[i] == '\n') {
					bytes[i] = ' ';
				}
			}
			Files.write(params.getOutFile(), bytes); //write the list to the output
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}//end try-catch
		
		
	}//end format()

}//end class
