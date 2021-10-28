package self.vikingar.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import self.vikingar.model.base.ApiResult;


/**
 * @Author: YuanChangShuai
 * @Date: 2021/10/18 17:59
 * @Description:
 **/
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = NoLoginException.class)
    public ResponseEntity<ApiResult<?>> noLoginExceptionHandle(NoLoginException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ApiResult.fail(e.getMessage()));
    }

    @ExceptionHandler(value = CommonException.class)
    public ResponseEntity<ApiResult<?>> commonExceptionHandle(CommonException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResult.fail(e.getMessage()));
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ApiResult<?>> runtimeExceptionHandle(CommonException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResult.fail(e.getMessage()));
    }

}
