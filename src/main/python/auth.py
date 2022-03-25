import requests
import info

login_url = f"{info.url}/login"
login = {'username' : 'user', 'password' : '123'}


def get_token():
    good_post = requests.post(url=login_url, data=login)
    token = good_post.json()['access_token']   
    return token

def print_token():
    print(get_token)

def header_boiler(token):
    return {'Authorization' : f'Bearer {token}'}

def get_auth_header():
    return header_boiler(get_token())



