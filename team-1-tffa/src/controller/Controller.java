package controller;

import common.Capsule;
import common.Options;
import formatter.Formatter;

import java.nio.file.Path;
import java.nio.file.Paths;

import analyzer.Analyzer;
import gui.TFFAGui;

public class Controller {

	/* Singleton Implementation */
	private Controller() {}
	
	private static class ControllerHolder {
		private static final Controller INSTANCE = new Controller();
	}
	
	public static Controller getInstance() {
		return ControllerHolder.INSTANCE;
	}


    // To be called by GUI
    public Capsule goFormat(TFFAGui gui) {
    	
    		Capsule cap = new Capsule();
    		
    		Path inputPathName = Paths.get(gui.getInputFileName());
    		Path outputPathName = Paths.get(gui.getOutputFileName());
    		Options opts = gui.getOptions();

    		opts.setMaxLineLength(80);
    		
    		cap.setInputFile(inputPathName);
    		cap.setOutputFile(outputPathName);
    		cap.setOptions(opts);
    		
    		Formatter fmt = new Formatter(cap);
    		cap = fmt.formatInputFile();
    		
    		Analyzer anlyz = new Analyzer(cap);
    		cap = anlyz.analyze();
    		
    		return cap;
    }
}
