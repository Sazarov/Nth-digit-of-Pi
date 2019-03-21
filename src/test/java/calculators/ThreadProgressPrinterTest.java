package calculators;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class ThreadProgressPrinterTest {
	@Test
	public void startTimerReturnsFalseIfCalculatorIsNotSet() {
		ThreadProgressPrinter tpp = new ThreadProgressPrinter();
		assertFalse(tpp.startLogTimer());
	}
}
