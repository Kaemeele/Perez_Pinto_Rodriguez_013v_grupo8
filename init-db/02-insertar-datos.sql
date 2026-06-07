-- 1. Clientes Seeds
USE db_clientes;
INSERT IGNORE INTO clientes (id, nombre, email, password, rol) VALUES 
(1, 'Administrador Maestro', 'admin@bullycars.cl', '$2a$10$8.V28i5rY4rGz5d.GkY.ee4G2dO7r753VqS2H2p0qHpyR.0d8qSKe', 'ROLE_ADMIN'),
(2, 'Juan Perez', 'juan.perez@email.com', '$2a$10$8.V28i5rY4rGz5d.GkY.ee4G2dO7r753VqS2H2p0qHpyR.0d8qSKe', 'ROLE_CLIENTE');

INSERT IGNORE INTO direcciones_clientes (id, calle, ciudad, comuna, cliente_id) VALUES
(1, 'Av. Vitacura 1234', 'Santiago', 'Vitacura', 1),
(2, 'Avenida Providencia 567', 'Santiago', 'Providencia', 2);

-- 2. Vehiculos Seeds
USE db_vehiculos;
INSERT IGNORE INTO vehiculos (id, patente, marca, modelo, anio) VALUES
(1, 'AB12CD', 'Toyota', 'Yaris', 2022),
(2, 'XY78ZW', 'Ford', 'Mustang', 2024);

INSERT IGNORE INTO historial_kilometraje (id, fecha, kilometraje, vehiculo_id) VALUES
(1, NOW(), 15000, 1),
(2, NOW(), 5000, 2);

-- 3. Citas Seeds
USE db_citas;
INSERT IGNORE INTO citas (id, fecha_hora, descripcion_problema, cliente_id, vehiculo_id, confirmado, tipo_servicio, servicio_id) VALUES
(1, '2026-06-01 10:00:00', 'Revisión de frenos y cambio de aceite', 1, 1, FALSE, 'REPARACION', NULL),
(2, '2026-06-15 14:30:00', 'Instalación de Stealth Matte Wrap Premium', 2, 2, FALSE, 'ESTETICA', 2);

INSERT IGNORE INTO estado_cita_historial (id, fecha_hora, estado, detalle, cita_id) VALUES
(1, NOW(), 'PROGRAMADA', 'Cita de tipo REPARACION programada con éxito.', 1),
(2, NOW(), 'PROGRAMADA', 'Cita de tipo ESTETICA programada con éxito.', 2);

-- 4. Estetica Seeds
USE db_estetica;
INSERT IGNORE INTO servicios_estetica (id, nombre, descripcion, precio) VALUES
(1, 'Lavado Premium', 'Lavado exterior, interior y encerado completo', 15000.00),
(2, 'Ceramic Coating 9H Nano-Estelar', 'Protección cerámica de pintura con curado infrarrojo de 9H dureza', 280000.00);

-- 5. Inventario Seeds
USE db_inventario;
INSERT IGNORE INTO repuestos (id, nombre, stock, precio) VALUES
(1, 'Pastillas de Freno', 50, 25000.00),
(2, 'Filtro de Aire K&N Alto Flujo', 15, 45000.00);

-- 6. Proveedores Seeds
USE db_proveedores;
INSERT IGNORE INTO proveedores (id, nombre_empresa, contacto, categoria_insumos) VALUES
(1, 'AutoParts Chile', 'ventas@autoparts.cl', 'Repuestos Generales'),
(2, 'WrapMasters International', 'soporte@wrapmasters.com', 'Vinilos & Wraps Premium');

-- 7. Reparaciones Seeds
USE db_reparaciones;
INSERT IGNORE INTO ordenes_trabajo (id, descripcion, estado, vehiculo_id, mecanico_asignado, cita_id, costo_mano_obra) VALUES
(1, 'Reemplazo de pastillas de freno delanteras', 'Pendiente', 1, 'Carlos Mecánico', 1, 15000.00),
(2, 'Instalación de Suspensión Ajustable Coilover Tein', 'En Proceso', 2, 'Sebastián Tuner', 2, 45000.00);

-- 8. Facturacion Seeds
USE db_facturacion;
INSERT IGNORE INTO facturas (id, cita_id, total, fecha_emision) VALUES
(1, 1, 40000.00, NOW()),
(2, 2, 325000.00, NOW());

INSERT IGNORE INTO detalles_factura (id, descripcion, cantidad, precio_unitario, factura_id) VALUES
(1, 'Servicio de Reparación General', 1, 40000.00, 1),
(2, 'Stealth Matte Wrap Premium & Coilovers', 1, 325000.00, 2);

-- 9. Notificaciones Seeds
USE db_notificaciones;
INSERT IGNORE INTO notificaciones (id, cliente_id, mensaje, fecha_envio) VALUES
(1, 1, 'Su vehículo está listo para revisión y retiro.', NOW()),
(2, 2, 'Su cita para la instalación del Wrap ha sido confirmada para el 15 de Junio.', NOW());

INSERT IGNORE INTO logs_envio (id, canal, estado, fecha_hora, notificacion_id) VALUES
(1, 'EMAIL', 'ENVIADO', NOW(), 1),
(2, 'EMAIL', 'ENVIADO', NOW(), 2);

-- 10. Resenas Seeds
USE db_resenas;
INSERT IGNORE INTO resenas (id, cliente_id, calificacion, comentario) VALUES
(1, 1, 5, 'Excelente servicio, muy rápidos, prolijos y transparentes en la cotización.'),
(2, 2, 5, 'El Stealth Matte Wrap quedó espectacular, la atención al detalle y las terminaciones son perfectas. Totalmente recomendado.');
