package me.subtypezero.store.item;

import me.subtypezero.store.util.Logger;

import java.util.HashMap;

public class Category {
	private HashMap<Item, Integer> items;
	private final String name;

	/**
	 * @param name the category name
	 */
	public Category(String name) {
		this(name, new HashMap<Item, Integer>());
	}

	/**
	 * @param name the category name
	 * @param items the items to place in the category
	 */
	public Category(String name, HashMap<Item, Integer> items) {
		this.items = items;
		this.name = name;
	}

	/**
	 * Add a new item to the category, the item amount will be set to zero.
	 * @param item the item to add
	 * @return
	 */
	public boolean addItem(Item item) {
		return addItem(item, 0);
	}

	/**
	 * Add an item to the category.
	 * @param item the item to add
	 * @param count the amount to add
	 * @return the status of the operation, false if there was a problem
	 */
	public boolean addItem(Item item, int count) {
		if (count < 0) {
			Logger.logError(this.getName(), "add item", "count cannot be negative");
			return false;
		}
		if (itemExists(item)) {
			Logger.logError(this.getName(), "add item", "the item already exists");
			return false;
		}
		items.put(item, count);
		return true;
	}

	/**
	 * Remove an item from the category.
	 * @param item the item to remove
	 * @return the status of the operation, false if there was a problem
	 */
	public boolean removeItem(Item item) {
		if (itemExists(item)) {
			items.remove(item);
			return true;
		}
		Logger.logError(this.getName(), "remove item", "the item does not exist");
		return false;
	}

	/**
	 * Check if an item exists in the category.
	 * @param item the item to check
	 * @return true if the item exists
	 */
	public boolean itemExists(Item item) {
		return items.containsKey(item);
	}

	/**
	 * Increase the count of an item in the category by one.
	 * @param item the item to add
	 * @return the status of the operation, false if there was a problem
	 */
	public boolean increaseCount(Item item) {
		return increaseCount(item, 1);
	}

	/**
	 * Increase the count of an item in the category.
	 * @param item the item to add
	 * @param amount the number to items to add
	 * @return the status of the operation, false if there was a problem
	 */
	public boolean increaseCount(Item item, int amount) {
		if (itemExists(item)) {
			items.put(item, items.get(item) + amount);
			return true;
		}
		Logger.logError(this.getName(), "increase count", "the item does not exist");
		return false;
	}

	/**
	 * Decrease the count of an item in the category by one.
	 * @param item the item to remove
	 * @return the status of the operation, false if there was a problem
	 */
	public boolean decreaseCount(Item item) {
		return decreaseCount(item, 1);
	}

	/**
	 * Decrease the count of an item in the category.
	 * @param item the item to remove
	 * @param amount the number of items to remove
	 * @return the status of the operation, false if there was a problem
	 */
	public boolean decreaseCount(Item item, int amount) {
		if (itemExists(item)) {
			int stock = this.items.get(item);

			if (stock - amount >= 0) {
				this.items.put(item, stock - amount);
				return true;
			}
			Logger.logError(this.getName(), "decrease count", "not enough items in stock");
			return false;
		}
		Logger.logError(this.getName(), "decrease count", "the item does not exist");
		return false;
	}

	/**
	 * Get the count of how many items are in the category.
	 * @param item the item to check
	 * @return the number of items, -1 if the item does not exist
	 */
	public int getCount(Item item) {
		if (itemExists(item)) {
			return items.get(item);
		}
		return -1;
	}

	/**
	 * Set the count of an item in the category.
	 * @param item the item to set
	 * @param count the number of items
	 * @return the status of the operation, false if there was a problem
	 */
	public boolean setCount(Item item, int count) {
		if (itemExists(item)) {
			if (count < 0) {
				Logger.logError(this.getName(), "set count", "count cannot be negative");
				return false;
			}
			items.put(item, count);
			return true;
		}
		Logger.logError(this.getName(), "set count", "the item does not exist");
		return false;
	}

	/**
	 * @return a {@link HashMap} of all items in the category
	 */
	public HashMap<Item, Integer> getItems() {
		return items;
	}

	/**
	 * Set the {@link HashMap} of items the category.
	 * @param items the items to set
	 */
	public void setItems(HashMap<Item, Integer> items) {
		this.items = items;
	}

	/**
	 * @return the name of the category
	 */
	public String getName() {
		return name;
	}
}
