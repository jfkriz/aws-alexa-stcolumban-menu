package net.kriz.stcolumban.menu;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import net.kriz.stcolumban.menu.DailyMenu;
import net.kriz.stcolumban.menu.JsonMenuReader;

public class OutputUtteranceServiceImplTest {
	private OutputUtteranceServiceImpl fixture;
	
	@Before
	public void SetUp() {
		fixture = new OutputUtteranceServiceImpl("https://s3.amazonaws.com/lambda-function-bucket-us-east-1-1486176755721/SaintColumban/dailyMenuOutputUtterances.json");
	}

	@Test
	public void testGetMenuForDate() {
		Calendar c = Calendar.getInstance();
		c.set(2017, 1, 14);
		DailyMenu menu = new DailyMenu();
		menu.setEntreeItem(new MenuItem());
		menu.getEntreeItem().setDescription("Chicken Tenders");
		menu.setVeggieItem(new MenuItem());
		menu.getVeggieItem().setDescription("Broccoli");
		menu.setTreatItem(new MenuItem());
		menu.getTreatItem().setDescription("blue jello");
		String output = fixture.generateDailyMenuOutput(c.getTime(), menu);
		System.out.println(output);
	}

}
