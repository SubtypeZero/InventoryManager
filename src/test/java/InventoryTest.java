import me.subtypezero.store.item.Category;
import me.subtypezero.store.item.Inventory;
import me.subtypezero.store.item.Item;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;

public class InventoryTest {

	@Test
	public void testInventory() {
		Item toaster = new Item("HB-22623", "Hamilton Beach Toaster, Red", 19.99);
		Item ninjaBar = new Item("NINJA-CB", "Ninja Coffee Bar XL", 159.99);
		Item kitchenAid = new Item("KSM150PSER", "KitchenAid Mixer", 309.99);

		Item folgers = new Item("FGRS-CR", "Folgers Classic Roast", 5.93);
		Item deathWish = new Item("DWGC-CO16", "Death Wish Ground Coffee", 18.99);
		Item starbucks = new Item("SB-FR40", "Starbucks French Roast", 24.45);

		// Verify that the Item class is working properly
		folgers.setName("FLGRS-CR");
		assertEquals("FLGRS-CR", folgers.getName());
		toaster.setDesc("Hamilton Beach Toaster");
		assertEquals("Hamilton Beach Toaster", toaster.getDesc());
		starbucks.setPrice(-1.0);
		assertEquals(24.45, starbucks.getPrice(), 0);


		Category appliances = new Category("Stuff");
		appliances.setTitle("Appliances");
		assertEquals("Appliances", appliances.getTitle());

		HashMap<Item, Integer> stock = new HashMap<>();
		stock.put(kitchenAid, 1);
		appliances.setStock(stock);

		HashMap<Item, Integer> stock1 = appliances.getStock();
		assertEquals(1, stock1.get(kitchenAid), 0);
		appliances.setStock(new HashMap<Item, Integer>());

		assertEquals(true, appliances.addItem(toaster));
		assertEquals(true, appliances.addItem(ninjaBar, 5));
		assertEquals(false, appliances.addItem(ninjaBar, 20));
		assertEquals(5, appliances.getStock(ninjaBar));

		assertEquals(-1, appliances.getStock(kitchenAid));
		assertEquals(false, appliances.decreaseStock(kitchenAid));

		assertEquals(false, appliances.setStock(kitchenAid, 5));

		// Pretend that all of the ninja coffee bars have just been sold
		assertEquals(true, appliances.decreaseStock(ninjaBar, 5));

		// Verify that the Category class is working properly
		appliances.increaseStock(toaster, 10);
		assertEquals(11, appliances.getStock(toaster));
		assertEquals(false, appliances.increaseStock(kitchenAid));

		assertEquals(false, appliances.decreaseStock(ninjaBar)); // This should cause an error to be logged
		assertEquals(true, appliances.increaseStock(ninjaBar));
		assertEquals(1, appliances.getStock(ninjaBar));


		Category coffee = new Category("Coffee");
		coffee.addItem(folgers,8);
		coffee.addItem(deathWish,6);
		coffee.addItem(starbucks, 15);

		// Pretend a shipment of folgers coffee just arrived
		assertEquals(false, coffee.setStock(deathWish, -5));
		assertEquals(6, coffee.getStock(deathWish));
		assertEquals(true, coffee.setStock(folgers, 30));

		// Remove an item from the store
		assertEquals(true, coffee.removeItem(starbucks));
		assertEquals(false, coffee.removeItem(starbucks));
		assertEquals(false, coffee.itemExists(starbucks));

		// Add and remove categories
		Inventory store = new Inventory("Save-A-Bunch");
		store.setTitle("Save-A-Lot");
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
