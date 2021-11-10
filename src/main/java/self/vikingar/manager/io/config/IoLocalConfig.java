package self.vikingar.manager.io.config;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/10 15:08
 * @Description:
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class IoLocalConfig extends IoConfig {

    private String fileBase;

    @Override
    public void initIoTypeEnum() {
        super.setIoTypeEnum(IoTypeEnum.LOCATION);
    }
}
