package self.vikingar.controller.base;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import self.vikingar.ano.NoLoginRequired;
import self.vikingar.config.exception.CommonException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/8 13:40
 * @Description:
 **/
@Controller
@NoLoginRequired
@RequestMapping("${server.error.path:${error.path:/error}}")
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    @RequestMapping(produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
        throw CommonException.newException(getErrorMessage(request));
    }

    @RequestMapping
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        throw CommonException.newException(getErrorMessage(request));
    }

    private String getErrorMessage(HttpServletRequest request) {
        Object code = request.getAttribute("javax.servlet.error.status_code");
        Object exceptionType = request.getAttribute("javax.servlet.error.exception_type");
        Object message = request.getAttribute("javax.servlet.error.message");
        Object path = request.getAttribute("javax.servlet.error.request_uri");
        Object exception = request.getAttribute("javax.servlet.error.exception");
        return String.format("code:%s," +
                        "exceptionType:%s," +
                        "message:%s," +
                        "path:%s," +
                        "exception:%s",
                code, exceptionType, message, path, exception);
    }
}
