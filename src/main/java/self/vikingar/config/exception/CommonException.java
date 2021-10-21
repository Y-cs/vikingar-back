package self.vikingar.config.exception;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/9/30 17:19
 * @Description:
 **/
public class CommonException extends RuntimeException {

    public CommonException(String msg) {
        super(msg);
    }

    public static CommonException newException(String msg) {
        return new CommonException(msg);
    }

}
