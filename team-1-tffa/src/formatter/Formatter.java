package formatter;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import common.Capsule;
import common.Options;
import common.Statistics;

/**
 * This class is responsible for formatting the text file.
 * It uses independent filters of type <FormatFilter> to accomplish this.
 * In this way the format can be changed easily.
 * In addition the buildGoList helper method could be placed in the controller to
 * allow the user more control over how a file is formatted.  This wild probably
 * involve the builder pattern.    
 *  
 * @author lance
 *
 */
public class Formatter {
	
	private Options opts;
	private Statistics stats;
	private Capsule caps;
	
	
	public Formatter(Capsule capsule) {
		this.opts = capsule.getOptions();
		this.stats = capsule.getStatistics();
		this.caps = capsule;
	}
	
	
	/**
	 * Formats the file.
	 * This uses the Options and Statistics objects set in the constructor.
	 * 
	 */
	public Capsule formatInputFile() {
		
		List<FormatFilter> goList;
		
		goList = buildGoList();
		
		//iterate through the list and apply each filter.
		//this version of a for loop ensures the proper order.
		//a for-each loop might not execute the the right order.
		for(int i=0; i < goList.size(); ++i) {
			goList.get(i).format();
		}
		
		return caps;
	}//end formatInFile()
		
	
	
	/**
	 * This sets the order for the format filters.
	 * @return
	 * A List<FormatFilter> that is to be executed in the order of their index. 
	 */
	private List<FormatFilter> buildGoList() {
		
		List<FormatFilter> output = new ArrayList<>();
		
		FilterParams params = new FilterParams();
		
		Path outfile;
		
		//count the blank lines
		params.setOpts(opts);
		params.setStats(stats);
		params.setInFile(caps.getInputFile());
		output.add(new CountBlanksFilter(params));

		//strip off all newlines 
		params = new FilterParams();
		params.setOpts(opts);
		params.setStats(stats);
		params.setInFile(caps.getInputFile());
		outfile = Paths.get("stage2");
		params.setOutFile(outfile);
		output.add(new RemoveCRFilter(params));

		//strip off extra whitespace
		params = new FilterParams();
		params.setOpts(opts);
		params.setStats(stats);
		params.setInFile(outfile);
		outfile = Paths.get("stage3");
		params.setOutFile(outfile);
		output.add(new WhitespaceFilter(params));
		
		//ensure proper line length
		params = new FilterParams();
		params.setOpts(opts);
		params.setStats(stats);
		params.setInFile(outfile);
		outfile = Paths.get("stage4");
		params.setOutFile(outfile);
		output.add(new LineLengthFilter(params));
		
		//justify
		params = new FilterParams();
		params.setOpts(opts);
		params.setStats(stats);
		params.setInFile(outfile);
		params.setOutFile(caps.getOutputFile());
		output.add(new JustyFilter(params));
		
		return output;
		
	}//end buildGoList()
	
	
	
	
	
	
	
	
	
	
}//end class
