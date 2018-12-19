# Oauth2 Authorization Server

Authorization server that allows to store clients and tokens in SQL databases, using the password grants.

1. Requirements:

Java >= 1.8

2. Clone project:

```
git clone https://github.com/dabliuw22/sb-oauth2-auth-server.git
```

3. Switch to the password-jdbc-client branch:

```
git checkout password-jdbc-client
```

4. Running the project.

5. Make request to get the bearer token to the endpoint `/oauth/token`:

```
curl -X POST -u clientapp:secret.clientapp\
  'http://localhost:9090/oauth/token?grant_type=password&username=user&password=user.password'
```