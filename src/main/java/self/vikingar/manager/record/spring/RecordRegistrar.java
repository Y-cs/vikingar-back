package self.vikingar.manager.record.spring;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import self.vikingar.manager.record.RecordHandleFactory;
import self.vikingar.manager.record.config.DefaultSpelRootObject;
import self.vikingar.manager.record.config.LogRecordConfig;
import self.vikingar.manager.record.config.SpelRootObject;
import self.vikingar.manager.record.parse.LogParseManager;
import self.vikingar.manager.record.persistence.DefaultRecordPersistence;
import self.vikingar.manager.record.persistence.RecordPersistence;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/18 9:58
 * @Description:
 **/
@EnableConfigurationProperties(SpringAutoConfig.class)
public class RecordRegistrar {

    @Bean
    @ConditionalOnMissingBean(RecordPersistence.class)
    public RecordPersistence getRecordPersistence() {
        return new DefaultRecordPersistence();
    }


    @Bean
    @ConditionalOnMissingBean(SpelRootObject.class)
    public SpelRootObject getSpelRootObject() {
        return new DefaultSpelRootObject();
    }

    @Bean
    @ConditionalOnMissingBean(LogParseManager.class)
    public LogParseManager getLogParseManager(LogRecordConfig config) {
        return LogParseManager.getDefault(config);
    }


    @Bean
    @ConditionalOnClass({LogRecordConfig.class, RecordPersistence.class, LogParseManager.class})
    public RecordHandleFactory getRecordHandleFactory(LogRecordConfig config, LogParseManager parseManager, RecordPersistence persistence) {
        return new RecordHandleFactory(config, parseManager, persistence);
    }
}
