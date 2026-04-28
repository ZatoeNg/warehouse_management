package org.example.config;


import org.example.filter.LoginCheckFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

@Configuration
public class ServletConfig {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

//配置自定义过滤器
    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
//创建FilterRegistrationBean对象
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
//创建自定义过滤器
        LoginCheckFilter loginCheckFilter = new LoginCheckFilter();

//        手动注入
        loginCheckFilter.setRedisTemplate(stringRedisTemplate);

//注册自定义过滤器到filterRegistrationBean
        filterRegistrationBean.setFilter(loginCheckFilter);
//定义过滤规则
        filterRegistrationBean.addUrlPatterns("/*");

        return filterRegistrationBean;

    }
}
