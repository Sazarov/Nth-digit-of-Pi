package calculators;

import java.math.BigDecimal;
import java.util.concurrent.Callable;

public class ConcurrentRunner extends PiCalculator implements Callable<BigDecimal> {
	private int from;
	private int to;
	private int workerNumber;
	private double progress;
	private boolean usePrinter;
	
	public ConcurrentRunner(int from, int to) {
		super();
		this.from = from;
		this.to = to;
		this.workerNumber = 1;
	}
	
	public ConcurrentRunner(int from, int to, int precision) {
		super(precision);
		this.from = from;
		this.to = to;
		this.workerNumber = 1;
	}
	
	public ConcurrentRunner(int from, int to, int precision, int workerNumber) {
		super(precision);
		this.from = from;
		this.to = to;
		this.workerNumber = workerNumber;
	}
	
	public ConcurrentRunner(int from, int to, int precision, int workerNumber, boolean usePrinter) {
		super(precision);
		this.usePrinter = usePrinter;
		this.from = from;
		this.to = to;
		this.workerNumber = workerNumber;
	}
	
	public int getWorkerNumber() {
		return workerNumber;
	}
	
	public BigDecimal calculate() {
		if (!precisionWithinRange(super.getPrecision()))
			throw new IllegalArgumentException();
		
		return calculateNthDigit();
	}
	
	@Override
	protected BigDecimal calculateNthDigit() {
		BigDecimal tempSum = new BigDecimal(0);
		
		for (int i = from; i < to; i++) {
			BigDecimal curr = coefficient(i).multiply(innerSum(i));
			tempSum = tempSum.add(curr);
//			printProgress(i);
			setProgress(i);
		}
		
		return tempSum;
	}
	
	private void setProgress(int iteration) {
		progress = (double) (iteration - from) / (to - from);
	}
	
	public double getProgress() {
		return progress;
	}
	
	@Override
	public BigDecimal call() throws Exception {
		if(!usePrinter)
			return calculateNthDigit();
		else {
			ThreadProgressPrinter threadProgressPrinter = new ThreadProgressPrinter(this);
			threadProgressPrinter.startLogTimer();
			
			BigDecimal result = calculateNthDigit();
			
			threadProgressPrinter.stopLogTimer();
			
			return result;
		}
	}
}
