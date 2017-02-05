import me.subtypezero.store.item.Category;
import me.subtypezero.store.item.Inventory;
import me.subtypezero.store.item.Item;
import org.junit.Test;

import static org.junit.Assert.*;

public class InventoryTest {

	@Test
	public void testInventory() {
		Item toaster = new Item("HB-22623", "Hamilton Beach Toaster", 19.99);
		Item ninjaBar = new Item("NINJA-CB", "Ninja Coffee Bar XL", 159.99);
		Item kitchenAid = new Item("KSM150PSER", "KitchenAid Mixer", 309.99);

		Item folgers = new Item("FGRS-CR", "Folgers Classic Roast", 5.93);
		Item deathWish = new Item("DWGC-CO16", "Death Wish Ground Coffee", 18.99);
		Item starbucks = new Item("SB-FR40", "Starbucks French Roast", 24.45);

		assertEquals("FGRS-CR", folgers.getName());
		assertEquals("Hamilton Beach Toaster", toaster.getDesc());
		assertEquals(24.45, starbucks.getPrice(), 0);

		Category appliances = new Category("Appliances");
		assertEquals(true, appliances.addItem(toaster));
		assertEquals(true, appliances.addItem(ninjaBar, 5));
		assertEquals(false, appliances.addItem(ninjaBar, 20));
		assertEquals(5, appliances.getStock(ninjaBar));
		appliances.addItem(kitchenAid);
		System.out.println("There should be am error above this line about an item already existing. \n");


		// Pretend that all of the ninja coffee bars have just been sold
		assertEquals(true, appliances.decreaseStock(ninjaBar, 5));

		// Verify that the Category class is working properly
		appliances.increaseStock(toaster, 10);
		assertEquals(11, appliances.getStock(toaster));

		assertEquals(false, appliances.decreaseStock(ninjaBar)); // This should cause an error to be logged
		assertEquals(0, appliances.getStock(ninjaBar));
		System.out.println("There should be am error above this line about not having enough stock. \n");


		Category coffee = new Category("Coffee");
		coffee.addItem(folgers,8);
		coffee.addItem(deathWish,6);
		coffee.addItem(starbucks, 15);

		// Pretend a shipment of folgers coffee just arrived
		assertEquals(false, coffee.setStock(deathWish, -5));
		assertEquals(6, coffee.getStock(deathWish));
		assertEquals(true, coffee.setStock(folgers, 30));
		System.out.println("There should be am error above this line about stock being negative. \n");

		// Remove an item from the store
		assertEquals(true, coffee.removeItem(starbucks));
		assertEquals(false, coffee.itemExists(starbucks));

		// add category
		Inventory store = new Inventory("Save-A-Bunch");
		assertEquals(false, store.categoryExists(appliances));
		assertEquals(true, store.addCategory(appliances));
		assertEquals(false, store.addCategory(appliances));
		System.out.println("There should be am error above this line about a category already existing. \n");

		assertEquals(true, store.removeCategory(appliances));
		assertEquals(false, store.categoryExists(appliances));

		assertEquals(false, store.removeCategory(coffee));
		assertEquals(true, store.addCategory(coffee));
		assertEquals(true, store.categoryExists(coffee));
		System.out.println("There should be am error above this line about a category not existing.");
	}
}
