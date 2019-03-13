import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PiPrecisionCalculatorTest {
	@Test
	public void throwsErrorWithZeroPrecision() {
		PiPrecisionCalculator calculator = new PiPrecisionCalculator(0);
		assertThrows(IllegalArgumentException.class, () -> calculator.calculatePi());
	}
	
	@Test
	public void precisionWithinRangeReturnsFalseForUnderOne() {
		Assertions.assertFalse(PiPrecisionCalculator.precisionWithinRange(0));
	}
	
	@Test
	public void precisionWithinRangeReturnsTrueUntilMaxInteger() {
		Assertions.assertTrue(PiPrecisionCalculator.precisionWithinRange(Integer.MAX_VALUE));
	}
	
	@Test
	public void correctlySetsPrecision() {
		int precision = 5;
		PiPrecisionCalculator calculator = new PiPrecisionCalculator(0);
		calculator.setPrecision(precision);
		
		assertEquals(calculator.getPrecision(), precision);
	}
}
