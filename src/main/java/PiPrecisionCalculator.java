public class PiPrecisionCalculator {
	private int precision;
	
	public static final int MAX_PRECISION = 15;
	public static final int MIN_PRECISION = 1;
	
	public PiPrecisionCalculator() {
		this(2);
	}
	
	public PiPrecisionCalculator(int precision) {
		this.precision = precision;
	}
	
	public double calculatePi() {
		if (!precisionWithinRange(precision))
			throw new IllegalArgumentException();
		
		return calculateNthDigit();
	}
	
	public void setPrecision(int precision) {
		this.precision = precision;
	}
	
	public static boolean precisionWithinRange(int precision) {
		return precision >= MIN_PRECISION && precision <= MAX_PRECISION;
	}
	
	private double calculateNthDigit() {
		double tempSum = 0;
		
		for (int i = 0; i < precision; i++) {
			tempSum += Math.pow(16, -i) * innerSum(i);
		}
		
		return tempSum;
	}
	
	private double innerSum(int iter) {
		double term1 = 4.0 / (8 * iter + 1);
		double term2 = 2.0 / (8 * iter + 4);
		double term3 = 1.0 / (8 * iter + 5);
		double term4 = 1.0 / (8 * iter + 6);
		
		return term1 - term2 - term3 - term4;
	}
}
