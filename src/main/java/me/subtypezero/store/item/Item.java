package me.subtypezero.store.item;

public class Item {
	private final String name;
	private String desc;
	private double price;

	/**
	 * @param name the name of the item
	 * @param desc the description of the item
	 * @param price the price of the item
	 */
	public Item(String name, String desc, double price) {
		this.name = name;
		this.desc = desc;
		setPrice(price);
	}

	/**
	 * @return the name of the item
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the description of the item
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * Set the description of the item.
	 * @param desc the description
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * @return the price of the item
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Set the price of an item, must be greater than zero.
	 * @param price the price
	 */
	public void setPrice(double price) {
		if (price < 0 || price > 9999999.99) {
			return;
		}
		this.price = price;
	}
}
