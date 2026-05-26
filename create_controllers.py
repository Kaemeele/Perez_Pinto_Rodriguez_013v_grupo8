import os
import glob

services = ['Citas', 'Vehiculos', 'Inventario', 'Reparaciones', 'Estetica', 'Facturacion', 'Proveedores', 'Notificaciones', 'Resenas']

for s in services:
    matches = glob.glob(f'BullyCars/{s}/src/main/java/BullyCars/*')
    if not matches:
        print(f"Skipping {s}, no java dir found")
        continue
    base_dir = matches[0]
    
    ctrl_dir = os.path.join(base_dir, 'Controller')
    os.makedirs(ctrl_dir, exist_ok=True)
    
    path = f'/api/v1/{s.lower()}'
    if s == 'Resenas':
        path = '/api/v1/resenas'
        
    code = f'''package BullyCars.{os.path.basename(base_dir)}.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("{path}")
public class {s}Controller {{

    @GetMapping
    public Map<String, String> get{s}() {{
        return Collections.singletonMap("mensaje", "Conectado a {s} exitosamente");
    }}
}}
'''
    with open(os.path.join(ctrl_dir, f'{s}Controller.java'), 'w', encoding='utf-8') as f:
        f.write(code)
print('Controllers created.')
