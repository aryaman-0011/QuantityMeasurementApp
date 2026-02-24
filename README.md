# QuantityMeasurementApp

---

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
### 📅 UC9 : WeightMeasurement 
(21-02-2026)

- Introduces a new **Weight** measurement category supporting kilogram, gram, and pound units.  
- Implements equality, conversion, and addition operations similar to the Length design.  
- Uses a standalone **WeightUnit** enum responsible for conversion to and from the base unit (kilogram).  
- Ensures immutability, validation, and precision while maintaining category type safety (weight ≠ length).  
- Demonstrates scalability and reusability of the architecture for multiple measurement categories.  

[UC9-WeightMeasurement](https://github.com/aryaman-0011/QuantityMeasurementApp/tree/feature/UC9-WeightMeasurement)

---
### 📅 UC10 : GenericQuantity
(21-02-2026)

- Refactors the design into a single generic **Quantity<U extends IMeasurable>** class to eliminate duplication across categories.  
- Introduces a common **IMeasurable** interface implemented by all unit enums (LengthUnit, WeightUnit).  
- Removes parallel QuantityLength and QuantityWeight classes, enforcing DRY and Single Responsibility principles.  
- Ensures type safety using generics while preventing cross-category comparison (length ≠ weight).  
- Establishes a scalable architecture where adding a new category only requires a new enum implementing **IMeasurable**, with no changes to the core Quantity class.  

[UC10-GenericQuantity](https://github.com/aryaman-0011/QuantityMeasurementApp/tree/feature/UC10-GenericQuantity)

---
### 📅 UC11 : VolumeEquality
(23-02-2026)

- Adds **Volume measurement support** (Litre, Millilitre, Gallon) using the generic **Quantity<U>** class and **IMeasurable** interface without modifying existing architecture.  
- Implements a new **VolumeUnit enum** with litre as the base unit and accurate conversion factors for seamless cross-unit operations.  
- Supports **equality comparison, unit conversion, and addition** for volume measurements similar to length and weight categories.  
- Ensures **type safety and category isolation**, preventing comparisons between volume, length, and weight measurements.  
- Validates the **scalability and reusability** of the generic design by adding a third category with minimal effort, proving DRY and extensibility.  

[UC11-VolumeMeasurement](https://github.com/aryaman-0011/QuantityMeasurementApp/tree/feature/UC11-VolumeEquality) 

---
### 📅 UC12 : SubtractionDivision
(23-02-2026)

- Adds **subtraction and division** to the generic Quantity design.  
- Supports **cross-unit subtraction** with implicit or explicit target unit.  
- Division returns a **dimensionless ratio** for comparison.  
- Ensures **immutability, validation, and type safety** across categories.  
- Maintains consistency with equality, conversion, and addition.  

[UC12-SubtractionDivision](https://github.com/aryaman-0011/QuantityMeasurementApp/tree/feature/UC12-SubtractionDivision)  

---
### 📅 UC13: CentralizedArithmetic
(23-02-2026)

- **Refactored arithmetic operations** (add, subtract, divide) to remove code duplication.  
- Introduced a **centralized private helper** for validation, base-unit conversion, and operation execution.  
- Created **ArithmeticOperation enum** (ADD, SUBTRACT, DIVIDE) to cleanly handle operations.  
- Ensures **consistent error handling**, type checks, and immutability across all operations.  
- Public API remains **unchanged**; all UC12 behaviors preserved.  
- Improves **maintainability, readability, and scalability** for future operations (multiplication, modulo, etc.).  
- Demonstration in QuantityMeasurementApp works exactly as before; results unchanged.  


[UC13-CentralizedArithmetic](https://github.com/aryaman-0011/QuantityMeasurementApp/tree/feature/UC13-CentralizedArithmetic) 

---
### 📅 UC14: TemperatureMeasurement
(23-02-2026)

- Added **TemperatureUnit** (Celsius, Fahrenheit, Kelvin) with conversion support.  
- Refactored **IMeasurable**: arithmetic operations optional via **default methods**.  
- Introduced **SupportsArithmetic** interface; lambdas indicate supported operations.  
- Temperature supports **equality & conversion**; unsupported arithmetic throws exceptions.  
- **Quantity<U>** checks operation support before execution.  
- Maintains **cross-category type safety** and backward compatibility with UC1–UC13.  
- Follows **SOLID principles**, enabling scalable future measurement types.  

[UC14-TemperatureMeasurement](https://github.com/aryaman-0011/QuantityMeasurementApp/tree/feature/UC14-TemperatureMeasurement) 


