package self.vikingar.config.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import self.vikingar.util.PathUtil;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/10/27 9:47
 * @Description:
 **/
@ConfigurationProperties(prefix = "application.config")
@Setter
@Getter
public class ApplicationConfig {

    private String fileBase;

    public String getFileBase() {
        return fileBase == null ? "" : PathUtil.inspect(fileBase);
    }
}
