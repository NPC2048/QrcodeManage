package com.liangyuelong.qrcode.config.security;

import com.liangyuelong.qrcode.common.bean.R;
import com.liangyuelong.qrcode.common.utils.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

/**
 * spring security 配置
 *
 * @author yuelong.liang
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@Configuration
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .cors().and()
                .authorizeRequests().antMatchers("/manage/**").authenticated()
                // 其他路径都不需要登录即可访问
                .anyRequest().permitAll()
                .and().formLogin().loginPage("/login")
                .successHandler((request, response, authentication) -> ResponseUtils.json(response, R.success("登录成功")))
                .failureHandler((request, response, e) -> ResponseUtils.json(response, R.failed("登录失败")))
                .loginProcessingUrl("/loginSuccess")
                .usernameParameter("username").passwordParameter("password").permitAll()
                .and().logout().logoutSuccessHandler((request, response, e) -> ResponseUtils.json(response, R.success("退出登录")))
                .and().exceptionHandling().authenticationEntryPoint((request, response, e) -> ResponseUtils.json(response, R.NO_LOGIN))
        ;
    }

}
