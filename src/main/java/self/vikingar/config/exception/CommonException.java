package self.vikingar.config.exception;

import lombok.Getter;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/9/30 17:19
 * @Description:
 **/
@Getter
public class CommonException extends RuntimeException {

    private Exception perSeException;

    /**
     * 通用错误
     *
     * @param msg            错误信息
     * @param perSeException 真实的错误
     */
    public CommonException(String msg, Exception perSeException) {
        super(msg);
        this.perSeException = perSeException;
    }

    public CommonException(String msg) {
        super(msg);
    }

    /**
     * 通用错误
     *
     * @param msg            错误信息
     * @param perSeException 真实的错误
     */
    public static CommonException newException(String msg, Exception perSeException) {
        return new CommonException(msg, perSeException);
    }

    public static CommonException newException(String msg) {
        return new CommonException(msg);
    }

}
