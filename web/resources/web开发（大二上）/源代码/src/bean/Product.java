package bean;

public class Product {
	private int id;
	private String name;
	private String origin;
	private double price;
	private int inventory;
	private int canBuy;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getInventory() {
		return inventory;
	}

	public void setInventory(int inventory) {
		this.inventory = inventory;
	}
	
	public int getCanBuy() {
		return canBuy;
	}

	public void setCanBuy(int canBuy) {
		this.canBuy = canBuy;
	}
}
