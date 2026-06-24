CREATE TABLE IF NOT EXISTS proveedores (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre_empresa VARCHAR(255) NOT NULL,
    contacto VARCHAR(255),
    categoria_insumos VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS insumos_proveedores (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre_insumo VARCHAR(255) NOT NULL,
    precio DOUBLE NOT NULL,
    proveedor_id BIGINT,
    FOREIGN KEY (proveedor_id) REFERENCES proveedores(id) ON DELETE CASCADE
);
