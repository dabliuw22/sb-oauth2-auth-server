CREATE TABLE IF NOT EXISTS roles (
	id INTEGER NOT NULL,
	name VARCHAR(256) NOT NULL,
	CONSTRAINT pk_roles PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS users (
	id INTEGER NOT NULL,
	username VARCHAR(256) NOT NULL,
	password VARCHAR(256) NOT NULL,
	is_enabled BOOLEAN DEFAULT true,
	CONSTRAINT pk_users PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS users_roles (
	user_id INTEGER NOT NULL,
	role_id INTEGER NOT NULL,
	CONSTRAINT pk_users_roles PRIMARY KEY (user_id, role_id),
	CONSTRAINT fk_users_roles_users FOREIGN KEY (user_id) REFERENCES users(id),
	CONSTRAINT fk_users_roles_roles FOREIGN KEY (role_id) REFERENCES roles(id)
);

CREATE TABLE IF NOT EXISTS oauth_client_details (
	client_id VARCHAR(256)  NOT NULL,
	resource_ids VARCHAR(256) NULL,
	client_secret VARCHAR(256) NOT NULL,
	scope VARCHAR(256) NOT NULL,
	authorized_grant_types VARCHAR(256) NOT NULL,
	web_server_redirect_uri VARCHAR(256) NOT NULL,
	authorities VARCHAR(256) NULL,
	access_token_validity INTEGER NOT NULL,
	refresh_token_validity INTEGER NOT NULL,
	additional_information VARCHAR(4096) NULL,
	autoapprove VARCHAR(256) NOT NULL,
	user_id INTEGER NOT NULL,
	CONSTRAINT pk_oauth_client_details PRIMARY KEY (client_id),
	CONSTRAINT fk_oauth_client_details_users FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE SEQUENCE IF NOT EXISTS user_sequence START WITH 2 INCREMENT 1;
CREATE SEQUENCE IF NOT EXISTS role_sequence START WITH 2 INCREMENT 1;