package self.vikingar.manager.record.enums;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/18 16:16
 * @Description:
 **/
public enum RecordParamEnum {


    /**
     * 操作人的对象Key
     */
    OPERATOR_KEY("operator"),
    /**
     * 获取操作人的方法
     */
    OPERATOR_METHOD("getOperator()"),
    /**
     * 结果的Key
     */
    RESULT_PARAM("result"),
    /**
     * 错误的Key
     */
    EXCEPTION("exception"),


    ;

    private String key;

    RecordParamEnum(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
