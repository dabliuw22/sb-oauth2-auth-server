INSERT INTO oauth_client_details(client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri, 
	authorities, access_token_validity, refresh_token_validity,additional_information, autoapprove)
VALUES ('clientapp', null, 'secret.clientapp', 'read', 'authorization_code,refresh_token',
		'http://localhost:9091/callback', null, 60, 120, null, 'true');