package self.vikingar.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import self.vikingar.config.configuration.ApplicationConfig;
import self.vikingar.manager.io.pool.IoHandlerPool;
import self.vikingar.manager.io.config.IoLocalConfig;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/10 14:59
 * @Description:
 **/
@Configuration
@ConditionalOnClass(ApplicationConfig.class)
public class ManagerBean {

    @Bean
    public IoHandlerPool getIoObjectPool(ApplicationConfig applicationConfig) {
        IoLocalConfig ioLocalConfig = new IoLocalConfig();
        ioLocalConfig.setFileBase(applicationConfig.getFileBase());
        return new IoHandlerPool(ioLocalConfig);
    }

}
