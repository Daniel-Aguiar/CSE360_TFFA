package formatter;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;

/**
 * This class assumes the input file is one long string with no newlines and a single space between words.
 */
public class LineLengthFilter extends FormatFilter {

	
	public LineLengthFilter(FilterParams params) {
		super(params);
	}
	

	@Override
	public void format() {
			
		int lineLen = params.getOpts().getMaxLineLength();
		int end = lineLen;
		int start = 0;
		byte[] bytes = null;
		
		try {
			bytes = Files.readAllBytes(params.getInFile());
			
			//iterate through the array MaxLineLen elements at a time
			while (end < bytes.length) {
				
				end = replacePrevSpace(bytes, end);
				
				if(end < 0) { //it found a word that was 80 characters or longer
					end = replaceNextSpace(bytes, start + lineLen);
				}
				
				start = end;
				end += lineLen;
			}//end while
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}//end try-catch
		
		//write the bytes to a file
		try (OutputStream outStream = Files.newOutputStream(params.getOutFile())){
			outStream.write(bytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}//end format()
	
	
	/**
	 * Finds the next space before end, and replaces it with a '\n'
	 * @param A
	 * The array to check
	 * @param end
	 * the index to start checking
	 * @return
	 * the index of the previous space or -1 if a '\n' was encountered first.
	 */
	private int replacePrevSpace(byte[] A, int end) {
		
		//find the previous space or '\n'
		while(end > 0 && A[end] != ' ' && A[end] != '\n') {
			end -= 1;
		}
		
		if(A[end] == '\n' || end <= 0) {
			end = -1;
		} else {
			A[end] = '\n';
		}
		return end;
	}//end replacePrevSpace()

	
	/**
	 * Finds the next space before end, and replaces it with a '\n'
	 * @param A
	 * The array to check
	 * @param idx
	 * the index to start checking
	 * @return
	 * the index of the previous space or -1 if a '\n' was encountered first.
	 */
	private int replaceNextSpace(byte[] A, int idx) {
		//find the next space or '\n'
		while(idx < A.length && A[idx] != ' ') {
			idx += 1;
		}
		
		//Check to see if we fell off the end of the array
		if(idx >= A.length) {
			idx = -1;
		} else {
			A[idx] = '\n';
		}
		return idx;
	}//end replaceNextSpace()
	
	
}//end class











































