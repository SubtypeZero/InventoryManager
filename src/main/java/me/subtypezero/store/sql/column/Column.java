package me.subtypezero.store.sql.column;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Column<Type> implements Cloneable {
	public String name;
	public Type value;

	/**
	 * @param name of the column
	 */
	public Column(String name) {
		this.name = name;
	}

	/**
	 * @param name  the name of the column
	 * @param value the value of the column
	 */
	public Column(String name, Type value) {
		this.name = name;
		this.value = value;
	}

	/**
	 * @return the string used to create this column
	 */
	public abstract String getCreateString();

	/**
	 * Get the value of the column from the {@link ResultSet}
	 *
	 * @param resultSet the set of results
	 * @return the value of the column
	 * @throws SQLException if there is a problem retrieving the value
	 */
	public abstract Type getValue(ResultSet resultSet) throws SQLException;

	/**
	 * Set the value for the column in a {@link PreparedStatement}
	 *
	 * @param preparedStatement the statement to prepare
	 * @param columnNumber      the column number to set
	 * @throws SQLException
	 */
	public abstract void setValue(PreparedStatement preparedStatement, int columnNumber) throws SQLException;

	public abstract Column<Type> clone();
}
