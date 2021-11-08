package self.vikingar.config.constant;

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

    HTML_SOURCE("html_source/"),


    ;

    private final String path;

    FilePathConstant(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
