import calculators.PiCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PiCalculatorTest {
	@Test
	public void throwsErrorWithZeroPrecision() {
		PiCalculator calculator = new PiCalculator(0);
		assertThrows(IllegalArgumentException.class, () -> calculator.calculate());
	}
	
	@Test
	public void precisionWithinRangeReturnsFalseForUnderOne() {
		Assertions.assertFalse(PiCalculator.precisionWithinRange(0));
	}
	
	@Test
	public void precisionWithinRangeReturnsTrueUntilMaxInteger() {
		Assertions.assertTrue(PiCalculator.precisionWithinRange(Integer.MAX_VALUE));
	}
	
	@Test
	public void correctlySetsPrecision() {
		int precision = 5;
		PiCalculator calculator = new PiCalculator(0);
		calculator.setPrecision(precision);
		
		assertEquals(calculator.getPrecision(), precision);
	}
}
