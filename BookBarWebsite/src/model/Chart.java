package model;

public class Chart {
	private Menu menu;
	private int qta;
	
	public Chart() {}
	public Chart(Menu m, int q) {
		this.menu = m;
		this.qta = q;
	}
	
	public Menu getMenu() {
		return menu;
	}
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	public int getQta() {
		return qta;
	}
	public void setQta(int qta) {
		this.qta = qta;
	}
}
