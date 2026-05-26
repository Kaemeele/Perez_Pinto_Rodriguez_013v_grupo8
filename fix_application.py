import os
import glob
import re

for app_file in glob.glob('BullyCars/*/src/main/java/BullyCars/*/*Application.java') + glob.glob('BullyCars/*/src/main/java/BullyCars/*Application.java'):
    with open(app_file, 'r', encoding='utf-8') as f:
        content = f.read()
    
    # Replace the @SpringBootApplication(exclude = {...}) with just @SpringBootApplication
    new_content = re.sub(r'@SpringBootApplication\(exclude\s*=\s*\{[^}]+\}\)', '@SpringBootApplication', content)
    
    with open(app_file, 'w', encoding='utf-8') as f:
        f.write(new_content)
        
print("Fixed Application.java files.")
