package controller;

import common.Capsule;
import common.Options;

import formatter.Formatter;
import analyzer.Analyzer;
import gui.TFFAGui;

public class Controller {

	/* Members */


	// Should I have a private Capsule, or should GUI have control over the Capsule?

    // NOTE: Singleton implementation needed


    // To be called by GUI
    public Capsule goFormat(TffaGui gui) {
    	
    		Capsule cap = new Capsule();
    		
    		String inputPathName = gui.getInputFileName();
    		String outputPathName = gui.getOutputFileName();
    		Options opts = gui.getOptions();
    		
    		cap.setInputFile(inputPathName);
    		cap.setOutputFile(outputPathName);
    		cap.setOptions(opts);
    		
    		Formatter fmt = new Formatter(cap);
    		cap = fmt.format();
    		
    		Formatter anlyz = new Analyzer(cap);
    		cap = anlyz.analyze();
    		
    		return cap;
    }
}
