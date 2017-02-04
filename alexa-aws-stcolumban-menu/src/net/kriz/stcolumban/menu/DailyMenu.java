package net.kriz.stcolumban.menu;

public class DailyMenu {
	private String entree;
	private String veggie;
	private String treat;

	private MenuItem entreeItem;
	private MenuItem veggieItem;
	private MenuItem treatItem;

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

	public MenuItem getEntreeItem() {
		return entreeItem;
	}

	public void setEntreeItem(MenuItem entreeItem) {
		this.entreeItem = entreeItem;
	}

	public MenuItem getVeggieItem() {
		return veggieItem;
	}

	public void setVeggieItem(MenuItem veggieItem) {
		this.veggieItem = veggieItem;
	}

	public MenuItem getTreatItem() {
		return treatItem;
	}

	public void setTreatItem(MenuItem treatItem) {
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
