package controller;

import common.Statistics;
import common.Options;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Capsule {

    /* Private Members */
    private Path inputFile;
    private Path outputFile;
    private Statistics stats;
    private Options opts;

    public Capsule() {} // no-arg constructor
    
    /* Getters */
    public Path getInputFile() {
        return this.inputFile;
    }

    public Path getOutputFile() {
        return this.outputFile;
    }

    public Statistics getStatistics() {
        return this.stats;
    }

    public Options getOptions() {
        return this.opts;
    }

    /* Setters */
    public void setOutputFile(Path obj) {
        this.outputFile = obj;
    }

    public void setOutputFile(String filename) {
    		this.outputFile = Paths.get(filename);
    }

    public void setInputFile(Path obj) {
        this.inputFile = obj;
    }

    public void setInputFile(String filename) {
    		this.outputFile = Paths.get(filename);
    }

    public void setStatistics(Statistics obj) {
        this.stats = obj;
    }

    public void setOptions(Options obj) {
        this.opts = obj;
    }
}
