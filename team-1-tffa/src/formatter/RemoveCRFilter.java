package formatter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * This filter removes all CR/LF combos, single CR's, and newline characers.
 * 
 * @author lance
 *
 */
public class RemoveCRFilter extends FormatFilter {

	
	public RemoveCRFilter(FilterParams params) {super(params);}

	
	@Override
	public void format() {

		Path thisWillChange; //path from stats goes here
		
		Path path = Paths.get("src/main/resources/shakespeare.txt");
		try {

		  Files.lines(path).filter(line -> line.contains("\n")); 

		} catch (IOException ex) {
		  ex.printStackTrace();//handle exception here
		}

		
	}//end format()

}//end class
