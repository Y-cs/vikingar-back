package self.vikingar.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.Nullable;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import self.vikingar.ano.NoLoginRequired;
import self.vikingar.config.exception.NoLoginException;
import self.vikingar.manager.GlobalConstant;
import self.vikingar.manager.session.SessionSupport;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/10/11 10:23
 * @Description: 接口权限拦截器
 **/
@Slf4j
public class InterfaceAuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            if (((HandlerMethod) handler).getBeanType().getAnnotation(NoLoginRequired.class) != null
                    || handlerMethod.getMethod().getAnnotation(NoLoginRequired.class) != null) {
                return true;
            }
        }
        String token = request.getHeader(GlobalConstant.TOKEN.getConstant2String());
        if (StringUtils.isBlank(token)) {
            throw new NoLoginException();
        }
        SessionSupport.putToken4Session(token);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           @Nullable ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                @Nullable Exception ex) throws Exception {
    }
}
