package self.vikingar.manager.io.addition;

import lombok.SneakyThrows;
import org.springframework.util.ResourceUtils;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/10/28 11:23
 * @Description:
 **/
public interface IoDefaultPathSupport {

    /**
     * 默认路径
     *
     * @return
     */
    @SneakyThrows
    default String defaultPath() {
        return ResourceUtils.getURL("classpath:").getPath();
    }

}
