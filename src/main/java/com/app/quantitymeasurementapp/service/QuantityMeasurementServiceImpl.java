package com.app.quantitymeasurementapp.service;

import com.app.quantitymeasurementapp.entity.QuantityDTO;
import com.app.quantitymeasurementapp.entity.QuantityModel;
import com.app.quantitymeasurementapp.entity.QuantityMeasurementEntity;
import com.app.quantitymeasurementapp.exception.QuantityMeasurementException;
import com.app.quantitymeasurementapp.quantity.Quantity;
import com.app.quantitymeasurementapp.repository.IQuantityMeasurementRepository;
import com.app.quantitymeasurementapp.unit.*;

public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {

	private final IQuantityMeasurementRepository repository;

	public QuantityMeasurementServiceImpl(IQuantityMeasurementRepository repository) {
		this.repository = repository;
	}

	private QuantityModel convertDTOToModel(QuantityDTO dto) {
		return new QuantityModel(dto.getValue(), dto.getUnit(), dto.getMeasurementType());
	}

	private QuantityDTO convertModelToDTO(QuantityModel model) {
		return new QuantityDTO(model.getValue(), model.getUnit(), model.getMeasurementType());
	}

	private Quantity<? extends IMeasurable> convertModelToQuantity(QuantityModel model) {

		switch (model.getMeasurementType().toUpperCase()) {

		case "LENGTH":
			return new Quantity<>(model.getValue(), LengthUnit.valueOf(model.getUnit().toUpperCase()));

		case "WEIGHT":
			return new Quantity<>(model.getValue(), WeightUnit.valueOf(model.getUnit().toUpperCase()));

		case "VOLUME":
			return new Quantity<>(model.getValue(), VolumeUnit.valueOf(model.getUnit().toUpperCase()));

		case "TEMPERATURE":
			return new Quantity<>(model.getValue(), TemperatureUnit.valueOf(model.getUnit().toUpperCase()));

		default:
			throw new QuantityMeasurementException("Invalid Measurement Type");
		}
	}

	@Override
	public QuantityDTO add(QuantityDTO q1, QuantityDTO q2) {

		QuantityModel m1 = convertDTOToModel(q1);
		QuantityModel m2 = convertDTOToModel(q2);

		Quantity<? extends IMeasurable> quantity1 = convertModelToQuantity(m1);
		Quantity<? extends IMeasurable> quantity2 = convertModelToQuantity(m2);

		try {

			Quantity<?> result = ((Quantity<IMeasurable>) quantity1).add((Quantity<IMeasurable>) quantity2);

			QuantityModel resultModel = new QuantityModel(result.getValue(), result.getUnit().toString(),
					q1.getMeasurementType());

			repository.save("ADD",
					new QuantityMeasurementEntity(q1.getValue(), q1.getUnit(), q1.getMeasurementType(), q2.getValue(),
							q2.getUnit(), q2.getMeasurementType(), "ADD", result.getValue(),
							result.getUnit().toString(), q1.getMeasurementType(), result.toString(), false, null));

			return convertModelToDTO(resultModel);

		} catch (Exception e) {

			repository.save("ADD",
					new QuantityMeasurementEntity(q1.getValue(), q1.getUnit(), q1.getMeasurementType(), q2.getValue(),
							q2.getUnit(), q2.getMeasurementType(), "ADD", null, null, null, null, true,
							e.getMessage()));

			throw new QuantityMeasurementException(e.getMessage());
		}
	}

	@Override
	public QuantityDTO subtract(QuantityDTO q1, QuantityDTO q2) {

		QuantityModel m1 = convertDTOToModel(q1);
		QuantityModel m2 = convertDTOToModel(q2);

		Quantity<? extends IMeasurable> quantity1 = convertModelToQuantity(m1);
		Quantity<? extends IMeasurable> quantity2 = convertModelToQuantity(m2);

		Quantity<?> result = ((Quantity<IMeasurable>) quantity1).subtract((Quantity<IMeasurable>) quantity2);

		QuantityModel resultModel = new QuantityModel(result.getValue(), result.getUnit().toString(),
				q1.getMeasurementType());

		repository.save("SUBTRACT",
				new QuantityMeasurementEntity(q1.getValue(), q1.getUnit(), q1.getMeasurementType(), q2.getValue(),
						q2.getUnit(), q2.getMeasurementType(), "SUBTRACT", result.getValue(),
						result.getUnit().toString(), q1.getMeasurementType(), result.toString(), false, null));

		return convertModelToDTO(resultModel);
	}

	@Override
	public double divide(QuantityDTO q1, QuantityDTO q2) {

		QuantityModel m1 = convertDTOToModel(q1);
		QuantityModel m2 = convertDTOToModel(q2);

		Quantity<? extends IMeasurable> quantity1 = convertModelToQuantity(m1);
		Quantity<? extends IMeasurable> quantity2 = convertModelToQuantity(m2);

		double result = ((Quantity<IMeasurable>) quantity1).divide((Quantity<IMeasurable>) quantity2);

		repository.save("DIVIDE",
				new QuantityMeasurementEntity(q1.getValue(), q1.getUnit(), q1.getMeasurementType(), q2.getValue(),
						q2.getUnit(), q2.getMeasurementType(), "DIVIDE", result, "RATIO", null, String.valueOf(result),
						false, null));

		return result;
	}

	@Override
	public QuantityDTO convert(QuantityDTO q, String targetUnit) {

		QuantityModel model = convertDTOToModel(q);
		Quantity<? extends IMeasurable> quantity = convertModelToQuantity(model);

		IMeasurable target;

		switch (model.getMeasurementType().toUpperCase()) {

		case "LENGTH":
			target = LengthUnit.valueOf(targetUnit.toUpperCase());
			break;

		case "WEIGHT":
			target = WeightUnit.valueOf(targetUnit.toUpperCase());
			break;

		case "VOLUME":
			target = VolumeUnit.valueOf(targetUnit.toUpperCase());
			break;

		case "TEMPERATURE":
			target = TemperatureUnit.valueOf(targetUnit.toUpperCase());
			break;

		default:
			throw new QuantityMeasurementException("Invalid conversion type");
		}

		Quantity<?> converted = ((Quantity<IMeasurable>) quantity).convertTo(target);

		repository.save("CONVERT",
				new QuantityMeasurementEntity(q.getValue(), q.getUnit(), q.getMeasurementType(), null, null, null,
						"CONVERT", converted.getValue(), converted.getUnit().toString(), q.getMeasurementType(),
						converted.toString(), false, null));

		return new QuantityDTO(converted.getValue(), converted.getUnit().toString(), q.getMeasurementType());
	}

	@Override
	public boolean compare(QuantityDTO q1, QuantityDTO q2) {

		QuantityModel m1 = convertDTOToModel(q1);
		QuantityModel m2 = convertDTOToModel(q2);

		Quantity<? extends IMeasurable> quantity1 = convertModelToQuantity(m1);
		Quantity<? extends IMeasurable> quantity2 = convertModelToQuantity(m2);

		double base1 = quantity1.getUnit().convertToBaseUnit(quantity1.getValue());
		double base2 = quantity2.getUnit().convertToBaseUnit(quantity2.getValue());

		boolean result = Math.abs(base1 - base2) < 0.0001;

		repository.save("COMPARE",
				new QuantityMeasurementEntity(q1.getValue(), q1.getUnit(), q1.getMeasurementType(), q2.getValue(),
						q2.getUnit(), q2.getMeasurementType(), "COMPARE", null, null, null, String.valueOf(result),
						false, null));

		return result;
	}
}
