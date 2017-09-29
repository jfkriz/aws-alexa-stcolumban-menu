package net.kriz.stcolumban.menu;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.joestelmach.natty.DateGroup;
import com.joestelmach.natty.Parser;

public class TestDateParser {

	@Test
	public void test() {
		List<DateGroup> dates = new Parser().parse("next saturday");
		for (DateGroup g : dates) {
			System.out.println(g.getDates().get(0));
		}
	}

}
