INSERT INTO quantity_measurement_entity(
this_value,this_unit,this_measurement_type,
that_value,that_unit,that_measurement_type,
operation,result_value,result_unit,result_measurement_type,
result_string,is_error,error_message
)
VALUES
(10,'FEET','LENGTH',24,'INCHES','LENGTH','ADD',12,'FEET','LENGTH','12 FEET',FALSE,NULL);