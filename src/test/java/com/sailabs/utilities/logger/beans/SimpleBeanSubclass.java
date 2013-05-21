package com.sailabs.utilities.logger.beans;


import java.math.BigDecimal;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.stereotype.Component;

import com.sailabs.utilities.logger.LogLevel;
import com.sailabs.utilities.logger.aspects.Loggable;

@Component(value = "simpleBeanSubclass")
public class SimpleBeanSubclass extends SimpleBean {

	private BigDecimal decimalProperty;

	@Loggable(value = LogLevel.TRACE)
	public BigDecimal getDecimalProperty() {
		return decimalProperty;
	}

	@Loggable(value = LogLevel.TRACE)
	public void setDecimalProperty(final BigDecimal decimalProperty) {
		this.decimalProperty = decimalProperty;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("decimalProperty", decimalProperty)
				.appendSuper(super.toString()).toString();
	}
}