import requests
import info



expired_token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwicm9sZXMiOlsiVXNlciJdLCJleHAiOjE2NDgyMDEzMDF9.yAeP0rdDvJFIEaNVJEwamEgmQJofXtLeFmTYnKrlKfoBvdxvuyLnzr8kzWkW1hBAcfiDOq4txnR4lraH82Tp0g"
auth_header = info.header_boiler(expired_token)

get = requests.get(url=info.department_url, headers=auth_header)
print(get.json())