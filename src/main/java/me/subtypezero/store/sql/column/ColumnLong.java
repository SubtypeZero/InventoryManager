package me.subtypezero.store.sql.column;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ColumnLong extends Column<Long> {

	public ColumnLong(String name) {
		super(name);
		value = 0L;
	}

	public ColumnLong(String name, Long value) {
		super(name, value);
	}

	@Override
	public String getCreateString() {
		return name + " LONG";
	}

	@Override
	public Long getValue(ResultSet resultSet) throws SQLException {
		return resultSet.getLong(name);
	}

	@Override
	public void setValue(PreparedStatement preparedStatement, int columnNumber) throws SQLException {
		preparedStatement.setLong(columnNumber, value);
	}

	@Override
	public ColumnLong clone() {
		return new ColumnLong(name, value);
	}
}
