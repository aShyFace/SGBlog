package com.example.config;

import com.example.filter.JwtAuthenticationTokenFilter;
import com.example.handler.security.AccessDeniedHandlerImpl;
import com.example.handler.security.AuthenticationEntryPointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.annotation.Resource;
import java.util.Arrays;

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
    @Resource
    private AuthenticationEntryPointImpl authenticationEntryPoint;
    @Resource
    private AccessDeniedHandlerImpl accessDeniedHandler;
    @Resource
    private YamlConfignature yamlConfignature;


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

    // @Bean
    // public WebSecurityCustomizer webSecurityCustomizer() {
    //     return (web) -> web.ignoring().antMatchers("/login**");
    // }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                    .csrf().disable()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                    .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).accessDeniedHandler(accessDeniedHandler).and()
                    .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
                    // 登录接口在这里设置没用，得去webSecurityCustomizer设置
                    .authorizeRequests(authorize -> authorize
                            // .mvcMatchers().anonymous()
                            .mvcMatchers("/login", "/register").anonymous()
                            .mvcMatchers("/logout", "/user/**", "/comment/**", "/link/**").authenticated()
                            // // 实际上，访问这个接口的时候前端不会携带token，所以这个接口只用来测试权限认证是否开启
                            // .mvcMatchers("/link/getAllLink").authenticated()
                            .anyRequest().permitAll()
                    )
                    .cors().configurationSource(corsConfigurationSource()).and()
                    // 认证用户时用户信息加载配置，注入springAuthUserService
                    .userDetailsService(userDetailsService)
                    .logout().disable()
                    // .formLogin().loginPage("/login.html").loginProcessingUrl("/login").successForwardUrl("/end").failureUrl("/end")
                    // .and().build();
                    .build();
    }

    CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedHeaders(Arrays.asList("*"));
        corsConfiguration.setAllowedMethods(Arrays.asList("*"));
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedOrigins(Arrays.asList(yamlConfignature.Access_Control_Allow_Origin));

        corsConfiguration.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",corsConfiguration);
        return source;
    }


}
