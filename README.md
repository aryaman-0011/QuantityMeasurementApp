# QuantityMeasurementApp

### 📅 UC1 : Feet Measurement Equality

- Implements value-based equality comparison for measurements in feet using proper object-oriented design.
- Two Feet objects are considered equal if their numerical values are equal.
- Key Concepts
  - Overriding equals() method
  - Using Double.compare() for floating-point comparison
  - Null and type safety checks
  - Encapsulation and immutability

Example:
- Input: 1.0 ft and 1.0 ft
- Output: true

[UC1-FeetEquality](https://github.com/aryaman-0011/QuantityMeasurementApp/tree/feature/UC1-FeetEquality/src)

---

### 📅 UC2 : Inch Measurement Equality

- Implements value-based equality comparison for measurements in inches.
- Two Inch objects are considered equal if their numerical values are equal.
- Key Concepts
  - Overriding equals() method
  - Using Double.compare() for floating-point comparison
  - Null and type safety checks
  - Maintaining consistency with Feet equality design

Example:
- Input: 1.0 inch and 1.0 inch
- Output: true

[UC2-InchEquality](https://github.com/aryaman-0011/QuantityMeasurementApp/tree/feature/UC2-InchEquality/src)

---

### 📅 UC3 : Generic Length Class (DRY Principle)

- Refactored separate Feet and Inch classes into a single Length class.
- Introduced LengthUnit enum to represent supported measurement units.
- Eliminated code duplication by applying the DRY (Don't Repeat Yourself) principle.
- Implemented cross-unit equality comparison using conversion to a common base unit (inches).
- Maintains backward compatibility with UC1 and UC2 functionality.
- Key Concepts:
  - DRY Principle
  - Enum usage for type safety
  - Encapsulation of value and unit
  - Cross-unit comparison logic
  - Proper equals() and hashCode() implementation

Example:
- Input: Length(1.0, FEET) and Length(12.0, INCHES)
- Output: true

[UC3-Generic Length Class](https://github.com/aryaman-0011/QuantityMeasurementApp/tree/feature/UC3-GenericLength/src)

---

### 📅 UC4 : Extended Length Units Support - YARD, CENTIMETER

- Extended the generic Length class to support additional measurement units: YARD and CENTIMETER.
- Updated LengthUnit enum with the new units and their conversion factors.
- Maintained DRY principle—no duplicate logic for new units.
- Ensured cross-unit equality comparison works for all supported units (FEET, INCHES, YARD, CENTIMETER).
- Backward compatible: all UC1, UC2, and UC3 functionality remains intact.
- Key Concepts:
  - Enum extension for type-safe new units
  - Conversion logic for added units
  - Cross-unit comparison across all length units
  - Proper equals() and hashCode() handling for extended units

Example:
- Input: Length(3.0, FEET) and Length(1.0, YARD)
- Output: true

[UC4-Entended Length Class](https://github.com/aryaman-0011/QuantityMeasurementApp/tree/feature/UC4-YardEquality/src)

---
