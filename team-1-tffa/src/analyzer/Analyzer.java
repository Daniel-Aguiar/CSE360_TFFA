import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Path;

public class Analyzer 
{
	private int wordCount;		// Total # of words
	private int lineCount;		// Total # of lines
	private double avgWpL;		// Average words per line
	private double avgLL;		// Average line length
	private Capsule capsule;	// Capsule that will be updated with new Statistics
	private Path outputFile;	// Path to output file
	
	public Analyzer(Capsule cap)
	{
		capsule = cap;
		outputFile = cap.getOutputFile();
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
		
		try (BufferedReader reader = new BufferedReader(new StringReader(outputFile.toString()))) {
		    String line = null;
		    int size = 0;
		    
		    while ((line = reader.readLine()) != null) {
		    	String[] split = line.split(" ");
		    	size = split.length;
		    	words += size;
		    }
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}
		
		return words;
	}
	
	private int countLines() 
	{
		int lines = 0;
		
		try (BufferedReader reader = new BufferedReader(new StringReader(outputFile.toString()))) {
		    String line = null;
		    while ((line = reader.readLine()) != null) {
		    	lines++;
		    }
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
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
		try (BufferedReader reader = new BufferedReader(new StringReader(outputFile.toString()))) {
		    String line = null;
		    while ((line = reader.readLine()) != null) {
		    	totalLineLength += line.length();
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
