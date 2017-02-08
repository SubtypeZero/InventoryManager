package me.subtypezero.store.sql.column;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ColumnBoolean extends Column<Boolean> {

	public ColumnBoolean(String name) {
		super(name);
	}

	public ColumnBoolean(String name, boolean value) {
		super(name, value);
	}

	@Override
	public String getCreateString() {
		return name + " BOOLEAN";
	}

	@Override
	public Boolean getValue(ResultSet resultSet) throws SQLException {
		return resultSet.getBoolean(name);
	}

	@Override
	public void setValue(PreparedStatement preparedStatement, int columnNumber) throws SQLException {
		preparedStatement.setBoolean(columnNumber, value);
	}

	@Override
	public ColumnBoolean clone() {
		return new ColumnBoolean(name, value);
	}
}
