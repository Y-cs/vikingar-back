package self.vikingar.controller.base;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import self.vikingar.ano.NoLoginRequired;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/2 13:11
 * @Description:
 **/
@RestController
@RequestMapping("base")
@NoLoginRequired
public class BaseController {

    @RequestMapping(value = "ping", method = {RequestMethod.GET, RequestMethod.HEAD,
            RequestMethod.POST, RequestMethod.PUT, RequestMethod.PATCH, RequestMethod.DELETE,
            RequestMethod.OPTIONS, RequestMethod.TRACE})
    public String ping() {
        return "pang";
    }

}
