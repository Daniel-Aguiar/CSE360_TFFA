package formatter;

class RightJusty implements iJustify {

	
	/**
	 * Only process lines that are shorter than the max line length.
	 * It is assumed that a line is only longer than the max line length 
	 * if it contains only one word which is longer than the max line length.
	 */
	@Override
	public String justify(String input, int maxLineLen) {
		if(input.length() < maxLineLen && input.length() > 1) {
			int numSpaces = maxLineLen - input.length();

			StringBuilder spaces = makeSpaces(numSpaces);
		
			//add a number of spaces to the beginning of the line
			spaces.append(input);
		
			return spaces.toString();
		}
		else {
			return input;
		}
	}
	
	
	
	//creates a StringBuilder containing a number of spaces. 
	private StringBuilder makeSpaces(int numSpaces ) {
		StringBuilder spaces = new StringBuilder();
		
		for(int i=0;i<numSpaces;++i) {
			spaces.append(' ');
		}//end for
		
		return spaces;
		
	}//end makeSpaces()

}//end class










