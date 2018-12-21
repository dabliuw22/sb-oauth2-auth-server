# Oauth2 Authorization Server

Authorization server that allows you to store clients in memory and generate JWT tokens, using the authorization_code grants.

1. Requirements:
	* Java >= 1.8

2. Clone project:

```
git clone https://github.com/dabliuw22/sb-oauth2-auth-server.git
```

3. Switch to the `auth-code-memory-client` branch:

```
git checkout auth-code-memory-client
```

4. Running the project.

5. Obtain authorization code [here](http://localhost:9090/oauth/authorize?client_id=clientapp&redirect_uri=http://localhost:9091&response_type=code&scope=read)
   and copy the code. `username: user` and `password: user.password`

6. Make request to get the bearer token to the endpoint `/oauth/token`.

```
curl -X POST -u clientapp:secret.clientapp\
  'http://localhost:9090/oauth/token?grant_type=authorization_code&code={YOUR_CODE}&scope=read&redirect_uri=http://localhost:9091'
```