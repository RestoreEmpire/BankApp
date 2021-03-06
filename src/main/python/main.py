import requests
import info
import auth
# // Retail Banking
# // Corporate/Commercial Banking
# // Global Banking
# // Private Banking
# // Investment Banking
url = "http://localhost:8080/api/v1/bank-departments"


json = [
    {'departmentName' : 'Retail'},
    {'departmentName' : 'Corporate'},
    {'departmentName' : 'Global trade'},
    {'departmentName' : 'Private'},
    {'departmentName' : 'Investment'}
    ]

for j in json:
    x = requests.post(url, json=j, headers=auth.get_auth_header())
    print("Request: ", j)
    print("Response: ", x.text)

print()

# bad_json = {'asd' : 'asd'}
# post = requests.post(url, json=bad_json)
# print('Bad request ', bad_json)
# print('Bad response', post.text)
# print()

get_all = requests.get(url, headers=auth.get_auth_header())
print(get_all.status_code)
print('All :', get_all.text)
# print(auth.get_auth_header())