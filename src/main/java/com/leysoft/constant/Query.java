
package com.leysoft.constant;

public class Query {

    public static final String GET_CLIENT_BY_CLIENT_ID =
            "SELECT client_id, client_secret, scope, web_server_redirect_uri "
                    + "FROM oauth_client_details WHERE client_id = ?;";

    public static final String INSERT_CLIENT =
            "INSERT INTO oauth_client_details(client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri, "
                    + "    authorities, access_token_validity, refresh_token_validity,additional_information, autoapprove) "
                    + "VALUES (?, null, ?, ?, 'authorization_code,refresh_token', ?, null, 60, 120, null, 'true');";
}
