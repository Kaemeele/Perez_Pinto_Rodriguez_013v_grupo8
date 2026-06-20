CREATE TABLE IF NOT EXISTS repuestos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    stock INT NOT NULL,
    precio DOUBLE NOT NULL
);

CREATE TABLE IF NOT EXISTS movimientos_inventario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    fecha DATETIME NOT NULL,
    tipo_movimiento VARCHAR(255) NOT NULL,
    cantidad INT NOT NULL,
    descripcion VARCHAR(500),
    repuesto_id BIGINT,
    FOREIGN KEY (repuesto_id) REFERENCES repuestos(id) ON DELETE CASCADE
);
