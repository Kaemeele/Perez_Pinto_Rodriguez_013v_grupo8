import os
import subprocess

services = ['Citas', 'Vehiculos', 'Inventario', 'Reparaciones', 'EsteticaAutomotriz', 'Facturacion', 'Proveedores', 'Notificaciones', 'Resenas', 'API-Gateway', 'Clientes']

base_dir = os.path.abspath('BullyCars')

for s in services:
    service_dir = os.path.join(base_dir, s)
    if os.path.isdir(service_dir):
        print(f'Compiling {s}...')
        # Use mvnw.cmd on Windows
        mvnw = os.path.join(service_dir, 'mvnw.cmd')
        if os.path.exists(mvnw):
            result = subprocess.run([mvnw, 'clean', 'package', '-DskipTests'], cwd=service_dir, shell=True)
            if result.returncode != 0:
                print(f'Failed to compile {s}')
            else:
                print(f'Successfully compiled {s}')
        else:
            print(f'No mvnw.cmd found in {s}')
