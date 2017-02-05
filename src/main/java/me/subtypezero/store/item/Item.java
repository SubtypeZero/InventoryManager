package me.subtypezero.store.item;

public class Item {
	private String name;
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
		this.price = price;
	}

	/**
	 * @return the name of the item
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the name of the item.
	 * @param name the name of the item
	 */
	public void setName(String name) {
		this.name = name;
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
	 * Set the price of an item
	 * @param price the price
	 */
	public void setPrice(double price) {
		this.price = price;
	}
}
