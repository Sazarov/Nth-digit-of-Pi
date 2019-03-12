import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ApplicationRunner {
	public String run(String testString) {
		
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		
		UserInterface app = new UserInterface(testString, new PrintStream(outputStream));
		app.run();
		
		return outputStream.toString();
	}
}
