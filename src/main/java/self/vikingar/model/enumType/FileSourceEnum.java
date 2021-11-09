package self.vikingar.model.enumType;

import com.baomidou.mybatisplus.annotation.EnumValue;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/8 16:43
 * @Description:
 **/
public enum FileSourceEnum {

    LOCATION(1),
    ALI_OSS(2),

    ;

    @EnumValue
    private final int status;

    FileSourceEnum(int status) {
        this.status = status;
    }
}
