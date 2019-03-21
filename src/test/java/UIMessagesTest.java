import calculators.PiCalculator;
import org.junit.jupiter.api.Test;
import ui.UIMessages;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UIMessagesTest {
	
	@Test
	public void hasPredefinedMessages() {
		String badInput = "Invalid precision. Please specify a number in the range of [1, " + PiCalculator.MAX_PRECISION + "]";
		String startMsg = "*****************************************************************\n" +
				"**** Calculate PI with variable precision. Type \"x\" to quit. ****\n" +
				"*****************************************************************\n\n" +
				"Formula used: \n" +
				"Pi = ∑ 16^(-k) [ 4/(8*k+1) - 2/(8*k+4) - 1/(8*k+5) - 1/(8*k+6) ] \n" +
				"Read more: https://www.math.hmc.edu/funfacts/ffiles/20010.5.shtml \n";
		String closeMsg = "Closing application.";
		
		assertEquals(startMsg, UIMessages.STARTUP_MESSAGE.toString());
		assertEquals(badInput, UIMessages.BAD_INPUT.toString());
		assertEquals(closeMsg, UIMessages.CLOSE_MESSAGE.toString());
	}
}
