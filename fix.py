import re
with open('docker-compose.yml', 'r') as f: content = f.read()
content = re.sub(r'"808(\d):808(\d)"', r'"908\1:808\2"', content)
content = re.sub(r'"809(\d):809(\d)"', r'"909\1:809\2"', content)
with open('docker-compose.yml', 'w') as f: f.write(content)
