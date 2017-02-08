package me.subtypezero.store.sql;

import javax.sql.DataSource;

public class InventoryRepository extends RepositoryBase {
	private String CREATE_CATEGORY_TABLE = "CREATE TABLE IF NOT EXISTS category (name VARCHAR(50) NOT NULL, PRIMARY KEY(name));";
	private String CREATE_ITEM_TABLE = "CREATE TABLE IF NOT EXISTS item (category VARCHAR(50), name VARCHAR(50), description VARCHAR(100), price DOUBLE PRECISION(9, 2), amount INT NOT NULL, PRIMARY KEY(name), FOREIGN KEY (category) REFERENCES category(name));";

	private String UPDATE_ITEM_CATEGORY = "UPDATE item SET category = ? WHERE name = ?;";
	private String UPDATE_ITEM_DESCRIPTION = "UPDATE item SET description = ? WHERE name = ?;";
	private String UPDATE_ITEM_PRICE = "UPDATE item SET price = ? WHERE name = ?;";
	private String UPDATE_ITEM_QUANTITY = "UPDATE item SET amount = ? WHERE name = ?;";

	private String INSERT_ITEM = "INSERT INTO item (category, name, description, price, amount) VALUES(?, ?, ?, ?, ?);";

	private String SELECT_CATEGORIES = "";
	private String INSERT_CATEGORY = "";

	/**
	 * @param database the {@link DataSource} to use for connections
	 */
	public InventoryRepository(DataSource database) {
		super(database);
		//this.inventory = inventory;
	}

	@Override
	protected void init() {

	}
}
