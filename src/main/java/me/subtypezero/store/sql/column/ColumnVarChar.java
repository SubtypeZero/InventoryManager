package me.subtypezero.store.sql.column;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ColumnVarChar extends Column<String> {
	public int length = 25;

	public ColumnVarChar(String name, int length) {
		this(name, length, "");
	}

	public ColumnVarChar(String name, int length, String value) {
		super(name);

		this.length = length;
		this.value = value;
	}

	public String getCreateString() {
		return name + " VARCHAR(" + length + ")";
	}

	@Override
	public String getValue(ResultSet resultSet) throws SQLException {
		return resultSet.getString(name);
	}

	@Override
	public void setValue(PreparedStatement preparedStatement, int columnNumber) throws SQLException {
		preparedStatement.setString(columnNumber, value);
	}

	@Override
	public ColumnVarChar clone() {
		return new ColumnVarChar(name, length, value);
	}
}
