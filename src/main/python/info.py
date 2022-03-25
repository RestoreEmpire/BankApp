from requests.auth import HTTPBasicAuth


deps_json = [
    {'departmentName' : 'Retail'},
    {'departmentName' : 'Corporate'},
    {'departmentName' : 'Global trade'},
    {'departmentName' : 'Private'},
    {'departmentName' : 'Investment'}
    ]

url = "http://localhost:8080"

department_url = "http://localhost:8080/api/v1/bank-departments"



token = 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNjQ4MTE4NzI3fQ.m8CJyKHXFSvwOPGaffF6WgmpZcnxPftyrtjxQ9O0iwEm8Quqicc-lOZb7rJKXmjouIXT_qFTsFovynH7BqdbDg'
authorization_header = {'Authorization' : f'Bearer {token}'}

right_auth = HTTPBasicAuth("user", "123")
wrong_auth = HTTPBasicAuth("user", "asd")

