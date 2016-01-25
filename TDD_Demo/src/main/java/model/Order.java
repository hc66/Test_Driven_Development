package model;

import java.math.BigDecimal;

public class Order {
	
	private int quantity;
	private BigDecimal price = new BigDecimal("0.00");;
	private String state;
	
	public Order(BigDecimal i, int j, String string) {
		this.price = i;
		this.quantity = j;
		this.state = string;
	}

	public BigDecimal getTotal() {
		BigDecimal result_before_scale = price.multiply(new BigDecimal(quantity));
		result_before_scale = result_before_scale.multiply(BigDecimal.ONE.subtract(getDiscount()));
		result_before_scale = result_before_scale.multiply(BigDecimal.ONE.add(getTaxRate()));
		BigDecimal result= result_before_scale.setScale(2, BigDecimal.ROUND_UP);// BigDecimal is immutable!!!
		return result;
	}
	
	public BigDecimal getTaxRate() {
		BigDecimal result = new BigDecimal("0.0000");
		switch(state) {
			case "UT":	
				result = new BigDecimal("0.0685"); 
				break;
			case "NV": 	
				result = new BigDecimal("0.0800"); 
				break;
			case "TX": 	
				result = new BigDecimal("0.0625"); 
				break;
			case "AL": 	
				result = new BigDecimal("0.0400"); 
				break;
			case "CA": 	
				result = new BigDecimal("0.0825"); 
				break;
		}
		return result;
	}
	
	public BigDecimal getDiscount() {
		BigDecimal result = new BigDecimal("0.00");
		BigDecimal total_before_discount = price.multiply(new BigDecimal(quantity));		
		if(total_before_discount.compareTo(ONE_THOUSAND)>=0&&total_before_discount.compareTo(FIVE_THOUSAND)<0) {
			result = new BigDecimal("0.03");
		} else if(total_before_discount.compareTo(FIVE_THOUSAND)>=0&&total_before_discount.compareTo(SEVEN_THOUSAND)<0) {
			result = new BigDecimal("0.05");
		} else if(total_before_discount.compareTo(SEVEN_THOUSAND)>=0&&total_before_discount.compareTo(TEN_THOUSAND)<0) {
			result = new BigDecimal("0.07");
		} else if(total_before_discount.compareTo(TEN_THOUSAND)>=0&&total_before_discount.compareTo(FIFTY_THOUSAND)<0) {
			result = new BigDecimal("0.10");
		} else if(total_before_discount.compareTo(FIFTY_THOUSAND)>=0) {
			result = new BigDecimal("0.15");
		}
		return result;
	}
	
	private static final BigDecimal ONE_THOUSAND = new BigDecimal(1000.00);
	private static final BigDecimal FIVE_THOUSAND = new BigDecimal(5000.00);
	private static final BigDecimal SEVEN_THOUSAND = new BigDecimal(7000.00);
	private static final BigDecimal TEN_THOUSAND = new BigDecimal(10000.00);
	private static final BigDecimal FIFTY_THOUSAND = new BigDecimal(50000.00);
	
}
