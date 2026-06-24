CREATE TABLE IF NOT EXISTS citas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    fecha_hora DATETIME NOT NULL,
    descripcion_problema VARCHAR(500) NOT NULL,
    cliente_id BIGINT NOT NULL,
    vehiculo_id BIGINT NOT NULL,
    confirmado BOOLEAN DEFAULT FALSE,
    tipo_servicio VARCHAR(255) DEFAULT 'REPARACION',
    servicio_id BIGINT
);

CREATE TABLE IF NOT EXISTS estado_cita_historial (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    fecha_hora DATETIME NOT NULL,
    estado VARCHAR(255) NOT NULL,
    detalle VARCHAR(500),
    cita_id BIGINT,
    FOREIGN KEY (cita_id) REFERENCES citas(id) ON DELETE CASCADE
);
