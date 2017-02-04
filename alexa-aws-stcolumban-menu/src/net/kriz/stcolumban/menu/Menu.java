package net.kriz.stcolumban.menu;

import java.util.List;
import java.util.Map;

public class Menu {
	private List<MenuItem> entrees;
	private List<MenuItem> veggies;
	private List<MenuItem> treats;
	private Map<String, DailyMenu> dates;

	public List<MenuItem> getEntrees() {
		return entrees;
	}

	public void setEntrees(List<MenuItem> entrees) {
		this.entrees = entrees;
	}

	public List<MenuItem> getVeggies() {
		return veggies;
	}

	public void setVeggies(List<MenuItem> veggies) {
		this.veggies = veggies;
	}

	public List<MenuItem> getTreats() {
		return treats;
	}

	public void setTreats(List<MenuItem> treats) {
		this.treats = treats;
	}

	public Map<String, DailyMenu> getDates() {
		return dates;
	}

	public void setDates(Map<String, DailyMenu> dates) {
		this.dates = dates;
	}

	@Override
	public String toString() {
		return "Menu [entrees=" + entrees + ", veggies=" + veggies + ", treats=" + treats + ", dates=" + dates + "]";
	}
}
