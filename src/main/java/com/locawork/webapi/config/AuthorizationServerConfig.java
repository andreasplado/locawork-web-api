package com.locawork.webapi.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        PasswordEncoder encoder = new BCryptPasswordEncoder(4);
        clients
                .inMemory()
                .withClient("client_a")
                .secret(encoder.encode("password_a"))
                .authorities("ROLE_A")
                .scopes("all")
                .authorizedGrantTypes("client_credentials")
                .and()
                .withClient("client_b")
                .secret(encoder.encode("password_b"))
                .authorities("ROLE_B")
                .scopes("all")
                .authorizedGrantTypes("client_credentials").accessTokenValiditySeconds(24 * 365 * 60 * 60);
    }

    @Bean
    public TokenStore tokenStore() {
        return new InMemoryTokenStore();
    }
}
