package self.vikingar.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import self.vikingar.config.configuration.ApplicationConfig;
import self.vikingar.config.constant.GlobalConstant;
import self.vikingar.interceptor.InterfaceAuthInterceptor;
import self.vikingar.interceptor.PreInterceptor;

import javax.annotation.Nonnull;
import java.util.stream.Stream;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/10/11 14:04
 * @Description:
 **/
@Configuration
@ConditionalOnClass(ApplicationConfig.class)
public class WebConfig extends WebMvcConfigurationSupport {
    /**
     * 不拦截配置
     */
    private static final String[] HTML_SOURCES = {"**.js", "**.css", "**.html", "**.htm", "/error"};
    private static final String[] SOURCES_PATH = {"/static/**", "/webjars/**", "/resources/**", "/favicon.ico"};
    private static final String[] NOT_INTERCEPT_RESOURCES =
            Stream.concat(Stream.of(HTML_SOURCES), Stream.of(SOURCES_PATH)).toArray(String[]::new);

    private final ApplicationConfig applicationConfig;

    public WebConfig(ApplicationConfig applicationConfig) {
        this.applicationConfig = applicationConfig;
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        /*
          拦截全部 前置处理
         */
        InterceptorRegistration perInterceptor = registry.addInterceptor(new PreInterceptor());
        perInterceptor.excludePathPatterns(NOT_INTERCEPT_RESOURCES);
        perInterceptor.addPathPatterns("/**");

        /*
          权限拦截
         */
        InterceptorRegistration authInterceptor = registry.addInterceptor(new InterfaceAuthInterceptor());
        authInterceptor.excludePathPatterns(NOT_INTERCEPT_RESOURCES);
        authInterceptor.addPathPatterns("/**");
    }

    @Override
    protected void addResourceHandlers(@Nonnull ResourceHandlerRegistry registry) {
        /*
        webjar
         */
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        /*
        静态资源路径
         */
        registry.addResourceHandler(GlobalConstant.STATIC_RESOURCE_PATH.getConstant2String() + "**")
                .addResourceLocations("file:" + applicationConfig.getFileBase())
                .addResourceLocations("classpath:/META-INF/resources/")
                .addResourceLocations("classpath:/static/");
        /*
        图标这个坑人的东西
         */
        registry.addResourceHandler("/favicon.ico")
                .addResourceLocations("classpath:/META-INF/resources/")
                .addResourceLocations("classpath:/static/");
    }
}
