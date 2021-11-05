package self.vikingar.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import self.vikingar.config.configuration.ApplicationConfig;
import self.vikingar.interceptor.InterfaceAuthInterceptor;
import self.vikingar.interceptor.PreInterceptor;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/10/11 14:04
 * @Description:
 **/
@Configuration
@ConditionalOnClass(ApplicationConfig.class)
public class WebConfig extends WebMvcConfigurationSupport {
    /**
     * spring设计这个类的存在把关于webmvc的所有配置都拢到了一起 但感觉= =  很不方便和灵活
     */
    private final ApplicationConfig applicationConfig;

    public WebConfig(ApplicationConfig applicationConfig) {
        this.applicationConfig = applicationConfig;
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        /**
         * 拦截全部 前置处理
         */
        InterceptorRegistration perInterceptor = registry.addInterceptor(new PreInterceptor());
        perInterceptor.addPathPatterns("/**");

        /**
         * 权限拦截
         */
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(new InterfaceAuthInterceptor());
        interceptorRegistration.addPathPatterns("/api/**");

    }

}
