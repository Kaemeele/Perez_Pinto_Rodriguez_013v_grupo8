import urllib.request, time
for i in range(30):
    try:
        req = urllib.request.urlopen('http://localhost:9081/api/v1/vehiculos')
        if req.status == 200:
            print('Gateway Success!', req.read().decode())
            break
    except Exception as e:
        print('Wait...', e)
    time.sleep(5)
