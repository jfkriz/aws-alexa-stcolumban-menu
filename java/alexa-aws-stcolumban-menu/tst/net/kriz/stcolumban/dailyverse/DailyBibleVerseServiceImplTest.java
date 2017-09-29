package net.kriz.stcolumban.dailyverse;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DailyBibleVerseServiceImplTest {
	private DailyBibleVerseServiceImpl fixture;

	@Before
	public void setUp() throws Exception {
		fixture = new DailyBibleVerseServiceImpl("https://www.biblegateway.com/votd/get/?format=json&version=NIV");
	}

	@Test
	public void testGetDailyBibleVerse() {
		String verse = fixture.getDailyBibleVerse();
		assertNotNull(verse);
		System.out.println(verse);
	}

}
