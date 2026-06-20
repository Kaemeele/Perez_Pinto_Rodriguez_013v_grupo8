CREATE TABLE IF NOT EXISTS ordenes_trabajo (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(500) NOT NULL,
    estado VARCHAR(255) DEFAULT 'Pendiente',
    vehiculo_id BIGINT,
    mecanico_asignado VARCHAR(255),
    cita_id BIGINT,
    costo_mano_obra DOUBLE DEFAULT 0.0
);

CREATE TABLE IF NOT EXISTS repuestos_usados (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    repuesto_id BIGINT NOT NULL,
    cantidad INT NOT NULL,
    precio_unitario DOUBLE NOT NULL,
    nombre_repuesto VARCHAR(255),
    orden_trabajo_id BIGINT,
    FOREIGN KEY (orden_trabajo_id) REFERENCES ordenes_trabajo(id) ON DELETE CASCADE
);
