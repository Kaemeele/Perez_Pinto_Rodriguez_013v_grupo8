-- Base databases creation
CREATE DATABASE IF NOT EXISTS db_clientes CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Create custom user and grant privileges
CREATE USER IF NOT EXISTS 'Bully-cars'@'%' IDENTIFIED BY 'qwerty123';
GRANT ALL PRIVILEGES ON *.* TO 'Bully-cars'@'%';
FLUSH PRIVILEGES;
CREATE DATABASE IF NOT EXISTS db_citas CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE DATABASE IF NOT EXISTS db_vehiculos CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE DATABASE IF NOT EXISTS db_inventario CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE DATABASE IF NOT EXISTS db_reparaciones CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE DATABASE IF NOT EXISTS db_estetica CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE DATABASE IF NOT EXISTS db_facturacion CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE DATABASE IF NOT EXISTS db_proveedores CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE DATABASE IF NOT EXISTS db_notificaciones CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE DATABASE IF NOT EXISTS db_resenas CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
