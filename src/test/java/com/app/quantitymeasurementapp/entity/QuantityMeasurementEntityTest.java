package com.app.quantitymeasurementapp.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementEntityTest {

	@Test
	void testQuantityEntity_SingleOperandConstruction() {

		QuantityMeasurementEntity entity = new QuantityMeasurementEntity(10.0, "FEET", "LENGTH", null, null, null,
				"CONVERT", 3.048, "METRE", "LENGTH", "3.048 METRE", false, null);

		assertEquals("CONVERT", entity.getOperation());
		assertEquals(3.048, entity.getResultValue());
	}

	@Test
	void testQuantityEntity_BinaryOperandConstruction() {

		QuantityMeasurementEntity entity = new QuantityMeasurementEntity(10.0, "FEET", "LENGTH", 24.0, "INCHES",
				"LENGTH", "ADD", 12.0, "FEET", "LENGTH", "12 FEET", false, null);

		assertEquals("ADD", entity.getOperation());
		assertEquals(12.0, entity.getResultValue());
	}

	@Test
	void testQuantityEntity_ErrorConstruction() {

		QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
		entity.setError(true);
		entity.setErrorMessage("Invalid operation");

		assertTrue(entity.isError());
		assertEquals("Invalid operation", entity.getErrorMessage());
	}

	@Test
	void testQuantityEntity_ToString_Success() {

		QuantityMeasurementEntity entity = new QuantityMeasurementEntity(10.0, "FEET", "LENGTH", 24.0, "INCHES",
				"LENGTH", "ADD", 12.0, "FEET", "LENGTH", "12 FEET", false, null);

		assertTrue(entity.toString().contains("12"));
	}

	@Test
	void testQuantityEntity_ToString_Error() {

		QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
		entity.setError(true);
		entity.setErrorMessage("Conversion failed");

		assertTrue(entity.toString().contains("Conversion failed"));
	}
}
