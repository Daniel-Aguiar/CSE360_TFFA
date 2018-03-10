package formatter;

public abstract class FormatFilter {

	
	//only subclasses of this class in the same package can read this var.
	@SuppressWarnings("unused")
	FilterParams params;
	
	@SuppressWarnings("unused")
	private FormatFilter() {}
	
	public FormatFilter(FilterParams params) {
		this.params = params;
	}
	
	public abstract void format();
	
	
}//end class
