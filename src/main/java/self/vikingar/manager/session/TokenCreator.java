package self.vikingar.manager.session;

import java.util.UUID;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/10/11 10:09
 * @Description:
 **/
public class TokenCreator {

    private TokenCreator(){}

    public static String create() {
        return UUID.randomUUID().toString().replace("-", "");
    }


}
