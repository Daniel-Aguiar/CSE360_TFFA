package analyzer;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;

import common.Capsule;

public class Analyzer 
{
	private int wordCount;		// Total # of words
	private int lineCount;		// Total # of lines
	private double avgWpL;		// Average words per line
	private double avgLL;		// Average line length
	private Capsule capsule;	// Capsule that will be updated with new Statistics
	
	public Analyzer(Capsule cap)
	{
		capsule = cap;
		cap.getOutputFile();
	}
	
	public Capsule analyze()
	{	
		wordCount = countWords();
		lineCount = countLines();
		avgWpL = calcAvgWpL();
		avgLL = calcAvgLL();
		updateStatistics();
		return capsule;
	}
	
	private int countWords() 
	{
		int words = 0;
		
		try (BufferedReader reader = Files.newBufferedReader(capsule.getOutputFile())) {
		    String line = null;
		    
		    while ((line = reader.readLine()) != null) {
			    	String[] split = line.split(" ");
			    	
			    	for(int i = 0; i < split.length; i++)
			    	{
			    		if(split[i].equals("")) {
			    			// Empty indices caused by spaces after right justification. Don't count these.
			    		} else {
			    			words++;
			    		}
			    	}
		    }
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}
		
		return words;
	}
	
	private int countLines() 
	{
//		int lines = 0;
//		
//		try (BufferedReader reader = new BufferedReader(new StringReader(outputFile.toString()))) {
//		    String line = null;
//		    while ((line = reader.readLine()) != null) {
//		    	lines++;
//		    }
//		} catch (IOException x) {
//		    System.err.format("IOException: %s%n", x);
//		}
//		
//		return lines;
		
		int lines = 0;
		
		try (BufferedReader reader = Files.newBufferedReader(capsule.getOutputFile())){
			while (reader.readLine() != null) {
					lines++;
			}//end while
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return lines;
	}

	private double calcAvgWpL() 
	{
		double avgWpL = 0;
		
		//	Calculate average words per line.
		avgWpL = (double)wordCount / lineCount;
		return avgWpL;
	}
	
	private double calcAvgLL()
	{
		double avgLL = 0;
		int totalLineLength = 0;

		// Calculate total line length of the file.
		try (BufferedReader reader = Files.newBufferedReader(capsule.getOutputFile())) {
		    String line = null;
		    
		    if(capsule.getOptions().getJusty().equalsIgnoreCase("left")){
			    while ((line = reader.readLine()) != null) {
			    	totalLineLength += line.length();
			    }
			    //System.out.println(totalLineLength);
		    }
			else
			{
				char[] temp;
				while ((line = reader.readLine()) != null) {
			    	temp = line.toCharArray();
			    	
			    	for(int i=temp.length-1; i>=0;i--)
			    	{
			    		if(temp[i] != ' ')
			    		{
			    			totalLineLength++;
			    		}
			    		else if(temp[i] == ' ' && temp[i-1] == ' ')
			    		{
			    			i = -1;
			    		}
			    		else {
			    			totalLineLength++;
			    		}
			    	}
			    }
				//System.out.println(totalLineLength);
			}
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}
		
		//	Calculate average line length.
		avgLL = (double)totalLineLength / lineCount;
		
		return avgLL;
	}

	private void updateStatistics()
	{
		capsule.getStatistics().setTotalWords(wordCount);
		capsule.getStatistics().setTotalLines(lineCount);
		// Blank lines removed has already been filled in by Formatter.
		capsule.getStatistics().setAvgWpL(avgWpL);
		capsule.getStatistics().setAvgLL(avgLL);
	}
}
