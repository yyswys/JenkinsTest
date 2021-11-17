package net.project.nine_project.config;

import net.project.nine_project.intercepter.CorsIntercepter;
import net.project.nine_project.intercepter.LoginIntercepter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置拦截器
 * 不用权限可以访问 /api/v1/pub
 * 需要权限访问 /api/v1/pri
 */
@Configuration
public class IntercepterConfig implements WebMvcConfigurer {

    //这里相当于在配置文件中配置bean，实际上就是通过IOC注入LoginIntercepter的对象，且只有一次
    @Bean
    LoginIntercepter loginIntercepter(){
        return new LoginIntercepter();
    }

    @Bean
    CorsIntercepter corsIntercepter() {return new CorsIntercepter();}

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //拦截全部路径，这个跨域配置要放在最上面
        registry.addInterceptor(corsIntercepter()).addPathPatterns("/**");

        registry.addInterceptor(loginIntercepter()).addPathPatterns("/api/v1/pri/*/*/**")
                //不拦截哪些路径,注意以 / 开头
                .excludePathPatterns("/api/v1/pri/user/login","/api/v1/pri/user/register")
        ;
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}