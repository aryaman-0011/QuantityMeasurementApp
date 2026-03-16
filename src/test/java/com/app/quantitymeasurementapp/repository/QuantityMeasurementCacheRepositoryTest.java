package com.app.quantitymeasurementapp.repository;

import com.app.quantitymeasurementapp.entity.QuantityMeasurementEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementCacheRepositoryTest {

	private QuantityMeasurementCacheRepository repository;

	@BeforeEach
	void setup() {
		repository = new QuantityMeasurementCacheRepository();
	}

	@Test
	void testSaveAndRetrieveMeasurement() {

		QuantityMeasurementEntity entity = new QuantityMeasurementEntity();

		entity.setOperation("ADD");

		repository.save("key1", entity);

		QuantityMeasurementEntity result = repository.find("key1");

		assertNotNull(result);
		assertEquals("ADD", result.getOperation());
	}
}
