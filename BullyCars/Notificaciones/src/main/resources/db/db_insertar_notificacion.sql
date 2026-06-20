INSERT IGNORE INTO notificaciones (id, cliente_id, mensaje, fecha_envio) VALUES
(1, 1, 'Su vehículo está listo para revisión y retiro.', NOW()),
(2, 2, 'Su cita para la instalación del Wrap ha sido confirmada para el 15 de Junio.', NOW());

INSERT IGNORE INTO logs_envio (id, canal, estado, fecha_hora, notificacion_id) VALUES
(1, 'EMAIL', 'ENVIADO', NOW(), 1),
(2, 'EMAIL', 'ENVIADO', NOW(), 2);
