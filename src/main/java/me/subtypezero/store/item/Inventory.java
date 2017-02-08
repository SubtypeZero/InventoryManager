package me.subtypezero.store.item;

import java.util.ArrayList;

public class Inventory {
	private ArrayList<Category> categories;
	private final String title;

	/**
	 * @param title
	 */
	public Inventory(String title) {
		this(title, new ArrayList<Category>());
	}

	/**
	 * @param title
	 * @param categories
	 */
	public Inventory(String title, ArrayList<Category> categories) {
		this.title = title;
		this.categories = categories;
	}

	/**
	 * Add a category to the inventory.
	 * @param category the category to add
	 * @return the status of the operation, false if there was a problem
	 */
	public boolean addCategory(Category category) {
		if (categoryExists(category)) {
			logError("add category", "the category already exists");
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
		logError("remove category", "the category does not exist");
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
	 * Log an error in a pre-determined format.
	 * @param action the action that was attempted
	 * @param reason the reason the action failed
	 */
	private void logError(String action, String reason) {
		System.out.println(String.format("[ERROR] %s tried to %s and failed, %s.", this.getTitle(), action, reason));
	}

	/**
	 * @return the title of the inventory
	 */
	public String getTitle() {
		return title;
	}
}
