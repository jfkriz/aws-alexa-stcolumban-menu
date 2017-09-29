package net.kriz.stcolumban.menu;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.support.PropertyComparator;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonMenuReader implements MenuReaderInterface {
	private Menu menu;

	@SuppressWarnings("unchecked")
	private static final Comparator<MenuItem> MENU_ITEM_COMPARATOR = new PropertyComparator("id", false, true);

	public JsonMenuReader(String menuUrl) {
		InputStream menuStream = null;
		try {
			menuStream = new BufferedInputStream(new URL(menuUrl).openStream());
			menu = new ObjectMapper().readValue(menuStream, Menu.class);
			menu.getEntrees().sort(MENU_ITEM_COMPARATOR);
			menu.getTreats().sort(MENU_ITEM_COMPARATOR);
			menu.getVeggies().sort(MENU_ITEM_COMPARATOR);
			for (DailyMenu m : menu.getDates().values()) {
				m.setEntreeItem(findItem(m.getEntree(), menu.getEntrees()));
				m.setVeggieItem(findItem(m.getVeggie(), menu.getVeggies()));
				m.setTreatItem(findItem(m.getTreat(), menu.getTreats()));
			}
		} catch (IOException e) {
			menu = new Menu();
		} finally {
			try {
				if (menuStream != null) {
					menuStream.close();
				}
			} catch (Exception e) {
			}
		}
	}

	private static MenuItems findItem(String id, List<MenuItem> items) {
		if (StringUtils.isEmpty(id)) {
			return null;
		}

		List<MenuItem> found = new ArrayList<MenuItem>();
		String [] ids = id.split(";");
		for (String i : ids) {
			MenuItem find = new MenuItem();
			find.setId(i);
			
			int item = Collections.binarySearch(items, find, MENU_ITEM_COMPARATOR);
			if(item >= 0) {
				found.add(items.get(item));
			}
		}

		MenuItems m = new MenuItems();
		m.setItems(found);
		
		return found.size() > 0 ? m : null;
	}

	@Override
	public DailyMenu getMenuForDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("M/d/yyyy");
		return menu.getDates().get(sdf.format(date));
	}
}
