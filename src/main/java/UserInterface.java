import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.Scanner;

class UserInterface {
	private Scanner reader;
	private PrintStream out;
	private PiCalculator calculator;
	
	UserInterface() {
		reader = new Scanner(System.in);
		out = System.out;
		calculator = new PiCalculator(2, out);
	}
	
	UserInterface(String testInput, PrintStream testOut) {
		this();
		reader = new Scanner(testInput);
		out = testOut;
		calculator = new PiCalculator(2, testOut);
	}
	
	void run() {
		out.println(UIMessages.STARTUP_MESSAGE);
		
		while (true) {
			out.println("Choose precision (meaningful digits):\n");
			String command = reader.nextLine();
			
			if (command.equalsIgnoreCase("x")) {
				out.println(UIMessages.CLOSE_MESSAGE);
				break;
			}
			
			Integer precision = convertInputToInt(command);
			if (precision != null && PiCalculator.precisionWithinRange(precision))
				calculate(precision);
			else
				out.println(UIMessages.BOUNDARY_ERROR);
		}
	}
	
	private void calculate(Integer precision) {
		calculator.setPrecision(precision);
		BigDecimal piResult = calculator.calculatePi();
		
		out.println("Pi with precision (" + precision + "): " + trimResult(piResult, precision));
	}
	
	private String trimResult(BigDecimal pi, int precision) {
		return pi.toString().substring(0, 2 + precision);
	}
	
	private Integer convertInputToInt(String input) {
		try {
			return Integer.parseInt(input);
		} catch (RuntimeException e) {
			out.println(UIMessages.BAD_INPUT);
		}
		
		return null;
	}
	
}
