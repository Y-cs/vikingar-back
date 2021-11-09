package self.vikingar.config.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
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
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = NoLoginException.class)
    public ResponseEntity<ApiResult<String>> noLoginExceptionHandle(NoLoginException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ApiResult.fail(e.getMessage()));
    }

    @ExceptionHandler(value = CommonException.class)
    public ResponseEntity<ApiResult<String>> commonExceptionHandle(CommonException e) {
        if (log.isErrorEnabled() && e.getPerSeException() != null) {
            log.error("错误内容:" + e.getMessage(), e.getPerSeException());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResult.fail(e.getMessage()));
    }

    @ExceptionHandler(value = {Exception.class, RuntimeException.class})
    public ResponseEntity<ApiResult<String>> runtimeExceptionHandle(Exception e) {
        if (log.isErrorEnabled()) {
            log.error("错误内容", e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResult.fail(e.getMessage()));
    }

    @ExceptionHandler(value = {SizeLimitExceededException.class})
    public ResponseEntity<ApiResult<String>> runtimeExceptionHandle(SizeLimitExceededException e) {
        if (log.isErrorEnabled()) {
            log.error("错误内容", e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResult.fail("文件太大"));
    }

}
