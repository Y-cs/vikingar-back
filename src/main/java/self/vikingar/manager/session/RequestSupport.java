package self.vikingar.manager.session;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import self.vikingar.config.exception.CommonException;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/9 14:10
 * @Description:
 **/
public class RequestSupport {

    private RequestSupport() {
    }

    public static HttpServletRequest getRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes) {
            return ((ServletRequestAttributes) requestAttributes).getRequest();
        }
        throw CommonException.newException("请求不正确");
    }

}
