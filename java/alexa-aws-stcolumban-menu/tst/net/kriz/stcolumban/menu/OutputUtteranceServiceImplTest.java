package net.kriz.stcolumban.menu;

import java.util.ArrayList;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import net.kriz.stcolumban.menu.DailyMenu;

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

		MenuItems entrees = new MenuItems();
		entrees.setItems(new ArrayList<MenuItem>());
		entrees.getItems().add(new MenuItem());
		entrees.getItems().get(0).setDescription("Chicken Tenders");
		entrees.getItems().add(new MenuItem());
		entrees.getItems().get(1).setDescription("Hot Dogs");
		
		MenuItems veggies = new MenuItems();
		veggies.setItems(new ArrayList<MenuItem>());
		veggies.getItems().add(new MenuItem());
		veggies.getItems().get(0).setDescription("Broccoli");

		MenuItems treats = new MenuItems();
		treats.setItems(new ArrayList<MenuItem>());
		treats.getItems().add(new MenuItem());
		treats.getItems().get(0).setDescription("Blue Jello");

		DailyMenu menu = new DailyMenu();
		menu.setEntreeItem(entrees);
		menu.setVeggieItem(veggies);
		menu.setTreatItem(treats);
		String output = fixture.generateDailyMenuOutput(c.getTime(), menu);
		System.out.println(output);
	}

}
