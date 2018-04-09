package formatter;

class BothJusty implements iJustify {
	/**
	 * Only process lines that are shorter than the max line length.
	 * It is assumed that a line is only longer than the max line length 
	 * if it contains only one word which is longer than the max line length.
	 */
	@Override
	public String justify(String input, int maxLineLen) {
		//check for the one big word condition 
		if(input.length() < maxLineLen) {
			String[] words = input.split(" ");
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
				
				characters++;
			}//end while
	
			StringBuilder output = new StringBuilder();
			
			for(int i = 0; i < words.length; ++i) {
				output.append(words[i]);
			}
			return output.toString();
		}else {
			return input;
		}
	}
	
	private static int countChars(String[] words) {
		
		int characters = 0;
		
		for(int j = 0; j < words.length; ++j) {
			characters += words[j].length(); 
		}
		
		return characters;
	}//end countChars()
	
}//end class
