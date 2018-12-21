# Oauth2 Authorization Server

Authorization server that allows to store clients in SQL databases and generate JWT tokens, using the password grants.

1. Requirements:

Java >= 1.8

2. Clone project:

```
git clone https://github.com/dabliuw22/sb-oauth2-auth-server.git
```

3. Switch to the password-jdbc-client-jwt branch:

```
git checkout password-jdbc-client-jwt
```

4. Running the project.

5. Make request to get the bearer token to the endpoint `/oauth/token`:

```
curl -X POST -u clientapp:secret.clientapp\
  'http://localhost:9090/oauth/token?grant_type=password&username=user&password=user.password'
```

6. Generate new `access_token` with the `refresh_token`:

```
curl -X POST -u clientapp:secret.clientapp\
  'http://localhost:9090/oauth/token?grant_type=refresh_token&refresh_token={YOUR_REFRESH_TOKEN}'
```