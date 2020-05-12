package com.hpdc.user.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 安全配置类
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()    //所有security全注解配置实现的必要开端）
                .antMatchers("/**").permitAll() //所有路径，全部允许
                .antMatchers("/admin/**").hasRole("admin")  //admin路径，需要admin角色才能访问
                .anyRequest().authenticated()   //任何请求，必须认证后才能访问
                .and().csrf().disable();    //固定写法，禁止启用csrf攻击拦截
    }
}
