package self.vikingar.config.exception;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/10/11 14:25
 * @Description:
 **/
public class NoLoginException extends RuntimeException {

    public NoLoginException() {
        super("没有登陆");
    }


}
