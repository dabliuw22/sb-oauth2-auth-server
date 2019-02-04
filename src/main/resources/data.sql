INSERT INTO roles(id, name) VALUES (1, 'ROLE_USER');

INSERT INTO users(id, username, password) 
VALUES (1, 'user', '$2a$10$CicqJGQXUWn4Sks3EYIxnOxbA5HnkOOjaMECzcy7R8YS5QX2.CNha');

INSERT INTO users_roles VALUES (1, 1);

INSERT INTO oauth_client_details(client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri, 
	authorities, access_token_validity, refresh_token_validity,additional_information, autoapprove, user_id)
VALUES ('clientapp', null, 'secret.clientapp', 'read', 'authorization_code,refresh_token',
		'http://localhost:9091/callback', null, 60, 120, null, 'true', 1);