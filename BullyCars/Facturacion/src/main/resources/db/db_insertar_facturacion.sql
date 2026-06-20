INSERT IGNORE INTO facturas (id, cita_id, total, fecha_emision) VALUES
(1, 1, 40000.00, NOW()),
(2, 2, 325000.00, NOW());

INSERT IGNORE INTO detalles_factura (id, descripcion, cantidad, precio_unitario, factura_id) VALUES
(1, 'Servicio de Reparación General', 1, 40000.00, 1),
(2, 'Stealth Matte Wrap Premium & Coilovers', 1, 325000.00, 2);
