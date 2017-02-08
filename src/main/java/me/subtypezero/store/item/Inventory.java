package me.subtypezero.store.item;

import me.subtypezero.store.util.Logger;

import java.util.ArrayList;

public class Inventory {
	private ArrayList<Category> categories;
	private final String name;

	/**
	 * @param name
	 */
	public Inventory(String name) {
		this(name, new ArrayList<Category>());
	}

	/**
	 * @param name
	 * @param categories
	 */
	public Inventory(String name, ArrayList<Category> categories) {
		this.name = name;
		this.categories = categories;
	}

	/**
	 * Add a category to the inventory.
	 * @param category the category to add
	 * @return the status of the operation, false if there was a problem
	 */
	public boolean addCategory(Category category) {
		if (categoryExists(category)) {
			Logger.logError(this.getName(), "add category", "the category already exists");
			return false;
		}
		categories.add(category);
		return true;
	}

	/**
	 * Remove a category from the inventory.
	 * @param category the category to remove
	 * @return the status of the operation, false if there was a problem
	 */
	public boolean removeCategory(Category category) {
		if (categoryExists(category)) {
			categories.remove(category);
			return true;
		}
		Logger.logError(this.getName(), "remove category", "the category does not exist");
		return false;
	}

	/**
	 * Get a category by name from the inventory.
	 *
	 * @param name the name of the category to search for
	 * @return the category, null if the category was not found
	 */
	public Category getCategory(String name) {
		for (Category category : categories) {
			if (category.getName().equals(name)) {
				return category;
			}
		}
		return null;
	}

	/**
	 * Check if a category exists in the inventory.
	 * @param category the category to check
	 * @return true if the item exists
	 */
	public boolean categoryExists(Category category) {
		return categories.contains(category);
	}

	/**
	 * @return an {@link ArrayList} of all the categories in the inventory
	 */
	public ArrayList<Category> getCategories() {
		return categories;
	}

	/**
	 * Set the inventory categories.
	 * @param categories the categories to set
	 */
	public void setCategories(ArrayList<Category> categories) {
		this.categories = categories;
	}

	/**
	 * @return the name of the inventory
	 */
	public String getName() {
		return name;
	}
}
