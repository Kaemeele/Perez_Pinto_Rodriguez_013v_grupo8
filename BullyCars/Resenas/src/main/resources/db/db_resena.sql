CREATE TABLE IF NOT EXISTS resenas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cliente_id BIGINT,
    calificacion INT,
    comentario VARCHAR(1000)
);
