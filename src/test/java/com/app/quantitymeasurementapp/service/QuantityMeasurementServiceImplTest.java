package com.app.quantitymeasurementapp.service;

import com.app.quantitymeasurementapp.entity.QuantityDTO;
import com.app.quantitymeasurementapp.repository.QuantityMeasurementCacheRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementServiceImplTest {

	private QuantityMeasurementServiceImpl service;

	@BeforeEach
	void setup() {

		service = new QuantityMeasurementServiceImpl(new QuantityMeasurementCacheRepository());
	}

	@Test
	void testService_Add_Success() {

		QuantityDTO q1 = new QuantityDTO(10, "FEET", "LENGTH");
		QuantityDTO q2 = new QuantityDTO(24, "INCHES", "LENGTH");

		QuantityDTO result = service.add(q1, q2);

		assertEquals(12.0, result.getValue());
	}

	@Test
	void testService_Convert_Success() {

		QuantityDTO q = new QuantityDTO(1, "FEET", "LENGTH");

		QuantityDTO result = service.convert(q, "INCHES");

		assertEquals(12.0, result.getValue());
	}

	@Test
	void testService_Divide_Success() {

		QuantityDTO q1 = new QuantityDTO(10, "FEET", "LENGTH");
		QuantityDTO q2 = new QuantityDTO(2, "FEET", "LENGTH");

		double result = service.divide(q1, q2);

		assertEquals(5.0, result);
	}

	@Test
	void testService_CompareEquality_SameUnit_Success() {

		QuantityDTO q1 = new QuantityDTO(1, "FEET", "LENGTH");
		QuantityDTO q2 = new QuantityDTO(1, "FEET", "LENGTH");

		assertTrue(service.compare(q1, q2));
	}
}
