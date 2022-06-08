package com.lunch.appfeeder.config.security;


import com.lunch.appfeeder.config.CustomAccessDeniedHandler;
import com.lunch.appfeeder.config.JwtAuthenticationFilter;
import com.lunch.appfeeder.model.login.AppRole;
import com.lunch.appfeeder.model.login.AppUser;
import com.lunch.appfeeder.service.role.IAppRoleService;
import com.lunch.appfeeder.service.user.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.PostConstruct;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private IAppUserService appUserService;
    @Autowired
    private IAppRoleService appRoleService;

    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() { //bean mã hóa pass
        return new BCryptPasswordEncoder();
    }

    @Bean
    public RestAuthenticationEntryPoint restServicesEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }

    @Bean
    public CustomAccessDeniedHandler customAccessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //lấy user từ DB
        auth.userDetailsService(appUserService).passwordEncoder(passwordEncoder());
    }

    @PostConstruct
    public void init() {
        List<AppUser> appUsers = (List<AppUser>) appUserService.findAll();
        List<AppRole> appRoleList = (List<AppRole>) appRoleService.findAll();
        if (appRoleList.isEmpty()) {
            AppRole roleAdmin = new AppRole("ROLE_ADMIN");
            appRoleService.save(roleAdmin);
            AppRole roleUser= new AppRole("ROLE_USER");
            appRoleService.save(roleUser);
//            AppRole roleAdminGroup= new AppRole("ROLE_ADMIN_GROUP");
//            appRoleService.save(roleUser);
        }
        if (appUsers.isEmpty()) {
            AppUser admin = new AppUser("admin","123456");
            appUserService.saveAdmin(admin);
        }
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().ignoringAntMatchers("/**");
        http.httpBasic().authenticationEntryPoint(restServicesEntryPoint());//Tùy chỉnh lại thông báo 401 thông qua class restEntryPoint
        http.authorizeRequests()
                .antMatchers("/**",
                        "/login",
                        "/register"
                        ).permitAll()
                .antMatchers("/admin")
                .access("hasRole('ROLE_ADMIN')")
                .anyRequest().authenticated()
                .and().csrf().disable();
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler());
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.cors() ;
    }
}
