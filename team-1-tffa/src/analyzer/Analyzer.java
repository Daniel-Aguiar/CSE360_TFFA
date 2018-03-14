import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Analyzer 
{
	private int wordCount;		// # words
	private int lineCount;		// # lines
	//private int blankLineCount;	// # blank lines removed
	private double avgWpL;		// avg words per line
	private double avgLL;		// avg line length
	//private ArrayList<WordData> list = new ArrayList<WordData>();	// To hold words and their occurrences.
	
	public Capsule analyze(Capsule cap)
	{
		Path outputFile = cap.getOutputFile();
		
		wordCount = countWords(outputFile);
		lineCount = countLines(outputFile);
		//blankLineCount = getBlankLines(cap);	// Don't need this method. Formatter fills this field.
		avgWpL = calcAvgWpL(wordCount, lineCount);
		avgLL = calcAvgLL(lineCount, outputFile);
		updateStatistics(wordCount, lineCount, avgWpL, avgLL, cap);
		return cap;
	}
	
	private int countWords(Path file) 
	{
		int words = 0;
		// To be completed...
		return words;
	}
	
	private int countLines(Path file) 
	{
		int lines = 0;
		
		try (BufferedReader reader = new BufferedReader(new StringReader(file.toString()))) {
		    String line = null;
		    while ((line = reader.readLine()) != null) {
		    	lines++;
		    }
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}
		
		return lines;
	}
	
	/*
	private int getBlankLines(Capsule cap)
	{
		Statistics temp;
		temp = cap.getStatistics();
		return temp.getBlankLines();
	}
	*/
	
	private double calcAvgWpL(int wordCount, int lineCount) 
	{
		double avgWpL = 0;
		
		avgWpL = wordCount / lineCount;
		return avgWpL;
	}
	
	private double calcAvgLL(int lineCount, Path file) 
	{
		double avgLL = 0;
		int totalLineLength = 0;

		try (BufferedReader reader = new BufferedReader(new StringReader(file.toString()))) {
		    String line = null;
		    while ((line = reader.readLine()) != null) {
		    	totalLineLength += line.length();
		    }
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}
		
		avgLL = lineCount / totalLineLength;
		return avgLL;
	}
	
	private void updateStatistics(int wordCount, int lineCount, double avgWpL, double avgLL, Capsule cap)
	{
		cap.getStatistics().setTotalWords(wordCount);
		cap.getStatistics().setTotalLines(lineCount);
		// Blank lines removed is already filled in.
		cap.getStatistics().setAvgWpL(avgWpL);
		cap.getStatistics().setAvgLL(avgLL);
	}
}
