CREATE TABLE IF NOT EXISTS vehiculos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    patente VARCHAR(255) NOT NULL,
    marca VARCHAR(255) NOT NULL,
    modelo VARCHAR(255) NOT NULL,
    anio INT NOT NULL
);

CREATE TABLE IF NOT EXISTS historial_kilometraje (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    fecha DATETIME NOT NULL,
    kilometraje INT NOT NULL,
    vehiculo_id BIGINT,
    FOREIGN KEY (vehiculo_id) REFERENCES vehiculos(id) ON DELETE CASCADE
);
