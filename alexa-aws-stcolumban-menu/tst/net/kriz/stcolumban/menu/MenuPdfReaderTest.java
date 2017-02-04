package net.kriz.stcolumban.menu;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import net.kriz.stcolumban.menu.DailyMenu;
import net.kriz.stcolumban.menu.PdfMenuReader;


public class MenuPdfReaderTest {
	private PdfMenuReader fixture;
	
	@Before
	public void SetUp() {
		fixture = new PdfMenuReader();
	}

	@Test
	public void testGetMenuForDate() {
		DailyMenu result = fixture.getMenuForDate(new Date());
		assertNotNull("Result should not be null", result);
	}

}
