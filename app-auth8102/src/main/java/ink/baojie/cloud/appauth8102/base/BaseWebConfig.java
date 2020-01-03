package ink.baojie.cloud.appauth8102.base;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class BaseWebConfig implements WebMvcConfigurer {
    /**
     * 配置跨越
     */
    // @Override
    // public void addCorsMappings(CorsRegistry registry) {
    //     registry.addMapping("/**")
    //             .allowCredentials(true)
    //             .allowedHeaders("*")
    //             .allowedMethods("*")
    //             .allowedOrigins("*");
    //             // .allowedMethods("GET","POST","PUT","DELETE")
    // }

    // @Override
    // public void addInterceptors(InterceptorRegistry registry) {
    //     // 多个拦截器组成一个拦截器链
    //     // addPathPatterns 用于添加拦截规则
    //     // excludePathPatterns 用户排除拦截
    //     registry.addInterceptor(firstInterceptor()).addPathPatterns("/**");
    // }
    //
    // @Bean
    // public BaseInterceptor firstInterceptor() {
    //     return new BaseInterceptor();
    // }
}
