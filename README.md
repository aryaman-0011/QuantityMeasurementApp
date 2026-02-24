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
### 📅 UC6 : UnitAddition
(20-02-2026)

- Implements addition of two **Length** measurements with different units in the same category.  
- Converts both values to a common base unit (feet) to ensure accurate calculation.  
- Returns the result in the unit of the first operand while maintaining immutability.  
- Adds validation for null units, NaN, and infinite values with proper exception handling.  
- Demonstrates concepts like arithmetic on value objects, unit normalization, precision handling, and reusability of conversion logic.  

[UC6-UnitAddition](https://github.com/aryaman-0011/QuantityMeasurementApp/tree/feature/UC6-UnitAddition)

---
### 📅 UC7 : TargetUnitAddition
(20-02-2026)

- Extends UC6 by allowing the result of addition to be returned in any explicitly specified target unit.  
- Supports flexible use cases where the output must be in a required unit regardless of operand units.  
- Converts both operands to a common base unit before addition, then converts the result to the target unit.  
- Ensures immutability by returning a new **Length** object and keeping original objects unchanged.  
- Adds validation for null, invalid units, NaN, and infinite values while maintaining precision and commutativity.  

[UC7-TargetUnitAddition](https://github.com/aryaman-0011/QuantityMeasurementApp/tree/feature/UC7-TargetUnitAddition)

---
### 📅 UC8 : StandaloneUnit
(21-02-2026)

- Refactors **LengthUnit** enum into a standalone top-level class to improve design and scalability.  
- Assigns full responsibility of unit conversion (to and from base unit) to the **LengthUnit** enum.  
- Simplifies **QuantityLength** by delegating conversion logic, focusing only on comparison and arithmetic.  
- Maintains backward compatibility; all features from UC1–UC7 work without modifying client code.  
- Demonstrates principles like Single Responsibility, low coupling, high cohesion, and scalable architecture for future measurement categories.  

[UC8-StandaloneUnit](https://github.com/aryaman-0011/QuantityMeasurementApp/tree/feature/UC8-StandaloneUnit)

---
