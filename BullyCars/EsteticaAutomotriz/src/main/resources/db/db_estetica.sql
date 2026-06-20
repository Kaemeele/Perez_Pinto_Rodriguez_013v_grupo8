CREATE TABLE IF NOT EXISTS servicios_estetica (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    descripcion VARCHAR(500),
    precio DOUBLE NOT NULL
);

CREATE TABLE IF NOT EXISTS reservas_estetica (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cita_id BIGINT NOT NULL,
    vehiculo_id BIGINT NOT NULL,
    estado VARCHAR(255) DEFAULT 'Pendiente',
    servicio_estetica_id BIGINT,
    FOREIGN KEY (servicio_estetica_id) REFERENCES servicios_estetica(id)
);
