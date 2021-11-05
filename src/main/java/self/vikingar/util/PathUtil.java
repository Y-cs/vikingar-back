package self.vikingar.util;

import javax.annotation.Nonnull;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/2 14:35
 * @Description:
 **/
public class PathUtil {

    private static final String SEPARATOR = "/";

    public static String inspect(@Nonnull String name) {
        name = name.replace("\\", "/");
        if (!name.endsWith(SEPARATOR)) {
            name += "/";
        }
        return name;
    }
}
