package self.vikingar.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import self.vikingar.manager.session.SessionSupport;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/10/18 18:13
 * @Description: 前置处理-拦截器
 * 在应用层最前层的拦截处理器
 * 现在主要处理了跨域问题,和资源销毁问题
 **/
@Slf4j
public class PreInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String curOrigin = request.getHeader("Origin");
        //允许来源
        response.setHeader("Access-Control-Allow-Origin", curOrigin == null ? "true" : curOrigin);
        //认证授权
        response.setHeader("Access-Control-Allow-Credentials", "true");
        //请求方式
        response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS");
        //缓存时间
        response.setHeader("Access-Control-Max-Age", "3600");
        //允许的请求头字段
        response.setHeader("Access-Control-Allow-Headers", "*");
        if (CorsUtils.isPreFlightRequest(request)) {
            //预检请求直接返回204
            log.debug("预检请求直接返回");
            response.setStatus(HttpStatus.NO_CONTENT.value());
            return false;
        }
        SessionSupport.clear();
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        /*
         * 资源清理 清理线程活动迹象
         */
        SessionSupport.clear();
    }
}
