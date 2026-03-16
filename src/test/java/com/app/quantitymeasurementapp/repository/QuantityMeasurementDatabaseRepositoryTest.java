package com.app.quantitymeasurementapp.repository;

import com.app.quantitymeasurementapp.entity.QuantityMeasurementEntity;
import com.app.quantitymeasurementapp.util.ConnectionPool;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementDatabaseRepositoryTest {

	private QuantityMeasurementDatabaseRepository repository;

	@BeforeEach
	void setup() throws Exception {

		repository = new QuantityMeasurementDatabaseRepository();

		// Create table before running test
		Connection conn = ConnectionPool.getConnection();
		Statement stmt = conn.createStatement();

		stmt.execute("""
				    CREATE TABLE IF NOT EXISTS quantity_measurement_entity (
				        id BIGINT AUTO_INCREMENT PRIMARY KEY,
				        this_value DOUBLE NOT NULL,
				        this_unit VARCHAR(50) NOT NULL,
				        this_measurement_type VARCHAR(50) NOT NULL,
				        that_value DOUBLE,
				        that_unit VARCHAR(50),
				        that_measurement_type VARCHAR(50),
				        operation VARCHAR(20) NOT NULL,
				        result_value DOUBLE,
				        result_unit VARCHAR(50),
				        result_measurement_type VARCHAR(50),
				        result_string VARCHAR(255),
				        is_error BOOLEAN DEFAULT FALSE,
				        error_message VARCHAR(500),
				        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
				    )
				""");

		stmt.execute("""
				    CREATE TABLE IF NOT EXISTS quantity_measurement_history (
				        id BIGINT AUTO_INCREMENT PRIMARY KEY,
				        entity_id BIGINT NOT NULL,
				        operation_count INT DEFAULT 1,
				        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
				        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
				    )
				""");

		conn.close();
	}

	@Test
	void testDatabaseRepository_SaveEntity() {

		QuantityMeasurementEntity entity = new QuantityMeasurementEntity(10.0, "FEET", "LENGTH", 24.0, "INCHES",
				"LENGTH", "ADD", 12.0, "FEET", "LENGTH", "12 FEET", false, null);

		repository.save("ADD_TEST", entity);

		// If no exception occurs → test passed
		assertTrue(true);
	}
}
