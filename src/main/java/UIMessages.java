public enum UIMessages {
	BAD_INPUT("Invalid precision. Please specify a number in the range of [1, " + PiPrecisionCalculator.MAX_PRECISION + "]"),
	BOUNDARY_ERROR("Cannot calculate Pi that precisely. Please pick a number between [1, " + PiPrecisionCalculator.MAX_PRECISION + "]"),
	STARTUP_MESSAGE(
			"*****************************************************************\n" +
					"**** Calculate PI with variable precision. Type \"x\" to quit. ****\n" +
					"*****************************************************************\n\n" +
					"Formula used: \n" +
					"Pi = ∑ 16^(-k) [ 4/(8*k+1) - 2/(8*k+4) - 1/(8*k+5) - 1/(8*k+6) ] \n" +
					"Read more: https://www.math.hmc.edu/funfacts/ffiles/20010.5.shtml \n"
	),
	CLOSE_MESSAGE("Closing application.");
	
	private String message;
	
	UIMessages(String message) {
		this.message = message;
	}
	
	public String toString() {
		return message;
	}
}
