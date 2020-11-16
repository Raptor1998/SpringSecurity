package com.raptor.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

/**
 * @author raptor
 * @version V1.0
 * @Package com.raptor.config
 * @date 2020/11/14 15:49
 */
@Configuration
@EnableResourceServer
public class OauthSourceConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    public DataSource dataSource;

    //指定token的持久化策略

    /**
     * TokenStore是OAuth2保存token的接口
     * 其下有RedisTokenStore保存到redis中，
     * JdbcTokenStore保存到数据库中，
     * InMemoryTokenStore保存到内存中等实现类，
     * 这里我们选择保存在数据库中
     *
     * @return
     */
    @Bean
    public TokenStore jdbcTokenStore() {
        return new JdbcTokenStore(dataSource);
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId("product_api").tokenStore(jdbcTokenStore());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //指定不同请求方式访问资源所需要的权限，一般查询是read，其余是write。
                .antMatchers(HttpMethod.GET, "/**")
                .access("#oauth2.hasScope('read')")
                .antMatchers(HttpMethod.POST, "/**")
                .access("#oauth2.hasScope('write')")
                .antMatchers(HttpMethod.PATCH, "/**")
                .access("#oauth2.hasScope('write')")
                .antMatchers(HttpMethod.PUT, "/**")
                .access("#oauth2.hasScope('write')")
                .antMatchers(HttpMethod.DELETE, "/**")
                .access("#oauth2.hasScope('write')")
                .and()
                .headers()
                .addHeaderWriter((request, response) -> {
                response.addHeader("Access-Control-Allow-Origin", "*");
            //允许跨域
            if (request.getMethod().equals("OPTIONS")) {
                //如果是跨域的预检请求，则原封不动向下传达请 求头信息
                response.setHeader("Access-Control-Allow-Methods",
                        request.getHeader("Access- Control-Request-Method"));
                response.setHeader("Access-Control-Allow-Headers",
                        request.getHeader("Access- Control-Request-Headers"));
            }
        });
    }
}
