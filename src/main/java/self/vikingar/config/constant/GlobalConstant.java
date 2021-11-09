package self.vikingar.config.constant;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/10/9 16:59
 * @Description:
 **/
public enum GlobalConstant {

    /**
     * session token key
     */
    TOKEN_CACHE_KEY("TOKEN_CACHE_KEY"),

    /**
     * token key by request
     */
    TOKEN("TOKEN"),

    /**
     * request
     */
    REQUEST("REQUEST"),

    STATIC_RESOURCE_PATH("static/"),
    ;

    Object constant;

    GlobalConstant(Object constant) {
        this.constant = constant;
    }

    public String getConstant2String() {
        return constant.toString();
    }

    public long getConstant2Long() {
        return (long) constant;
    }

    @Override
    public String toString() {
        return "GlobalConstant{" +
                "constant=" + constant +
                '}';
    }
}
