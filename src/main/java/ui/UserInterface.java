package ui;

import calculators.Calculator;
import calculators.PiCalculator;
import calculators.PiConcurrentCalculator;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.Scanner;

public class UserInterface {
	private Scanner reader;
	private PrintStream out;
	private PiCalculator calculator;
	public static final int PRECISION_THREADING_THRESHOLD = 10_000;
	
	public UserInterface() {
		reader = new Scanner(System.in);
		out = System.out;
		calculator = new PiCalculator(2, out);
	}
	
	public UserInterface(String testInput, PrintStream testOut) {
		this();
		reader = new Scanner(testInput);
		out = testOut;
		calculator = new PiCalculator(2, testOut);
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
			
			if (precision != null && PiCalculator.precisionWithinRange(precision))
				pickCalculator(precision);
			else
				out.println(UIMessages.BOUNDARY_ERROR);
		}
	}
	
	private void pickCalculator(int precision) {
		if (precision > PRECISION_THREADING_THRESHOLD)
			calculateWithThreads(precision);
		else
			calculateSingleThreaded(precision);
	}
	
	private void calculateWithThreads(Integer precision) {
		out.println(UIMessages.USE_THREADS);
		Integer threads = convertInputToInt(reader.nextLine());
		if (threads != null && PiConcurrentCalculator.validNumberOfThreads(threads)) {
			PiConcurrentCalculator cr = new PiConcurrentCalculator(threads, precision);
			cr.setPrintProgress(true);
			calculate(cr);
		}
	}
	
	private void calculate(Calculator calculator) {
		long startMilis = System.currentTimeMillis();
		
		BigDecimal result = calculator.calculate();
		
		long endMillis = System.currentTimeMillis();
		long timeElapsed = endMillis - startMilis;
		
		printResult(result, timeElapsed, calculator.getPrecision());
	}
	
	private void printResult(BigDecimal result, long timeElapsed, int precision) {
		long hours = (timeElapsed / 1000) / 60 / 60;
		long minutes = (timeElapsed / 1000) / 60;
		long seconds = (timeElapsed / 1000) % 60;
		
		out.println("Pi with precision (" + precision + "): " + trimResult(result, precision));
		out.println("This calculation took: " + hours + "h " + minutes + "m " + seconds + "s");
		
	}
	
	private void calculateSingleThreaded(Integer precision) {
		calculator.setPrecision(precision);
		calculate(calculator);
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
