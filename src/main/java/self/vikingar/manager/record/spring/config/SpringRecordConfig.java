package self.vikingar.manager.record.spring.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import self.vikingar.manager.record.config.RecordConfig;
import self.vikingar.manager.record.config.RecordPersistence;
import self.vikingar.manager.record.config.RecordRootObject;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/23 16:21
 * @Description:
 **/
@ConfigurationProperties(prefix = "record")
public class SpringRecordConfig extends RecordConfig {

    public SpringRecordConfig(RecordRootObject recordRootObject, RecordPersistence persistence) {
        setRecordRootObject(recordRootObject);
        setPersistence(persistence);
    }

}
