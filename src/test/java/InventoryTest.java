import me.subtypezero.store.item.Category;
import me.subtypezero.store.item.Inventory;
import me.subtypezero.store.item.Item;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class InventoryTest {

	@Test
	public void testInventory() {
		Item toaster = new Item("HB-22623", "Hamilton Beach Toaster, Red", 19.99);
		Item ninjaBar = new Item("NINJA-CB", "Ninja Coffee Bar XL", 159.99);
		Item kitchenAid = new Item("KSM150PSER", "KitchenAid Mixer", 309.99);

		Item folgers = new Item("FLGRS-CR", "Folgers Classic Roast", 5.93);
		Item deathWish = new Item("DWGC-CO16", "Death Wish Ground Coffee", 18.99);
		Item starbucks = new Item("SB-FR40", "Starbucks French Roast", 24.45);

		// Verify that the Item class is working properly
		assertEquals("FLGRS-CR", folgers.getName());
		toaster.setDesc("Hamilton Beach Toaster");
		assertEquals("Hamilton Beach Toaster", toaster.getDesc());
		starbucks.setPrice(-1.0);
		assertEquals(24.45, starbucks.getPrice(), 0);


		Category appliances = new Category("Appliances");
		assertEquals("Appliances", appliances.getName());

		HashMap<Item, Integer> items = new HashMap<>();
		items.put(kitchenAid, 1);
		appliances.setItems(items);

		HashMap<Item, Integer> stock1 = appliances.getItems();
		assertEquals(1, stock1.get(kitchenAid), 0);
		appliances.setItems(new HashMap<Item, Integer>());

		assertEquals(true, appliances.addItem(toaster));
		assertEquals(true, appliances.addItem(ninjaBar, 5));
		assertEquals(false, appliances.addItem(ninjaBar, 20));
		assertEquals(5, appliances.getCount(ninjaBar));

		assertEquals(-1, appliances.getCount(kitchenAid));
		assertEquals(false, appliances.decreaseCount(kitchenAid));

		assertEquals(false, appliances.setCount(kitchenAid, 5));

		// Pretend that all of the ninja coffee bars have just been sold
		assertEquals(true, appliances.decreaseCount(ninjaBar, 5));

		// Verify that the Category class is working properly
		appliances.increaseCount(toaster, 10);
		assertEquals(10, appliances.getCount(toaster));
		assertEquals(false, appliances.increaseCount(kitchenAid));

		assertEquals(false, appliances.decreaseCount(ninjaBar)); // This should cause an error to be logged
		assertEquals(true, appliances.increaseCount(ninjaBar));
		assertEquals(1, appliances.getCount(ninjaBar));


		Category coffee = new Category("Coffee");
		coffee.addItem(folgers,8);
		coffee.addItem(deathWish,6);
		coffee.addItem(starbucks, 15);

		// Pretend a shipment of folgers coffee just arrived
		assertEquals(false, coffee.setCount(deathWish, -5));
		assertEquals(6, coffee.getCount(deathWish));
		assertEquals(true, coffee.setCount(folgers, 30));

		// Remove an item from the store
		assertEquals(true, coffee.removeItem(starbucks));
		assertEquals(false, coffee.removeItem(starbucks));
		assertEquals(false, coffee.itemExists(starbucks));

		// Add and remove categories
		Inventory store = new Inventory("Save-A-Lot");
		assertEquals("Save-A-Lot", store.getTitle());

		ArrayList<Category> categories = new ArrayList<>();
		categories.add(coffee);
		store.setCategories(categories);

		ArrayList<Category> categories1 = store.getCategories();
		assertEquals(true, categories1.contains(coffee));
		store.setCategories(new ArrayList<Category>());

		assertEquals(false, store.categoryExists(appliances));
		assertEquals(true, store.addCategory(appliances));
		assertEquals(false, store.addCategory(appliances));

		assertEquals(true, store.removeCategory(appliances));
		assertEquals(false, store.categoryExists(appliances));

		assertEquals(false, store.removeCategory(coffee));
		assertEquals(true, store.addCategory(coffee));
		assertEquals(true, store.categoryExists(coffee));
	}
}
