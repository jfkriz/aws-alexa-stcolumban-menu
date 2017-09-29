package net.kriz.stcolumban.menu;

public class DailyMenu {
	private String entree;
	private String veggie;
	private String treat;

	private MenuItems entreeItem;
	private MenuItems veggieItem;
	private MenuItems treatItem;

	public String getEntree() {
		return entree;
	}

	public void setEntree(String entree) {
		this.entree = entree;
	}

	public String getVeggie() {
		return veggie;
	}

	public void setVeggie(String veggie) {
		this.veggie = veggie;
	}

	public String getTreat() {
		return treat;
	}

	public void setTreat(String treat) {
		this.treat = treat;
	}

	public MenuItems getEntreeItem() {
		return entreeItem;
	}

	public void setEntreeItem(MenuItems entreeItem) {
		this.entreeItem = entreeItem;
	}

	public MenuItems getVeggieItem() {
		return veggieItem;
	}

	public void setVeggieItem(MenuItems veggieItem) {
		this.veggieItem = veggieItem;
	}

	public MenuItems getTreatItem() {
		return treatItem;
	}

	public void setTreatItem(MenuItems treatItem) {
		this.treatItem = treatItem;
	}

	public boolean hasTreatItem() {
		return treatItem != null;
	}

	@Override
	public String toString() {
		return "DailyMenu [entree=" + entree + ", veggie=" + veggie + ", treat=" + treat + ", entreeItem=" + entreeItem
				+ ", veggieItem=" + veggieItem + ", treatItem=" + treatItem + "]";
	}
}
