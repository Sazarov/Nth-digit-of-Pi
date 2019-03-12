import java.io.PrintStream;
import java.util.Scanner;

public class UserInterface {
	private Scanner reader;
	private PrintStream out;
	private PiPrecisionCalculator calculator;
	
	public UserInterface(String testInput, PrintStream testOut) {
		this();
		reader = new Scanner(testInput);
		out = testOut;
	}
	
	public UserInterface() {
		reader = new Scanner(System.in);
		out = System.out;
		calculator = new PiPrecisionCalculator();
	}
	
	public void run() {
		out.println(UIMessages.STARTUP_MESSAGE);
		while (true) {
			out.println("Choose precision (meaningful digits):\n");
			String command = reader.nextLine();
			
			if (command.equalsIgnoreCase("x")) {
				out.println(UIMessages.CLOSE_MESSAGE);
				break;
			}
			
			Integer precision = convertInputToInt(command);
			if (precision != null && PiPrecisionCalculator.precisionWithinRange(precision))
				calculate(precision);
			else {
				out.println(UIMessages.BOUNDARY_ERROR);
				continue;
			}
		}
	}
	
	private void calculate(Integer precision) {
		calculator.setPrecision(precision);
		double piResult = calculator.calculatePi();
		
		out.println("Pi with precision (" + precision + "): " + trimResult(piResult, precision));
	}
	
	private String trimResult(double pi, int precision) {
		return Double.toString(pi).substring(0, 2 + precision);
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
