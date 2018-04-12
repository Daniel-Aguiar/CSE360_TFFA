package formatter;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import common.Capsule;
import common.Justification;
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
		
		if(!teapotCheck()) {
		
			List<FormatFilter> goList;
			
			goList = buildGoList();
			
			//iterate through the list and apply each filter.
			//this version of a for loop ensures the proper order.
			//a for-each loop might not execute the the right order.
			for(int i=0; i < goList.size(); ++i) {
				goList.get(i).format();
			}
		} else {//handle the teapot case
			FilterParams params = new FilterParams();
			params.setOpts(opts);
			params.setStats(stats);
			params.setInFile(caps.getInputFile());
			params.setOutFile(Paths.get("teapot.txt"));
			FormatFilter teapotFtl = new CountBlanksFilter(params);
			teapotFtl.format();
		}
		
		
		return caps;
	}//end formatInFile()
		
	
	
	/**
	 * This sets the order for the format filters.
	 * Really should have this as a builder or at least some kind of factory.
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
		outfile = Paths.get("1cr");
		params.setOutFile(outfile);
		output.add(new RemoveCRFilter(params));

		//strip off extra whitespace
		params = new FilterParams();
		params.setOpts(opts);
		params.setStats(stats);
		params.setInFile(outfile); 
		//input file is output file of previous filter
		outfile = Paths.get("2ws");
		params.setOutFile(outfile); 
		output.add(new WhitespaceFilter(params));
		
		//ensure proper line length
		params = new FilterParams();
		params.setOpts(opts);
		params.setStats(stats);
		//input file is output file of previous filter
		params.setInFile(outfile);
		outfile = Paths.get("3ll");
		params.setOutFile(outfile);
		output.add(new LineLengthFilter(params));
		
		//set  line spacing
		params = new FilterParams();
		params.setOpts(opts);
		params.setStats(stats);
		//input file is output file of previous filter
		params.setInFile(outfile);
		outfile = Paths.get("4ls");
		params.setOutFile(outfile);
		output.add(new LineSpaceFilter(params));
		
		//justify
		params = new FilterParams();
		params.setOpts(opts);
		params.setStats(stats);
		//input file is output file of previous filter
		params.setInFile(outfile);
		params.setOutFile(caps.getOutputFile());
		output.add(new JustyFilter(params));
		
		return output;
		
	}//end buildGoList()
	
	private boolean teapotCheck() {
		
//		line len  = 1729
//		output file = teapot.txt
//		right justy
//		double spaced
		
		boolean flag = false;
		
		if(this.caps.getOptions().getMaxLineLength() == 1729) {
			if(this.caps.getOutputFile().equals("teapot.txt")) {
				if(this.caps.getOptions().getJusty() == Justification.RIGHT) {
					if(this.caps.getOptions().getSpacing() == 2) {
						flag = true;
					}
				}
			}
		}//end obnoxious if tree 
		
		return flag;
	}
	
}//end class


