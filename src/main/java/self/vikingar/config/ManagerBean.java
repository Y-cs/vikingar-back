package self.vikingar.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import self.vikingar.config.configuration.ApplicationConfig;
import self.vikingar.manager.io.config.IoLocalConfig;
import self.vikingar.manager.io.pool.IoHandlerPool;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/10 14:59
 * @Description:
 **/
@Configuration
public class ManagerBean {

    @Bean
    @ConditionalOnClass(ApplicationConfig.class)
    public IoHandlerPool getIoObjectPool(ApplicationConfig applicationConfig) {
        IoLocalConfig ioLocalConfig = new IoLocalConfig();
        ioLocalConfig.setFileBase(applicationConfig.getFileBase());
        return new IoHandlerPool(ioLocalConfig);
    }

}
