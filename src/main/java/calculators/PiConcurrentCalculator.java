package calculators;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class PiConcurrentCalculator implements Calculator {
	private int numberOfThreads;
	private List<ConcurrentRunner> calculatorList;
	private int precision;
	private ExecutorService es;
	private boolean printProgress;
	public static final int MAX_THREADS = 50;
	
	public PiConcurrentCalculator() {
		this(1);
	}
	
	public PiConcurrentCalculator(int numberOfThreads) {
		this(numberOfThreads, 2);
	}
	
	public PiConcurrentCalculator(int numberOfThreads, int precision) {
		this.numberOfThreads = numberOfThreads;
		this.precision = precision;
		this.calculatorList = new ArrayList<>();
		this.es = Executors.newFixedThreadPool(numberOfThreads);
		
	}
	
	public int getNumberOfThreads() {
		return numberOfThreads;
	}
	
	public List<ConcurrentRunner> getCalculatorList() {
		return calculatorList;
	}
	
	public void setPrintProgress(boolean printProgress) {
		this.printProgress = printProgress;
	}
	
	public static String stringifyAndTrim(BigDecimal result, int precision) {
		return result.toString().substring(0, 2 + precision);
	}
	
	public static boolean validNumberOfThreads(int numberOfThreads) {
		return numberOfThreads >= 1 && numberOfThreads <= MAX_THREADS;
	}
	
	public BigDecimal calculate() {
		int[] ranges = getRanges();
		Future<BigDecimal>[] results = beginCalculation(ranges);
		
		return sumResults(results);
	}
	
	@Override
	public void setPrecision(int precision) {
		this.precision = precision;
	}
	
	@Override
	public int getPrecision() {
		return precision;
	}
	
	protected BigDecimal sumResults(Future<BigDecimal>[] results) {
		BigDecimal sum = new BigDecimal(0);
		
		for (Future result :
				results) {
			try {
				if (result.get() instanceof BigDecimal)
					sum = sum.add((BigDecimal) (result.get()));
			} catch (ExecutionException | InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		return sum;
	}
	
	protected Future<BigDecimal>[] beginCalculation(int[] ranges) {
		Future<BigDecimal>[] results = new Future[numberOfThreads];
		
		for (int i = 0; i < numberOfThreads; i++) {
			calculatorList.add(i, new ConcurrentRunner(ranges[i], ranges[i + 1], precision, i, printProgress));
			results[i] = es.submit(calculatorList.get(i));
		}
		
		return results;
	}
	
	protected int[] getRanges() {
		int[] ranges = new int[numberOfThreads + 1];
		
		for (int i = 0; i < ranges.length; i++) {
			double partitionSize = Math.ceil((double) (precision) / numberOfThreads);
			ranges[i] = i * (int) (partitionSize);
		}
		
		return ranges;
	}
}
