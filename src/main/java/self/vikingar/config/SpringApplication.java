package self.vikingar.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import self.vikingar.config.exception.StartException;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/10/22 13:42
 * @Description:
 **/
@Configuration
public class SpringApplication implements ApplicationContextAware {

    private static ApplicationContext context;

    private static boolean isReady = false;

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        SpringApplication.setContext(applicationContext);
    }

    private static void setContext(ApplicationContext applicationContext) {
        SpringApplication.context = applicationContext;
        SpringApplication.isReady = true;
    }

    public static ApplicationContext getContext() {
        if (!isReady) {
            //阻止启动
            throw new StartException("ApplicationContext 还没准备好你就使用了,快检查一下是哪里!!");
        }
        return context;
    }

}
