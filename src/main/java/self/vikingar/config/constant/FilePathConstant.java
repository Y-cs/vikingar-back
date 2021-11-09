package self.vikingar.config.constant;

import self.vikingar.util.PathUtil;

import java.time.LocalDate;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/2 14:31
 * @Description:
 **/
public enum FilePathConstant {

    /**
     * 模板
     */
    TEMPLATE("template/"),
    /**
     * html资源
     */
    HTML_SOURCE("html/"),
    /**
     * 媒体文件
     */
    MEDIA("media/"),

    ;

    private final String path;

    FilePathConstant(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public String getFolder() {
        //文件夹生成策略[folder]/yyyy/MM
        return String.format("%s%s/%s", PathUtil.inspect(this.path), LocalDate.now().getYear(), LocalDate.now().getMonthValue());
    }
}
