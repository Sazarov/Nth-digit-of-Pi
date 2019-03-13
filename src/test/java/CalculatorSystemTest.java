import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

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
	public void printsProgressForEvery100Loops() {
		int testPrecision = 400;
		String prg = "Progress: ";
		String expected1 = prg + 100 +" / " + 400;
		String expected2 = prg + 200 +" / " +  400;
		String expected3 = prg + 300 +" / " +  400;
		String expected4 = prg + 400 +" / " +  400;
		
		String output = runner.run(testPrecision + endExecutionCommand);
		
		assertThat(output, containsString(expected1));
		assertThat(output, containsString(expected2));
		assertThat(output, containsString(expected3));
		assertThat(output, containsString(expected4));
	}
}
