package calculators;

import java.io.PrintStream;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

public class ThreadProgressPrinter {
	private ConcurrentRunner calc;
	private Timer timer;
	private int loopTimeMiliseconds;
	public static PrintStream out = System.out;
	
	ThreadProgressPrinter() {
		this(null);
	}
	
	ThreadProgressPrinter(ConcurrentRunner calc) {
		this.calc = calc;
		this.timer = new Timer();
		this.loopTimeMiliseconds = 3000;
	}
	
	boolean startLogTimer() {
		if (calc == null)
			return false;
		
		timer.scheduleAtFixedRate(getTimerTask(), 0, loopTimeMiliseconds);
		return true;
	}
	
	void stopLogTimer() {
		timer.cancel();
	}
	
	private TimerTask getTimerTask() {
		return new TimerTask() {
			public void run() {
				String time = LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss"));
				out.println("[" + time + "] Worker " + calc.getWorkerNumber() + " is at " + Math.round(calc.getProgress() * 100 * 100.0) / 100.0 + " %");
			}
		};
	}
}
