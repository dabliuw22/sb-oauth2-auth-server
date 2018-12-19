INSERT INTO oauth_client_details(client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri, 
	authorities, access_token_validity, refresh_token_validity,additional_information, autoapprove)
VALUES ('clientapp', null, '$2a$10$zPQSgpG.MI6Ye3zDEsB7j.PD/XfLCQ5MbCa3fbrE/BrhX5ZJGjN9G', 'read', 'authorization_code,password,refresh_token',
		'http://localhost:9000/callback', null, 60, 120, null, true);