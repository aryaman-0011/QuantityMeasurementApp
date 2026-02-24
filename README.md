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
