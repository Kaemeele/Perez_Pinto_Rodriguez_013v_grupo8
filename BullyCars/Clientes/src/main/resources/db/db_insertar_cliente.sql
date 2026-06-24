INSERT IGNORE INTO clientes (id, nombre, email, password, rol) VALUES 
(1, 'Administrador Maestro', 'admin@bullycars.cl', '$2a$10$8.V28i5rY4rGz5d.GkY.ee4G2dO7r753VqS2H2p0qHpyR.0d8qSKe', 'ROLE_ADMIN'),
(2, 'Juan Perez', 'juan.perez@email.com', '$2a$10$8.V28i5rY4rGz5d.GkY.ee4G2dO7r753VqS2H2p0qHpyR.0d8qSKe', 'ROLE_CLIENTE');

INSERT IGNORE INTO direcciones_clientes (id, calle, ciudad, comuna, cliente_id) VALUES
(1, 'Av. Vitacura 1234', 'Santiago', 'Vitacura', 1),
(2, 'Avenida Providencia 567', 'Santiago', 'Providencia', 2);
