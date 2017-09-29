package net.kriz.stcolumban.menu;

import static org.junit.Assert.*;

import org.joda.time.DateTimeZone;
import org.junit.Before;
import org.junit.Test;

public class JodaTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		for (String z : DateTimeZone.getAvailableIDs()) {
			System.out.println(z);
		}
	}

}
