CREATE TABLE IF NOT EXISTS facturas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cita_id BIGINT,
    total DOUBLE,
    fecha_emision DATETIME
);

CREATE TABLE IF NOT EXISTS detalles_factura (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(500) NOT NULL,
    cantidad INT NOT NULL,
    precio_unitario DOUBLE NOT NULL,
    factura_id BIGINT,
    FOREIGN KEY (factura_id) REFERENCES facturas(id) ON DELETE CASCADE
);
