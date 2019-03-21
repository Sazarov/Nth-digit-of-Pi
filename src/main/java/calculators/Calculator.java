package calculators;

import java.math.BigDecimal;

public interface Calculator {
	public BigDecimal calculate();
	public void setPrecision(int precision);
	public int getPrecision();
}
