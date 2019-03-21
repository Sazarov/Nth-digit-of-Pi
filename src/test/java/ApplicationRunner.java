import calculators.ThreadProgressPrinter;
import ui.UserInterface;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ApplicationRunner {
	public String run(String testString) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PrintStream pr = new PrintStream(outputStream);
		ThreadProgressPrinter.out = pr;
		
		UserInterface app = new UserInterface(testString, pr);
		app.run();
		
		return outputStream.toString();
	}
}
