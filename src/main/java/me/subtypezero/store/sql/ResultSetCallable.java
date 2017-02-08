package me.subtypezero.store.sql;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultSetCallable {
	void processResultSet(ResultSet resultSet) throws SQLException;
}