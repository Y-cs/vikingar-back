package self.vikingar.config.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/10/28 10:51
 * @Description:
 **/
@Slf4j
public class StartException extends RuntimeException {

    public StartException(String msg) {
        super(msg);
        log.error("终止运行:{}", msg);
        throw new Error(msg);
    }

}
