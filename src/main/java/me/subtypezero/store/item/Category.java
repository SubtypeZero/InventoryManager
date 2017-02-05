package me.subtypezero.store.item;

import java.util.HashMap;

public class Category {
	private HashMap<Item, Integer> stock;
	private String title;

	/**
	 * @param title the category title
	 */
	public Category(String title) {
		this(title, new HashMap<Item, Integer>());
	}

	/**
	 * @param title the category title
	 * @param stock the stock to place in the category
	 */
	public Category(String title, HashMap<Item, Integer> stock) {
		this.stock = stock;
		this.title = title;
	}

	/**
	 * Add a new item to the category.
	 * @param item the item to add
	 * @return
	 */
	public boolean addItem(Item item) {
		return addItem(item, 1);
	}

	/**
	 * Add a new item to the category.
	 * @param item the item to add
	 * @param count the amount to add
	 * @return the status of the operation, false if there was a problem
	 */
	public boolean addItem(Item item, int count) {
		if (itemExists(item)) {
			logError("add item", "the item already exists");
			return false;
		}
		stock.put(item, count);
		return true;
	}

	/**
	 * Remove an item from the category.
	 * @param item the item to remove
	 * @return the status of the operation, false if there was a problem
	 */
	public boolean removeItem(Item item) {
		if (itemExists(item)) {
			stock.remove(item);
			return true;
		}
		logError("remove item", "the item does not exist");
		return false;
	}

	/**
	 * Check if an item exists in the category.
	 * @param item the item to check
	 * @return true if the item exists
	 */
	public boolean itemExists(Item item) {
		return stock.containsKey(item);
	}

	/**
	 * Add one more of an item to stock.
	 * @param item the item to add
	 * @return the status of the operation, false if there was a problem
	 */
	public boolean increaseStock(Item item) {
		return increaseStock(item, 1);
	}

	/**
	 * Add more of an item to stock.
	 * @param item the item to add
	 * @param count the number to add
	 * @return the status of the operation, false if there was a problem
	 */
	public boolean increaseStock(Item item, int count) {
		if (itemExists(item)) {
			stock.put(item, stock.get(item) + count);
			return true;
		}
		logError("increase item", "the item does not exist");
		return false;
	}

	/**
	 * Remove one item from stock.
	 * @param item the item to remove
	 * @return the status of the operation, false if there was a problem
	 */
	public boolean decreaseStock(Item item) {
		return decreaseStock(item, 1);
	}

	/**
	 * Remove items from stock.
	 * @param item the item to remove
	 * @param count the number of items to remove
	 * @return the status of the operation, false if there was a problem
	 */
	public boolean decreaseStock(Item item, int count) {
		if (itemExists(item)) {
			int stock = this.stock.get(item);

			if (stock - count >= 0) {
				this.stock.put(item, stock - count);
				return true;
			}
			logError("decrease item", "not enough in stock");
			return false;
		}
		logError("decrease item", "the item does not exist");
		return false;
	}

	/**
	 * Check how many of an item are in stock.
	 * @param item the item to check
	 * @return the number of items in stock, -1 if the item does not exist
	 */
	public int getStock(Item item) {
		if (itemExists(item)) {
			return stock.get(item);
		}
		return -1;
	}

	/**
	 * Set the amount of items in stock.
	 * @param item the item to set
	 * @param count the number of items
	 * @return the status of the operation, false if there was a problem
	 */
	public boolean setStock(Item item,  int count) {
		if (itemExists(item)) {
			if (count < 0) {
				logError("set stock", "stock cannot be negative");
				return false;
			}
			stock.put(item, count);
			return true;
		}
		logError("set stock", "the item does not exist");
		return false;
	}

	/**
	 * @return a {@link HashMap} of all stock in the category
	 */
	public HashMap<Item, Integer> getStock() {
		return stock;
	}

	/**
	 * Set the category stock.
	 * @param stock the stock to set
	 */
	public void setStock(HashMap<Item, Integer> stock) {
		this.stock = stock;
	}

	/**
	 * Log an error in a pre-determined format.
	 * @param action the action that was attempted
	 * @param reason the reason the action failed
	 */
	private void logError(String action, String reason) {
		System.out.println(String.format("[ERROR] %s tried to %s and failed, %s.", this.getTitle(), action, reason));
	}

	/**
	 * @return the title of the category
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Set the title of the category.
	 * @param title the title to set
	 */
	public void setName(String title) {
		this.title = title;
	}
}
