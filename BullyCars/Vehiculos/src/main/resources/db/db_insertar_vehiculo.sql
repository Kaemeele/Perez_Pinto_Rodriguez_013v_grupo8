INSERT IGNORE INTO vehiculos (id, patente, marca, modelo, anio) VALUES
(1, 'AB12CD', 'Toyota', 'Yaris', 2022),
(2, 'XY78ZW', 'Ford', 'Mustang', 2024);

INSERT IGNORE INTO historial_kilometraje (id, fecha, kilometraje, vehiculo_id) VALUES
(1, NOW(), 15000, 1),
(2, NOW(), 5000, 2);
