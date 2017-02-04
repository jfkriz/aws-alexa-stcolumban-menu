package net.kriz.stcolumban.menu;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import net.kriz.stcolumban.menu.DailyMenu;
import net.kriz.stcolumban.menu.JsonMenuReader;

public class JsonMenuReaderTest {
	private JsonMenuReader fixture;
	
	@Before
	public void SetUp() {
		fixture = new JsonMenuReader("https://s3.amazonaws.com/lambda-function-bucket-us-east-1-1486176755721/SaintColumban/menu.json");
	}

	@Test
	public void testGetMenuForDate() {
		Calendar c = Calendar.getInstance();
		c.set(2017, 1, 14);
		DailyMenu menu = fixture.getMenuForDate(c.getTime());
		System.out.println(menu);
	}

}
