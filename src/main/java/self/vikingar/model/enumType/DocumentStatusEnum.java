package self.vikingar.model.enumType;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/2 15:53
 * @Description:
 **/
public enum DocumentStatusEnum {

    /**
     * 草稿
     */
    DRAFT(0, "草稿"),
    PUBLISHED(1, "已发布"),
    DELAYED_RELEASE(2, "延时发布"),
    ;

    @EnumValue
    private int status;

    @JsonValue
    private String describe;

    DocumentStatusEnum(int status, String describe) {
        this.status = status;
        this.describe = describe;
    }
}
