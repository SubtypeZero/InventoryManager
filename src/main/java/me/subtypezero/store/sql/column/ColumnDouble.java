package me.subtypezero.store.sql.column;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ColumnDouble extends Column<Double> {

	public ColumnDouble(String name) {
		super(name);
		value = 0.0;
	}

	public ColumnDouble(String name, Double value) {
		super(name, value);
	}

	@Override
	public String getCreateString() {
		return name + " DOUBLE";
	}

	@Override
	public Double getValue(ResultSet resultSet) throws SQLException {
		return resultSet.getDouble(name);
	}

	@Override
	public void setValue(PreparedStatement preparedStatement, int columnNumber) throws SQLException {
		preparedStatement.setDouble(columnNumber, value);
	}

	@Override
	public ColumnDouble clone() {
		return new ColumnDouble(name, value);
	}
}
