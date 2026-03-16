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
}
