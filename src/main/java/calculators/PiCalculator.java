package calculators;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class PiCalculator implements Calculator {
	private int precision;
	private PrintStream out;
	
	public static final int MAX_PRECISION = Integer.MAX_VALUE;
	public static final int MIN_PRECISION = 1;
	
	public PiCalculator() {
		this(2);
	}
	
	public PiCalculator(int precision) {
		this(precision, System.out);
	}
	
	public PiCalculator(int precision, PrintStream out) {
		this.precision = precision;
		this.out = out;
	}
	
	public void setPrecision(int precision) {
		this.precision = precision;
	}
	
	public int getPrecision() {
		return precision;
	}
	
	public static boolean precisionWithinRange(int precision) {
		return precision >= MIN_PRECISION;
	}
	
	public BigDecimal calculate() {
		if (!precisionWithinRange(precision))
			throw new IllegalArgumentException();
		
		return calculateNthDigit();
	}
	
	protected BigDecimal calculateNthDigit() {
		BigDecimal tempSum = new BigDecimal(0);
		
		for (int i = 0; i < precision + 5; i++) {
			BigDecimal curr = coefficient(i).multiply(innerSum(i));
			tempSum = tempSum.add(curr);
		}
		
		return tempSum;
	}
	
	BigDecimal coefficient(int iter) {
		int enhancedPrecision = precision + 5;
		BigDecimal denominator = new BigDecimal(16).pow(iter);
		BigDecimal quotient = new BigDecimal(1).divide(denominator, enhancedPrecision, RoundingMode.DOWN);
		
		return quotient;
	}
	
	BigDecimal innerSum(int iter) {
		int enhancedPrecision = precision + 5;
		
		BigDecimal bigIter = new BigDecimal(iter);
		BigDecimal bigOne = new BigDecimal(1);
		BigDecimal bigTwo = new BigDecimal(2);
		BigDecimal bigFour = new BigDecimal(4);
		BigDecimal bigFive = new BigDecimal(5);
		BigDecimal bigSix = new BigDecimal(6);
		BigDecimal bigEight = new BigDecimal(8);
		
		BigDecimal term1 = bigFour.divide((bigEight.multiply(bigIter).add(bigOne)), enhancedPrecision, RoundingMode.DOWN);
		BigDecimal term2 = bigTwo.divide(((bigEight.multiply(bigIter).add(bigFour))), enhancedPrecision, RoundingMode.DOWN);
		BigDecimal term3 = bigOne.divide(((bigEight.multiply(bigIter).add(bigFive))), enhancedPrecision, RoundingMode.DOWN);
		BigDecimal term4 = bigOne.divide(((bigEight.multiply(bigIter).add(bigSix))), enhancedPrecision, RoundingMode.DOWN);
		
		return term1.subtract(term2).subtract(term3).subtract(term4);
	}
}
