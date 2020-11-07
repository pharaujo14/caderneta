package br.com.caderneta.security;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.caderneta.model.RoleEnum;
import lombok.AllArgsConstructor;

@EnableWebSecurity
@AllArgsConstructor
public class SecurityWebConfig extends WebSecurityConfigurerAdapter{
	
	private UserDetailsServiceImpl userService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
				
		http.authorizeRequests()
		 .antMatchers("/", "/csrf", "/v2/api-docs", "/configuration/ui", "/swagger-resources/**",
				"/configuration/**", "/swagger-ui.html", "/webjars/**", "/h2/**", "/h2-console/**").permitAll()
		 .antMatchers(HttpMethod.POST, "/alunos").permitAll()
		 .antMatchers(HttpMethod.POST, "/professores").permitAll()
		 .antMatchers(HttpMethod.POST, "/turmas").permitAll()
		 .antMatchers(HttpMethod.POST, "/aulas").permitAll()
		 .antMatchers(HttpMethod.GET, "/alunos/**").permitAll()
		 .antMatchers(HttpMethod.GET, "/professores").hasRole(RoleEnum.PROFESSOR.getName())
		 .anyRequest().permitAll() // arrumar
		 .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		 .and().cors().disable().csrf().disable()
		 .httpBasic();
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder builder) throws Exception {
		builder.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder());

	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
