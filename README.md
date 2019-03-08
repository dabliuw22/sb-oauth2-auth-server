# Oauth2 Authorization Server

Authorization server that allows you to store clients SQL databases and generate JWT tokens, using the authorization_code grants.

1. Requirements:
	* Java >= 1.8
	* Redis

2. Run Redis with docker:
```
docker image pull redis
docker container run -d --name redis -p 6379:6379 redis
```

3. Clone project:

```
git clone https://github.com/dabliuw22/sb-oauth2-auth-server.git
```

4. Switch to the `auth-code-jpa-client-jwt-login` branch:

```
git checkout auth-code-jpa-client-jwt-login
```

5. Running the project.

6. Login [here](http://localhost:9090/login) with `username: user` and `password: user.password` and obtain authorization code [here](http://localhost:9090/oauth/authorize?client_id=clientapp&redirect_uri=http://localhost:9091/callback&response_type=code&scope=read) and copy the code.

7. Or login here [here](http://localhost:9090/login?protocol=oauth2&client_id=clientapp&redirect_uri=http://localhost:9091/callback&response_type=code&scope=read) with `username: user` and `password: user.password`

8. Make request to get the bearer token to the endpoint `/oauth/token`.

```
curl -X POST -u clientapp:secret.clientapp\
  'http://localhost:9090/oauth/token?grant_type=authorization_code&code={YOUR_CODE}&scope=read&redirect_uri=http://localhost:9091/callback'
```