package com.sailabs.utilities.logger.aspects;


import java.math.BigDecimal;
import java.util.Calendar;
import java.util.TimeZone;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.sailabs.utilities.logger.LogLevel;
import com.sailabs.utilities.logger.MockLogger;
import com.sailabs.utilities.logger.MockLogger.LogMessage;
import com.sailabs.utilities.logger.beans.SimpleBean;
import com.sailabs.utilities.logger.beans.SimpleBeanSubclass;

@ContextConfiguration(locations = { "/applicationContext/applicationContext-aspect.xml", 
									"/applicationContext/applicationContext-logger.xml",
									"/applicationContext/applicationContext.xml" })
public class LoggingAspectTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private MockLogger logger;

	@Autowired
	@Qualifier(value = "simpleBean")
	public SimpleBean simpleBean;

	@Autowired
	public SimpleBeanSubclass simpleBeanSubclass;
	
	static String timezone = Calendar.getInstance().getTimeZone().getDisplayName(false, TimeZone.SHORT);

	@BeforeMethod
	public void before() {
		logger.setLogLevel(SimpleBean.class, LogLevel.TRACE);
		logger.setLogLevel(SimpleBeanSubclass.class, LogLevel.TRACE);
		logger.resetLoggers();
	}

	@Test(groups="datepropertytests")
	public void testSimpleBean_SetDateProperty() throws Exception {
		simpleBean.setDateProperty(DateUtils.parseDate("01/01/2010", new String[] { "dd/MM/yyyy" }));

		Assert.assertEquals(2, logger.getMessages(SimpleBean.class).size());
		assertEquals(logger.getMessages(SimpleBean.class).get(0), LogLevel.TRACE, "[ entering < setDateProperty > with params Fri Jan 01 00:00:00 "+timezone+" 2010 ]");
		assertEquals(logger.getMessages(SimpleBean.class).get(1), LogLevel.TRACE, "[ leaving < setDateProperty > ]");
	}

	@Test(groups="integerpropertytests")
	public void testSimpleBean_SetIntegerProperty() {
		simpleBean.setIntegerProperty(100);

		Assert.assertEquals(2, logger.getMessages(SimpleBean.class).size());
		assertEquals(logger.getMessages(SimpleBean.class).get(0), LogLevel.TRACE, "[ entering < setIntegerProperty > with params 100 ]");
		assertEquals(logger.getMessages(SimpleBean.class).get(1), LogLevel.TRACE, "[ leaving < setIntegerProperty > ]");
	}

	@Test(groups="stringpropertytests")
	public void testSimpleBean_SetStringProperty() {
		simpleBean.setStringProperty("stringProperty");

		Assert.assertEquals(2, logger.getMessages(SimpleBean.class).size());
		assertEquals(logger.getMessages(SimpleBean.class).get(0), LogLevel.TRACE, "[ entering < setStringProperty > with params stringProperty ]");
		assertEquals(logger.getMessages(SimpleBean.class).get(1), LogLevel.TRACE, "[ leaving < setStringProperty > ]");
	}

	@Test(dependsOnGroups="datepropertytests")
	public void testSimpleBean_GetDateProperty() {
		simpleBean.getDateProperty();

		Assert.assertEquals(2, logger.getMessages(SimpleBean.class).size());
		assertEquals(logger.getMessages(SimpleBean.class).get(0), LogLevel.TRACE, "[ entering < getDateProperty > ]");
		assertEquals(logger.getMessages(SimpleBean.class).get(1), LogLevel.TRACE, "[ leaving < getDateProperty > returning Fri Jan 01 00:00:00 "+timezone+" 2010 ]");
	}

	@Test(dependsOnGroups="integerpropertytests")
	public void testSimpleBean_GetIntegerProperty() {
		simpleBean.getIntegerProperty();

		Assert.assertEquals(2, logger.getMessages(SimpleBean.class).size());
		assertEquals(logger.getMessages(SimpleBean.class).get(0), LogLevel.TRACE, "[ entering < getIntegerProperty > ]");
		assertEquals(logger.getMessages(SimpleBean.class).get(1), LogLevel.TRACE, "[ leaving < getIntegerProperty > returning 100 ]");
	}

	@Test(dependsOnGroups="stringpropertytests")
	public void testSimpleBean_GetStringProperty() {
		simpleBean.getStringProperty();

		Assert.assertEquals(2, logger.getMessages(SimpleBean.class).size());
		assertEquals(logger.getMessages(SimpleBean.class).get(0), LogLevel.TRACE, "[ entering < getStringProperty > ]");
		assertEquals(logger.getMessages(SimpleBean.class).get(1), LogLevel.TRACE, "[ leaving < getStringProperty > returning stringProperty ]");
	}

	@Test(groups="datepropertytests")
	public void testSimpleBeanSubclass_SetDateProperty() throws Exception {
		simpleBeanSubclass.setDateProperty(DateUtils.parseDate("01/01/2010", new String[] { "dd/MM/yyyy" }));

		Assert.assertEquals(2, logger.getMessages(SimpleBeanSubclass.class).size());
		assertEquals(logger.getMessages(SimpleBeanSubclass.class).get(0), LogLevel.TRACE, "[ entering < setDateProperty > with params Fri Jan 01 00:00:00 "+timezone+" 2010 ]");
		assertEquals(logger.getMessages(SimpleBeanSubclass.class).get(1), LogLevel.TRACE, "[ leaving < setDateProperty > ]");
	}

	@Test(groups="decimalpropertytests")
	public void testSimpleBeanSubclass_SetDecimalProperty() {
		simpleBeanSubclass.setDecimalProperty(new BigDecimal("0.25"));

		Assert.assertEquals(2, logger.getMessages(SimpleBeanSubclass.class).size());
		assertEquals(logger.getMessages(SimpleBeanSubclass.class).get(0), LogLevel.TRACE, "[ entering < setDecimalProperty > with params 0.25 ]");
		assertEquals(logger.getMessages(SimpleBeanSubclass.class).get(1), LogLevel.TRACE, "[ leaving < setDecimalProperty > ]");
	}

	@Test(groups="integerpropertytests")
	public void testSimpleBeanSubclass_SetIntegerProperty() {
		simpleBeanSubclass.setIntegerProperty(100);

		Assert.assertEquals(2, logger.getMessages(SimpleBeanSubclass.class).size());
		assertEquals(logger.getMessages(SimpleBeanSubclass.class).get(0), LogLevel.TRACE, "[ entering < setIntegerProperty > with params 100 ]");
		assertEquals(logger.getMessages(SimpleBeanSubclass.class).get(1), LogLevel.TRACE, "[ leaving < setIntegerProperty > ]");
	}

	@Test(groups="stringpropertytests")
	public void testSimpleBeanSubclass_SetStringProperty() {
		simpleBeanSubclass.setStringProperty("stringProperty");

		Assert.assertEquals(2, logger.getMessages(SimpleBeanSubclass.class).size());
		assertEquals(logger.getMessages(SimpleBeanSubclass.class).get(0), LogLevel.TRACE,"[ entering < setStringProperty > with params stringProperty ]");
		assertEquals(logger.getMessages(SimpleBeanSubclass.class).get(1), LogLevel.TRACE, "[ leaving < setStringProperty > ]");
	}

	@Test(dependsOnGroups="datepropertytests")
	public void testSimpleBeanSubclass_GetDateProperty() {
		simpleBeanSubclass.getDateProperty();

		Assert.assertEquals(2, logger.getMessages(SimpleBeanSubclass.class).size());
		assertEquals(logger.getMessages(SimpleBeanSubclass.class).get(0), LogLevel.TRACE, "[ entering < getDateProperty > ]");
		assertEquals(logger.getMessages(SimpleBeanSubclass.class).get(1), LogLevel.TRACE, "[ leaving < getDateProperty > returning Fri Jan 01 00:00:00 "+timezone+" 2010 ]");
	}

	@Test(dependsOnGroups="decimalpropertytests")
	public void testSimpleBeanSubclass_GetDecimalProperty() {
		simpleBeanSubclass.getDecimalProperty();

		Assert.assertEquals(2, logger.getMessages(SimpleBeanSubclass.class).size());
		assertEquals(logger.getMessages(SimpleBeanSubclass.class).get(0), LogLevel.TRACE, "[ entering < getDecimalProperty > ]");
		assertEquals(logger.getMessages(SimpleBeanSubclass.class).get(1), LogLevel.TRACE, "[ leaving < getDecimalProperty > returning 0.25 ]");
	}

	@Test(dependsOnGroups="integerpropertytests")
	public void testSimpleBeanSubclass_GetIntegerProperty() {
		simpleBeanSubclass.getIntegerProperty();

		Assert.assertEquals(2, logger.getMessages(SimpleBeanSubclass.class).size());
		assertEquals(logger.getMessages(SimpleBeanSubclass.class).get(0), LogLevel.TRACE, "[ entering < getIntegerProperty > ]");
		assertEquals(logger.getMessages(SimpleBeanSubclass.class).get(1), LogLevel.TRACE, "[ leaving < getIntegerProperty > returning 100 ]");
	}

	@Test(dependsOnGroups="stringpropertytests")
	public void testSimpleBeanSubclass_GetStringProperty() {
		simpleBeanSubclass.getStringProperty();

		Assert.assertEquals(2, logger.getMessages(SimpleBeanSubclass.class).size());
		assertEquals(logger.getMessages(SimpleBeanSubclass.class).get(0), LogLevel.TRACE, "[ entering < getStringProperty > ]");
		assertEquals(logger.getMessages(SimpleBeanSubclass.class).get(1), LogLevel.TRACE, "[ leaving < getStringProperty > returning stringProperty ]");
	}

	private void assertEquals(LogMessage logMessage, LogLevel logLevel, String message) {
		Assert.assertEquals(logLevel, logMessage.getLogLevel());
		Assert.assertEquals(message, logMessage.getMessage());
	}
	
}
