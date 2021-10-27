package self.vikingar;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/9/30 13:45
 * @Description:
 **/
@Slf4j
@SpringBootApplication
@MapperScan("self.vikingar.mapper.*")
@EnableAsync
@ConfigurationPropertiesScan(basePackages={"self.vikingar.config.configuration"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        log.info("容器已启动...");
    }

}
