### 📅 UC1 : Feet Measurement Equality
(17-02-2026)

- Implements value-based equality comparison for measurements in feet.
- Two Feet objects are considered equal if their numerical values are equal.
- Key Concepts
  - Overriding equals() method
  - Using Double.compare() for floating-point comparison
  - Null and type safety checks
  - Encapsulation and immutability

[UC1-FeetEquality](https://github.com/aryaman-0011/QuantityMeasurementApp/tree/feature/UC1-FeetEquality)

---

### 📅 UC2 : Inch Measurement Equality
(18-02-2026)

- Implements value-based equality comparison for measurements in inches.
- Two Inch objects are considered equal if their numerical values are equal.
- Key Concepts
  - Overriding equals() method
  - Using Double.compare() for floating-point comparison
  - Null and type safety checks
  - Maintaining consistency with Feet equality design


[UC2-InchEquality](https://github.com/aryaman-0011/QuantityMeasurementApp/tree/feature/UC2-InchEquality)

---
### 📅 UC3 : Generic Quantity Length 

(19-02-2026)

* Refactors separate Feet and Inch classes into a single generic **Length** class.
* Eliminates code duplication using the **DRY principle**.
* Supports multiple units through an enum.
* Enables cross-unit comparison (e.g., 1 foot = 12 inches).
* Improves scalability and maintainability.

[UC3-GenericLength](https://github.com/aryaman-0011/QuantityMeasurementApp/tree/feature/UC3-GenericLength)

---
### 📅 UC4 : YardEquality

(20-02-2026)

* Extends the generic **Length** design to support additional units.
* Adds **Yards** and **Centimeters** using enum-based configuration.
* Enables comparison across all supported units (feet, inches, yards, centimeters).
* Demonstrates scalability with minimal code changes.

[UC4-YardEquality](https://github.com/aryaman-0011/QuantityMeasurementApp/tree/feature/UC4-YardEquality)

---
### 📅 UC5 : UnitConversion  

(20-02-2026)

- Extends the **Length** design to support explicit unit-to-unit conversion.  
- Adds static `convert()` and instance `convertTo()` methods.  
- Implements validation for null units, NaN, and infinite values.  
- Ensures precision handling and round-trip conversion accuracy.  
- Demonstrates immutability and same-unit optimization.  
- Strengthens test coverage with conversion, validation, and edge cases.  

[UC5-UnitConversion](https://github.com/aryaman-0011/QuantityMeasurementApp/tree/feature/UC5-UnitConversion)

---
