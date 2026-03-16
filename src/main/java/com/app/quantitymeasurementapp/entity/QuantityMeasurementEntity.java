package com.app.quantitymeasurementapp.entity;

public class QuantityMeasurementEntity {

	private double thisValue;
	private String thisUnit;
	private String thisMeasurementType;

	private Double thatValue;
	private String thatUnit;
	private String thatMeasurementType;

	private String operation;

	private Double resultValue;
	private String resultUnit;
	private String resultMeasurementType;

	private String resultString;

	private boolean isError;
	private String errorMessage;

	// Default constructor (needed for tests)
	public QuantityMeasurementEntity() {
	}

	// Full constructor
	public QuantityMeasurementEntity(double thisValue, String thisUnit, String thisMeasurementType, Double thatValue,
			String thatUnit, String thatMeasurementType, String operation, Double resultValue, String resultUnit,
			String resultMeasurementType, String resultString, boolean isError, String errorMessage) {

		this.thisValue = thisValue;
		this.thisUnit = thisUnit;
		this.thisMeasurementType = thisMeasurementType;

		this.thatValue = thatValue;
		this.thatUnit = thatUnit;
		this.thatMeasurementType = thatMeasurementType;

		this.operation = operation;

		this.resultValue = resultValue;
		this.resultUnit = resultUnit;
		this.resultMeasurementType = resultMeasurementType;

		this.resultString = resultString;

		this.isError = isError;
		this.errorMessage = errorMessage;
	}

	// Getters

	public double getThisValue() {
		return thisValue;
	}

	public String getThisUnit() {
		return thisUnit;
	}

	public String getThisMeasurementType() {
		return thisMeasurementType;
	}

	public Double getThatValue() {
		return thatValue;
	}

	public String getThatUnit() {
		return thatUnit;
	}

	public String getThatMeasurementType() {
		return thatMeasurementType;
	}

	public String getOperation() {
		return operation;
	}

	public Double getResultValue() {
		return resultValue;
	}

	public String getResultUnit() {
		return resultUnit;
	}

	public String getResultMeasurementType() {
		return resultMeasurementType;
	}

	public String getResultString() {
		return resultString;
	}

	public boolean isError() {
		return isError;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	// Setters (needed for some test cases)

	public void setError(boolean error) {
		isError = error;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public void setResultValue(Double resultValue) {
		this.resultValue = resultValue;
	}

	public void setResultUnit(String resultUnit) {
		this.resultUnit = resultUnit;
	}

	public void setResultMeasurementType(String resultMeasurementType) {
		this.resultMeasurementType = resultMeasurementType;
	}

	// toString() for readable output (used in tests)

	@Override
	public String toString() {

		if (isError) {
			return "Error: " + errorMessage;
		}

		return "Operation: " + operation + " | Result: " + resultValue + " " + resultUnit + " | Type: "
				+ resultMeasurementType;
	}
}