package net.kriz.stcolumban.menu;

import java.util.List;

public class MenuItems {
	private List<MenuItem> items;

	public List<MenuItem> getItems() {
		return items;
	}

	public void setItems(List<MenuItem> items) {
		this.items = items;
	}
	
	public String getDescription() {
		if(items == null || items.size() <= 0) {
			return "";
		}
		
		StringBuilder sb = new StringBuilder(items.get(0).getDescription());
		for (int i = 1; i < items.size(); i++) {
			sb.append(" or ");
			sb.append(items.get(i).getDescription());
		}
		
		return sb.toString();
	}
}
