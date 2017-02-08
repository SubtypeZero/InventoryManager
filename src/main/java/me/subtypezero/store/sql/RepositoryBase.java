package me.subtypezero.store.sql;

import me.subtypezero.store.sql.column.Column;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class RepositoryBase {
	private DataSource database; // Connection pool

	/**
	 * @param database - the {@link DataSource} responsible for providing the connection pool to the repository.
	 */
	public RepositoryBase(DataSource database) {
		this.database = database;
		init();
	}

	protected abstract void init();

	/**
	 * @return the {@link DataSource} used by the repository for connection pooling.
	 */
	protected DataSource getDatabase() {
		return database;
	}

	/**
	 * Note: {@link Connection}s must be closed after use so they can be returned to the pool!
	 *
	 * @return a newly fetched {@link Connection} from the connection pool if a connection can be made, return null otherwise.
	 * @see Connection#close()
	 */
	protected Connection getConnection() {
		try {
			return database.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Execute an update against the repository.
	 *
	 * @param query   the concatenated query to execute in string form
	 * @param columns the column data values used for insertion into the query
	 * @return the number of rows affected by this query in the repository
	 */
	protected int executeUpdate(String query, Column<?>... columns) {
		int affectedRows = 0;

		try (
				Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)
		) {
			for (int i = 0; i < columns.length; i++) {
				columns[i].setValue(preparedStatement, i + 1);
			}
			affectedRows = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return affectedRows;
	}

	/**
	 * Execute a query against the repository.
	 *
	 * @param query    the concatenated query to execute in string form
	 * @param callable the {@link ResultSetCallable} used to iterate over the result set
	 * @param columns  the column data values used for insertion into the query
	 */
	protected void executeQuery(String query, ResultSetCallable callable, Column<?>... columns) {
		try (
				Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(query)
		) {
			for (int i = 0; i < columns.length; i++) {
				columns[i].setValue(statement, i + 1);
			}

			ResultSet resultSet = statement.executeQuery();
			if (callable != null) {
				callable.processResultSet(resultSet);
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
	}
}
