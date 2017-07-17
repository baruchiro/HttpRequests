import requests

def GET(requestURL):
    return requests.get(requestURL).text

def POST(requestURL, data):
    return requests.post(requestURL,data).text

print("GET:https://httpbin.org/ip")
print(GET("https://httpbin.org/ip"))

print("POST:https://httpbin.org/post\tDATA:\"Hello World\"")
print(POST("https://httpbin.org/post", "Hello World"))