package com.example.config;

import com.example.filter.JwtAuthenticationTokenFilter;
import com.example.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

import static org.springframework.transaction.TransactionDefinition.withDefaults;

/**
 * @ClassName: SecurityConfiguration
 * @Description:
 * @author: Zhi
 * @date: 2023/3/31 上午9:30
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
    @Resource
    private UserDetailsService userDetailsService;


    /**
     * 获取AuthenticationManager（认证管理器），登录时认证使用
     * Spring Security提供的实现了默认认证和授权的类。开发者使用该类后，便可自定义"身份认证逻辑","全局安全过滤逻辑","url访问权限逻辑"
     * @param authenticationConfiguration
     * @return AuthenticationManager
     * @throws Exception
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    //    配置加密方式
    @Bean
    public PasswordEncoder passwordEncoder(){
        //  相同字符串加密后的密文不一样
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/login**");
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                // // 设置 jwtAuthError 处理认证失败、鉴权失败
                // .exceptionHandling().authenticationEntryPoint(jwtAuthError).accessDeniedHandler(jwtAuthError).and()
        return http
                    .csrf().disable()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    // 登录接口在这里设置没用，得去webSecurityCustomizer设置
                    .and().authorizeRequests(authorize -> authorize
                        // .antMatchers("/link/getAllLink").authenticated()
                        .anyRequest().authenticated()
                    )
                   .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
//                     认证用户时用户信息加载配置，注入springAuthUserService
//                    .userDetailsService(userDetailsService)
                    .logout().disable()
                    .formLogin().loginPage("/login.html").loginProcessingUrl("/login").successForwardUrl("/end").failureUrl("/end")
                    .and().build();

    }

}
