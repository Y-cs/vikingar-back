package self.vikingar.model.base;

import lombok.Data;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/10/11 15:54
 * @Description:
 **/
@Data
public class ApiResult<T> {

    private static final int OK = 0;
    private static final int FAIL = -1;

    private final T data;
    private final int code;

    public ApiResult(T t, int code) {
        this.data = t;
        this.code = code;
    }

    public ApiResult(T t) {
        this.data = t;
        this.code = OK;
    }

    public static ApiResult<?> success() {
        return new ApiResult<>("成功", OK);
    }

    public static <T> ApiResult<T> success(T t) {
        return new ApiResult<>(t, OK);
    }

    public static ApiResult<?> fail() {
        return new ApiResult<>("有点问题啊", FAIL);
    }

    public static ApiResult<?> fail(String err) {
        return new ApiResult<>(err, FAIL);
    }

    public static ApiResult<?> fail(RuntimeException runtimeException) {
        return new ApiResult<>(runtimeException.getMessage(), FAIL);
    }

}
