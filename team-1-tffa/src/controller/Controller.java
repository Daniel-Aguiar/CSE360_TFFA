package controller;

import common.Capsule;

public class Controller {

	/* Members */


	// Should I have a private Capsule, or should GUI have control over the Capsule?

    // NOTE: Singleton implementation needed


    // To be called by GUI
    public Capsule goFormat() {
    		/*
    		 * Should make a call to Formatter, providing the path objects and the Statistics object
			 * Before format(), use options object to set max line length to 80
    		 * Then should call Analyzer, providing it the Statistics Object
    		 * Then should return the private Capsule object
    		 */
    }
}
