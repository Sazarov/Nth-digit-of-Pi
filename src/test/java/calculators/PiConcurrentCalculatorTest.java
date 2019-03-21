package calculators;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Random;
import java.util.concurrent.Future;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PiConcurrentCalculatorTest {
	@Test
	public void concurrentRunnerCorrectlySetsThreads() {
		int numberOfThreads = new Random().nextInt(8)+1;
		PiConcurrentCalculator crunner = new PiConcurrentCalculator(numberOfThreads);
		
		assertEquals(crunner.getNumberOfThreads(), numberOfThreads);
	}
	
	@Test
	public void loopRangeArrayIs1LongerThanNumberOfThreads() {
		int numberOfThreads = 20;
		
		PiConcurrentCalculator crunner = new PiConcurrentCalculator(numberOfThreads, 100000);
		int[] rangesArray = crunner.getRanges();
		
		assertEquals(rangesArray.length, numberOfThreads+1);
	}
	
	@Test
	public void sumResultsReturnsProperResults() {
		int numberOfThreads = 2;
		int precision = 10;
		int[] ranges = {0, 5, 10};
		
		PiConcurrentCalculator crunner = new PiConcurrentCalculator(numberOfThreads, precision);
		
		String referencePi = "3.1415926535";
		
		Future<BigDecimal>[] testFutureResults = crunner.beginCalculation(ranges);
		BigDecimal computedPi = crunner.sumResults(testFutureResults);
		
		assertThat(computedPi.toString(), containsString(referencePi));
	}
	
	@Test
	public void stringifyAndTrimWorks() {
		double referencePi = 3.1415926535;
		BigDecimal testPi = new BigDecimal(3.1415926535);
		String expectedPiString = testPi.toString().substring(0, Double.toString(referencePi).length());
		int precision = 10;
		
		String output = PiConcurrentCalculator.stringifyAndTrim(testPi, precision);
		
		assertEquals(output.length(), expectedPiString.length());
		assertEquals(output, expectedPiString);
	}
	
	@Test
	public void calculationReturnsCorrectResults() {
		double referencePi = Math.PI;
		
		double result = createInstanceAndCompute(2, Double.toString(referencePi).length());
		
		assertEquals(result, referencePi);
	}
	
	private double createInstanceAndCompute(int numberOfThreads, int precision) {
		PiConcurrentCalculator crunner = new PiConcurrentCalculator(numberOfThreads, precision);
		BigDecimal computedPi = crunner.calculate();
		return Double.parseDouble(computedPi.toString().substring(0, precision));
	}
	
	@Test
	public void calculatesResultWithManyThreads() {
		double referencePi = Math.PI;
		
		double result = createInstanceAndCompute(18, Double.toString(referencePi).length());
		
		assertEquals(result, referencePi);
	}
}
