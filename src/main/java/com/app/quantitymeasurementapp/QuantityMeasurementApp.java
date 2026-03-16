package com.app.quantitymeasurementapp;

import com.app.quantitymeasurementapp.controller.QuantityMeasurementController;
import com.app.quantitymeasurementapp.entity.QuantityDTO;
import com.app.quantitymeasurementapp.repository.*;
import com.app.quantitymeasurementapp.service.*;
import com.app.quantitymeasurementapp.util.ApplicationConfig;
import com.app.quantitymeasurementapp.util.ConnectionPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuantityMeasurementApp {

	private static final Logger logger = LoggerFactory.getLogger(QuantityMeasurementApp.class);

	public static void main(String[] args) throws Exception {

		logger.info("Starting Quantity Measurement Application...");

		// Check tables in database
		Connection conn = ConnectionPool.getConnection();
		Statement stmt = conn.createStatement();

		// create table if it does not exist
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
				    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
				    FOREIGN KEY (entity_id)
				    REFERENCES quantity_measurement_entity(id)
				)
				""");

		// Show Tables
		ResultSet rs = stmt.executeQuery("SHOW TABLES");

		logger.info("Tables in Database:");
		while (rs.next()) {
			logger.info(rs.getString(1));
		}

		ConnectionPool.release(conn);

		// Start H2 Console
		Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082").start();
		logger.info("H2 Console started at http://localhost:8082");

		// Repository selection
		IQuantityMeasurementRepository repository;

		String repoType = ApplicationConfig.getProperty("repository.type");

		if ("database".equalsIgnoreCase(repoType)) {
			logger.info("Using Database Repository");
			repository = new QuantityMeasurementDatabaseRepository();
		} else {
			logger.info("Using Cache Repository");
			repository = new QuantityMeasurementCacheRepository();
		}

		IQuantityMeasurementService service = new QuantityMeasurementServiceImpl(repository);
		QuantityMeasurementController controller = new QuantityMeasurementController(service);

		// LENGTH ADDITION
		QuantityDTO length1 = new QuantityDTO(10, "FEET", "LENGTH");
		QuantityDTO length2 = new QuantityDTO(24, "INCHES", "LENGTH");

		logger.info("Length Addition:");
		logger.info(controller.performAddition(length1, length2).toString());

		// LENGTH SUBTRACTION
		logger.info("Length Subtraction:");
		logger.info(controller.performSubtraction(length1, length2).toString());

		// WEIGHT ADDITION
		QuantityDTO weight1 = new QuantityDTO(2, "KILOGRAM", "WEIGHT");
		QuantityDTO weight2 = new QuantityDTO(500, "GRAM", "WEIGHT");

		logger.info("Weight Addition:");
		logger.info(controller.performAddition(weight1, weight2).toString());

		// WEIGHT SUBTRACTION
		logger.info("Weight Subtraction:");
		logger.info(controller.performSubtraction(weight1, weight2).toString());

		// VOLUME CONVERSION
		QuantityDTO volume1 = new QuantityDTO(1, "GALLON", "VOLUME");

		logger.info("Volume Conversion (Gallon → Litre):");
		logger.info(controller.convertUnit(volume1, "LITRE").toString());

		// VOLUME DIVISION
		QuantityDTO volume2 = new QuantityDTO(5, "LITRE", "VOLUME");
		QuantityDTO volume3 = new QuantityDTO(500, "MILLILITRE", "VOLUME");

		logger.info("Volume Division:");
		logger.info(String.valueOf(controller.performDivision(volume2, volume3)));

		// TEMPERATURE CONVERSION
		QuantityDTO temp1 = new QuantityDTO(100, "CELSIUS", "TEMPERATURE");

		logger.info("Temperature Conversion (Celsius → Fahrenheit):");
		logger.info(controller.convertUnit(temp1, "FAHRENHEIT").toString());

		// TEMPERATURE COMPARISON
		QuantityDTO temp2 = new QuantityDTO(212, "FAHRENHEIT", "TEMPERATURE");

		logger.info("Temperature Comparison:");
		logger.info(String.valueOf(controller.performComparison(temp1, temp2)));

		logger.info("Application execution completed.");
	}
}
