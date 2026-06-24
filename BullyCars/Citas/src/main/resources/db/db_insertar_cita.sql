INSERT IGNORE INTO citas (id, fecha_hora, descripcion_problema, cliente_id, vehiculo_id, confirmado, tipo_servicio, servicio_id) VALUES
(1, '2026-06-01 10:00:00', 'Revisión de frenos y cambio de aceite', 1, 1, FALSE, 'REPARACION', NULL),
(2, '2026-06-15 14:30:00', 'Instalación de Stealth Matte Wrap Premium', 2, 2, FALSE, 'ESTETICA', 2);

INSERT IGNORE INTO estado_cita_historial (id, fecha_hora, estado, detalle, cita_id) VALUES
(1, NOW(), 'PROGRAMADA', 'Cita de tipo REPARACION programada con éxito.', 1),
(2, NOW(), 'PROGRAMADA', 'Cita de tipo ESTETICA programada con éxito.', 2);
