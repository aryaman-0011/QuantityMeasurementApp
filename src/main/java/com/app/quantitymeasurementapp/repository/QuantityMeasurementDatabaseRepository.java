package com.app.quantitymeasurementapp.repository;

import com.app.quantitymeasurementapp.entity.QuantityMeasurementEntity;
import com.app.quantitymeasurementapp.exception.DatabaseException;
import com.app.quantitymeasurementapp.util.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class QuantityMeasurementDatabaseRepository implements IQuantityMeasurementRepository {

	@Override
	public void save(String key, QuantityMeasurementEntity entity) {

		String entityInsert = """
				    INSERT INTO quantity_measurement_entity(
				        this_value,
				        this_unit,
				        this_measurement_type,
				        that_value,
				        that_unit,
				        that_measurement_type,
				        operation,
				        result_value,
				        result_unit,
				        result_measurement_type,
				        result_string,
				        is_error,
				        error_message
				    )
				    VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)
				""";

		String historyInsert = """
				    INSERT INTO quantity_measurement_history(
				        entity_id,
				        operation_count
				    )
				    VALUES (?,1)
				""";

		Connection conn = null;

		try {

			conn = ConnectionPool.getConnection();

			PreparedStatement stmt = conn.prepareStatement(entityInsert, PreparedStatement.RETURN_GENERATED_KEYS);

			stmt.setDouble(1, entity.getThisValue());
			stmt.setString(2, entity.getThisUnit());
			stmt.setString(3, entity.getThisMeasurementType());

			stmt.setObject(4, entity.getThatValue());
			stmt.setString(5, entity.getThatUnit());
			stmt.setString(6, entity.getThatMeasurementType());

			stmt.setString(7, entity.getOperation());

			stmt.setObject(8, entity.getResultValue());
			stmt.setString(9, entity.getResultUnit());
			stmt.setString(10, entity.getResultMeasurementType());

			stmt.setString(11, entity.getResultString());

			stmt.setBoolean(12, entity.isError());
			stmt.setString(13, entity.getErrorMessage());

			stmt.executeUpdate();

			ResultSet generatedKeys = stmt.getGeneratedKeys();

			long entityId = -1;

			if (generatedKeys.next()) {
				entityId = generatedKeys.getLong(1);
			}

			PreparedStatement historyStmt = conn.prepareStatement(historyInsert);

			historyStmt.setLong(1, entityId);

			historyStmt.executeUpdate();

		} catch (Exception e) {

			throw DatabaseException.queryFailed("Insert measurement", e);

		} finally {

			ConnectionPool.release(conn);
		}
	}

	@Override
	public QuantityMeasurementEntity find(String key) {
		return null;
	}
}
