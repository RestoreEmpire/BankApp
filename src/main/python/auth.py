import requests
import info
from requests.auth import HTTPBasicAuth



# get = requests.get(info.url)
# print(get.status_code)
# print(get.text)

good_post = requests.post(url=info.url, auth=('user', '123'))
# good_post = requests.post(url="http://localhost:8080/login?username=user&password=123")
print(good_post.status_code)
print(good_post.text)



