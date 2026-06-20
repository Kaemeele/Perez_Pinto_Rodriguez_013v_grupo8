CREATE TABLE IF NOT EXISTS notificaciones (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cliente_id BIGINT,
    mensaje VARCHAR(1000),
    fecha_envio DATETIME
);

CREATE TABLE IF NOT EXISTS logs_envio (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    canal VARCHAR(255) NOT NULL,
    estado VARCHAR(255) NOT NULL,
    fecha_hora DATETIME NOT NULL,
    notificacion_id BIGINT,
    FOREIGN KEY (notificacion_id) REFERENCES notificaciones(id) ON DELETE CASCADE
);
