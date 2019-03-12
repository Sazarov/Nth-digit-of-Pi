import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PiPrecisionCalculatorTest {
	@Test
	public void properlyCalculatesPiWithMaxPrecision() {
		PiPrecisionCalculator calculator = new PiPrecisionCalculator(PiPrecisionCalculator.MAX_PRECISION);
		double calculatedPi = calculator.calculatePi();
		
		assertEquals(calculatedPi, Math.PI);
	}
	
	@Test
	public void throwsErrorWithZeroPrecision() {
		PiPrecisionCalculator calculator = new PiPrecisionCalculator(0);
		assertThrows(IllegalArgumentException.class, () -> calculator.calculatePi());
	}
}
