package self.vikingar.manager.record.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/15 10:23
 * @Description:
 **/
public enum RecordEnum {
    ;


    @EnumValue
    private String type;

    RecordEnum(String type) {
        this.type = type;
    }
}
