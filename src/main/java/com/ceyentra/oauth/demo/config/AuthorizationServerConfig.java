package com.ceyentra.oauth.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableAuthorizationServer
@PropertySource("classpath:application.properties")
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private Environment env;

	static final String CLIENT_ID = "oauth-client";
//	private String CLIENT_ID = env.getProperty("security.oauth2.client.client-id");
	static final String CLIENT_SECRET = "oauth-secret";
	static final String GRANT_TYPE = "password";
	static final String AUTHORIZATION_CODE = "authorization_code";
    static final String REFRESH_TOKEN = "refresh_token";
    static final String IMPLICIT = "implicit";
	static final String SCOPE_READ = "read";
	static final String SCOPE_WRITE = "write";
    static final String TRUST = "trust";
	static final int ACCESS_TOKEN_VALIDITY_SECONDS = 40;
    static final int REFRESH_TOKEN_VALIDITY_SECONDS = 80;

	@Autowired
	private TokenStore tokenStore;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {

		configurer
				.inMemory()
				.withClient(CLIENT_ID)
				.secret(CLIENT_SECRET)
				.authorizedGrantTypes(GRANT_TYPE, AUTHORIZATION_CODE, REFRESH_TOKEN, IMPLICIT )
				.scopes(SCOPE_READ, SCOPE_WRITE, TRUST)
				.accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS)
				.refreshTokenValiditySeconds(REFRESH_TOKEN_VALIDITY_SECONDS);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.tokenStore(tokenStore)
				.authenticationManager(authenticationManager);
	}
}