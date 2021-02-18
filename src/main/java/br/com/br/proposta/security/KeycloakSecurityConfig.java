package br.com.br.proposta.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;


@EnableWebSecurity
public class KeycloakSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests(authorize ->
		authorize
		.antMatchers(HttpMethod.POST,"/api/proposta/**").hasAuthority("SCOPE_proposta:write")
		.antMatchers(HttpMethod.GET,"/api/proposta/**").hasAuthority("SCOPE_proposta:read")
		.antMatchers(HttpMethod.GET,"/api/actuator/**").hasAuthority("SCOPE_proposta:read")
		.antMatchers(HttpMethod.POST,"/api/biometria/**").hasAuthority("SCOPE_proposta:write")
		.antMatchers(HttpMethod.POST,"/api/bloqueio/**").hasAuthority("SCOPE_proposta:write")
		.antMatchers(HttpMethod.POST,"/api/cartoes/**").hasAuthority("SCOPE_proposta:write")
		.antMatchers(HttpMethod.POST,"/api/cartoes/**").hasAuthority("SCOPE_proposta:read")
		.anyRequest().authenticated())
		.csrf().disable()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
	}



}
