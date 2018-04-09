package formatter;

import common.Justification;

class JustyFactory {

	protected static iJustify getJustifier(Justification just) {
		
		iJustify output = null;
		
		switch(just) {
		case RIGHT:
			output = new RightJusty();
			break;
		case BOTH:
			output = new BothJusty();
			break;
		case LEFT:
			output = new LeftJusty();
			break;
		}
		
		return output;
	}
	
}//end class
