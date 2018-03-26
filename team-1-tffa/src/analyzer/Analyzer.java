package analyzer;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;

import common.Capsule;
import common.Justification;

public class Analyzer 
{
	private int wordCount;		// Total # of words
	private int lineCount;		// Total # of lines
	private double avgWpL;		// Average words per line
	private double avgLL;		// Average line length
	private int spaceCount;		// Total # of spaces
	private Capsule capsule;	// Capsule that will be updated with new Statistics
	
	public Analyzer(Capsule cap)	// Constructor
	{
		capsule = cap;
	}
	
	public Capsule analyze()
	{	
		wordCount = countWords();
		lineCount = countLines();
		avgWpL = calcAvgWpL();
		avgLL = calcAvgLL();
		spaceCount = countSpaces();
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
			    			// Empty indices caused by spaces. Don't count these.
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
		int lines = 0;
		
		try (BufferedReader reader = Files.newBufferedReader(capsule.getOutputFile())){
			while (reader.readLine() != null) {
					lines++;
			}
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
		    
		    if(capsule.getOptions().getJusty() == Justification.LEFT){	// Left Justification
			    while ((line = reader.readLine()) != null) {
			    	totalLineLength += line.length();
			    }
			    //System.out.println(totalLineLength);
		    }
			else	// Right and Both Justification
			{
				totalLineLength = lineCount * capsule.getOptions().getMaxLineLength();
			}
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}
		
		//	Calculate average line length.
		avgLL = (double)totalLineLength / lineCount;
		
		return avgLL;
	}

	private int countSpaces()
	{
		int totalSpaces = 0;
		int lineLength = 0;
		int wordsLength = 0;
		
		try (BufferedReader reader = Files.newBufferedReader(capsule.getOutputFile())) {
		    String line = null;
		    
		    while ((line = reader.readLine()) != null) {
			    	lineLength = line.length();	// Current line length (words and spaces)
			    	
			    	// Find the length of just the words in the line
			    	String[] split = line.split(" ");
			    	for(int i = 0; i < split.length; i++)
			    	{
			    		if(split[i].equals("")) {
			    			// Empty indices caused by spaces. Don't count these.
			    		} else {
			    			// Keep running total of the length of all the words in the current line
			    			wordsLength += split[i].length();
			    		}
			    	}
			    	
			    	// total spaces in current line = current line length - length of all words in current line
			    	// Add the number of spaces in the current line to running total
			    	totalSpaces += lineLength - wordsLength;
			    	
			    	lineLength = 0;		// Reset current line length
			    	wordsLength = 0;	// Reset length of all words in current line
		    }
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}
		
		return totalSpaces;
	}
	
	private void updateStatistics()
	{
		capsule.getStatistics().setTotalWords(wordCount);
		capsule.getStatistics().setTotalLines(lineCount);
		// Blank lines removed has already been filled in by Formatter.
		capsule.getStatistics().setAvgWpL(avgWpL);
		capsule.getStatistics().setAvgLL(avgLL);
		capsule.getStatistics().setTotalSpaces(spaceCount);
	}
}
