package org.hit.config;

import org.hit.controller.JWTInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册自定义拦截器对象
        registry.addInterceptor(new JWTInterceptor())
                //添加拦截路径
                .addPathPatterns("/admin/**")
                //排除拦截路径
                .excludePathPatterns("/user/login*","/user/**","/user/register","/swagger-resources/**",
                        "/webjars/**", "/v2/**", "/swagger-ui.html/**","/doc.html/**",
                        "/doc.html#/**","/static/**","/doc.html","/v3/api-docs","/static","/**");
    }


}

