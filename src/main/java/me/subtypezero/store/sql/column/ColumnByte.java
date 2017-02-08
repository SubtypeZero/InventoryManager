package me.subtypezero.store.sql.column;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ColumnByte extends Column<Byte> {

	public ColumnByte(String name) {
		super(name);
		value = (byte) 0;
	}

	public ColumnByte(String name, Byte value) {
		super(name, value);
	}

	@Override
	public String getCreateString() {
		return name + " TINYINT";
	}

	@Override
	public Byte getValue(ResultSet resultSet) throws SQLException {
		return resultSet.getByte(name);
	}

	@Override
	public void setValue(PreparedStatement preparedStatement, int columnNumber) throws SQLException {
		preparedStatement.setLong(columnNumber, value);
	}

	@Override
	public ColumnByte clone() {
		return new ColumnByte(name, value);
	}
}
