package abp.p170718;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

public class CellSumTest extends Assert {

	@Test
	public void testGetSum() {
		CellSum cellsum = new CellSum();
		BigDecimal num1 = BigDecimal.valueOf(2.5);
		BigDecimal num2 = BigDecimal.valueOf(21.125);
		BigDecimal num3 = BigDecimal.valueOf(37.28);
		cellsum.add(num1);
		cellsum.add(num2);
		cellsum.add(num3);
		BigDecimal sum = cellsum.getSum();
		assertEquals("60.905", sum.toString());
	}
	
	@Test
	public void testGetMax() {
		CellSum cellsum = new CellSum();
		BigDecimal num1 = BigDecimal.valueOf(3.15);
		BigDecimal num2 = BigDecimal.valueOf(16.363);
		BigDecimal num3 = BigDecimal.valueOf(20.1);
		cellsum.add(num1);
		cellsum.add(num2);
		cellsum.add(num3);
		BigDecimal max = cellsum.getMax();
		assertEquals("20.1", max.toString());
	}
	
	@Test
	public void testGetMin() {
		CellSum cellsum = new CellSum();
		BigDecimal num1 = BigDecimal.valueOf(12.35);
		BigDecimal num2 = BigDecimal.valueOf(5.512);
		BigDecimal num3 = BigDecimal.valueOf(30.7);
		cellsum.add(num1);
		cellsum.add(num2);
		cellsum.add(num3);
		BigDecimal min = cellsum.getMin();
		assertEquals("5.512", min.toString());
	}
	
	@Test
	public void testGetAverage() {
		CellSum cellsum = new CellSum();
		BigDecimal num1 = BigDecimal.valueOf(10.15);
		BigDecimal num2 = BigDecimal.valueOf(20.724);
		BigDecimal num3 = BigDecimal.valueOf(30.6);
		cellsum.add(num1);
		cellsum.add(num2);
		cellsum.add(num3);
		BigDecimal avg = cellsum.getAverage();
		assertEquals("20.491", avg.toString());
	}

}
