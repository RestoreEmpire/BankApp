from requests.auth import HTTPBasicAuth


deps_json = [
    {'departmentName' : 'Retail'},
    {'departmentName' : 'Corporate'},
    {'departmentName' : 'Global trade'},
    {'departmentName' : 'Private'},
    {'departmentName' : 'Investment'}
    ]

url = "http://localhost:8080/api/v1/bank-departments"

login_url = "http://user:123@localhost:8080/api/v1/bank-departments"

right_auth = HTTPBasicAuth("user", "123")
wrong_auth = HTTPBasicAuth("user", "asd")
