package model;

import org.junit.Test;
import static org.junit.Assert.*;
import java.math.BigDecimal;

public class OrderTest {

	@Test
	public void Test1() {
		Order o = new Order(new BigDecimal("493.87"), 15, "CA");
		assertEquals(new BigDecimal("7457.87"), o.getTotal());
	}
	
	@Test
	public void Test2() {
		Order o = new Order(new BigDecimal("1493.87"), 825, "UT");
		assertEquals(new BigDecimal("1119335.32"), o.getTotal());
	}

}
