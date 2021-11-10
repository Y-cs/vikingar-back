package self.vikingar.manager.io.config;

import com.baomidou.mybatisplus.annotation.EnumValue;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/8 16:43
 * @Description:
 **/
public enum IoTypeEnum {

    /**
     * 本地
     */
    LOCATION(1),
    /**
     * 阿里云
     */
    ALI_OSS(2),

    ;

    @EnumValue
    private final int status;

    IoTypeEnum(int status) {
        this.status = status;
    }
}
