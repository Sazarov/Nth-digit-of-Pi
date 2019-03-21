import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ui.UIMessages;
import ui.UserInterface;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class CalculatorSystemTest {
	private ApplicationRunner runner = new ApplicationRunner();
	private String endExecutionCommand = "\nx";
	private final double PI = Math.PI;
	
	private String slicePi(int precision) {
		return Double.toString(PI).substring(0, 2 + precision);
	}
	
	@Test
	public void shouldCloseUILoopWithX() {
		String output = runner.run(endExecutionCommand);
		assertThat(output, containsString(UIMessages.CLOSE_MESSAGE.toString()));
	}
	
	@Test
	public void endExecutionCommandIsCaseInsensitive() {
		String output = runner.run("X");
		assertThat(output, containsString(UIMessages.CLOSE_MESSAGE.toString()));
	}
	
	@Test
	public void outputShouldContainInputValue() {
		String testString = "5";
		String output = runner.run(testString + endExecutionCommand);
		
		assertThat(output, containsString(testString));
	}
	
	@Test
	public void shouldPrintErrorMsgIfInputInvalid() {
		String testString = "h";
		String errorMessage = UIMessages.BAD_INPUT.toString();
		String output = runner.run(testString + endExecutionCommand);
		
		assertThat(output, containsString(errorMessage));
		
	}
	
	@Test
	public void internal_slicePi() {
		int precision = 4;
		Assertions.assertTrue(slicePi(precision).length() == precision + 2);
	}
	
	@Test
	public void calculatesSignificantPiDigits() {
		int precision = 4;
		String testString = Integer.toString(precision);
		String output = runner.run(testString + endExecutionCommand);
		
		assertThat(output, containsString(slicePi(precision)));
	}
	
	@Test
	public void shouldHandleEmptyInput() {
		String testString = "";
		String errorMessage = UIMessages.BAD_INPUT.toString();
		String output = runner.run(testString + endExecutionCommand);
		
		assertThat(output, containsString(errorMessage));
	}
	
	@Test
	public void printsProgressForEachThread() {
		int numberOfThreads = 10;
		int testPrecision = UserInterface.PRECISION_THREADING_THRESHOLD+1;
		String testCommand = testPrecision + "\n" + numberOfThreads;
		List<String> workerStringList = new ArrayList<>();
		
		for (int i = 0; i < numberOfThreads ; i++) {
			workerStringList.add("Worker " + i + " is at ");
		}
		
		String output = runner.run(testCommand + endExecutionCommand);
		
		for (String workerStr :
				workerStringList) {
			assertThat(output, containsString(workerStr));
		}
	}
	
	@Test
	public void asksForThreadsWhenPrecisionGreaterThanThreshold() {
		int precisionOverThreshold = UserInterface.PRECISION_THREADING_THRESHOLD + 10;
		String threads = "\n5";
		String output = runner.run(precisionOverThreshold + threads + endExecutionCommand);
		
		assertThat(output, containsString(UIMessages.USE_THREADS.toString()));
	}
	
	@Test
	public void outputFromThreadedCalculationContainsMathPI() {
		int precisionOverThreshold = UserInterface.PRECISION_THREADING_THRESHOLD + 10;
		String threads = "\n10";
		String output = runner.run(precisionOverThreshold + threads + endExecutionCommand);
		
		assertThat(output, containsString(Double.toString(Math.PI)));
	}
}
