-- Base databases creation
CREATE DATABASE IF NOT EXISTS db_clientes CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE DATABASE IF NOT EXISTS db_citas CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE DATABASE IF NOT EXISTS db_vehiculos CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE DATABASE IF NOT EXISTS db_inventario CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE DATABASE IF NOT EXISTS db_reparaciones CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE DATABASE IF NOT EXISTS db_estetica CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE DATABASE IF NOT EXISTS db_facturacion CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE DATABASE IF NOT EXISTS db_proveedores CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE DATABASE IF NOT EXISTS db_notificaciones CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE DATABASE IF NOT EXISTS db_resenas CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 1. Clientes Schema
USE db_clientes;
CREATE TABLE IF NOT EXISTS clientes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    rol VARCHAR(255)
);
CREATE TABLE IF NOT EXISTS direcciones_clientes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    calle VARCHAR(255) NOT NULL,
    ciudad VARCHAR(255) NOT NULL,
    comuna VARCHAR(255) NOT NULL,
    cliente_id BIGINT,
    FOREIGN KEY (cliente_id) REFERENCES clientes(id) ON DELETE CASCADE
);

-- 2. Vehiculos Schema
USE db_vehiculos;
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

-- 3. Citas Schema
USE db_citas;
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

-- 4. Estetica Schema
USE db_estetica;
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

-- 5. Inventario Schema
USE db_inventario;
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

-- 6. Proveedores Schema
USE db_proveedores;
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

-- 7. Reparaciones Schema
USE db_reparaciones;
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

-- 8. Facturacion Schema
USE db_facturacion;
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

-- 9. Notificaciones Schema
USE db_notificaciones;
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

-- 10. Resenas Schema
USE db_resenas;
CREATE TABLE IF NOT EXISTS resenas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cliente_id BIGINT,
    calificacion INT,
    comentario VARCHAR(1000)
);
