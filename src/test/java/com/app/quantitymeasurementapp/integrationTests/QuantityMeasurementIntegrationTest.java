package com.app.quantitymeasurementapp.integrationTests;

import com.app.quantitymeasurementapp.controller.QuantityMeasurementController;
import com.app.quantitymeasurementapp.entity.QuantityDTO;
import com.app.quantitymeasurementapp.repository.QuantityMeasurementDatabaseRepository;
import com.app.quantitymeasurementapp.service.QuantityMeasurementServiceImpl;
import com.app.quantitymeasurementapp.util.ConnectionPool;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Statement;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementIntegrationTest {

	private QuantityMeasurementController controller;

	@BeforeEach
	void setup() throws Exception {

		Connection conn = ConnectionPool.getConnection();
		Statement stmt = conn.createStatement();

		// Load schema file
		String schemaSql = new BufferedReader(
				new InputStreamReader(getClass().getClassLoader().getResourceAsStream("test-db-schema.sql"))).lines()
				.collect(Collectors.joining("\n"));

		stmt.execute(schemaSql);

		// Load test data
		String dataSql = new BufferedReader(
				new InputStreamReader(getClass().getClassLoader().getResourceAsStream("test-data.sql"))).lines()
				.collect(Collectors.joining("\n"));

		stmt.execute(dataSql);

		controller = new QuantityMeasurementController(
				new QuantityMeasurementServiceImpl(new QuantityMeasurementDatabaseRepository()));
	}

	@Test
	void testIntegration_EndToEnd_LengthAddition() {

		QuantityDTO q1 = new QuantityDTO(10, "FEET", "LENGTH");
		QuantityDTO q2 = new QuantityDTO(24, "INCHES", "LENGTH");

		QuantityDTO result = controller.performAddition(q1, q2);

		assertEquals(12.0, result.getValue());
	}
}
