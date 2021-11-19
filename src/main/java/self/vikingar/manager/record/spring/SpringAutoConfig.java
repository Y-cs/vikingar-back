package self.vikingar.manager.record.spring;

import org.springframework.boot.context.properties.ConfigurationProperties;
import self.vikingar.manager.record.config.LogRecordConfig;
import self.vikingar.manager.record.config.SpelRootObject;
import self.vikingar.manager.record.persistence.RecordPersistence;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/18 9:47
 * @Description:
 **/
@ConfigurationProperties(prefix = "record")
public class SpringAutoConfig extends LogRecordConfig {

    public SpringAutoConfig(RecordPersistence recordPersistence, SpelRootObject spelRootObject){
        super.setRecordPersistence(recordPersistence);
        super.setSpelRootObject(spelRootObject);
    }

}
