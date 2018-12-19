# Oauth2 Authorization Server

1. Requirements:

Java >= 1.8

2. Clone project:

```
git clone https://github.com/dabliuw22/sb-oauth2-auth-server.git
```

3. Switch to the jdbc-client branch:

```
git checkout jdbc-client
```

5. Running the project.

6. Make request to get the bearer token to the endpoint `/oauth/token`:

```
curl -X POST -u clientapp:secret.clientapp\
  'http://localhost:9090/oauth/token?grant_type=password&username=user&password=user.password'
```