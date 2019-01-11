create table oauth_client_details (
  client_id VARCHAR(256) PRIMARY KEY NOT NULL,
  resource_ids VARCHAR(256) NULL,
  client_secret VARCHAR(256) NOT NULL,
  scope VARCHAR(256) NOT NULL,
  authorized_grant_types VARCHAR(256) NOT NULL,
  web_server_redirect_uri VARCHAR(256) NOT NULL,
  authorities VARCHAR(256) NULL,
  access_token_validity INTEGER NOT NULL,
  refresh_token_validity INTEGER NOT NULL,
  additional_information VARCHAR(4096) NULL,
  autoapprove VARCHAR(256) NOT NULL
);