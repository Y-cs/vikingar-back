package self.vikingar.manager.record.spring.factory;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import self.vikingar.manager.record.config.*;
import self.vikingar.manager.record.factory.RecordParseFactory;
import self.vikingar.manager.record.factory.RecordSupportFactory;
import self.vikingar.manager.record.spring.config.SpringRecordConfig;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/23 16:20
 * @Description:
 **/
@EnableConfigurationProperties(SpringRecordConfig.class)
public class RecordFactory {

    @Bean
    @ConditionalOnMissingBean(RecordRootObject.class)
    public RecordRootObject getRecordRootObject() {
        return new DefaultRecordRootObject();
    }

    @Bean
    @ConditionalOnMissingBean(RecordPersistence.class)
    public RecordPersistence getRecordPersistence() {
        return new DefaultRecordPersistence();
    }

    @Bean
    @ConditionalOnMissingBean(RecordParseFactory.class)
    public RecordParseFactory getRecordParseFactory(RecordConfig recordConfig) {
        return new RecordParseFactory(recordConfig);
    }

    @Bean
    @ConditionalOnMissingBean(RecordSupportFactory.class)
    public RecordSupportFactory recordSupportFactory(RecordConfig recordConfig, RecordParseFactory parseFactory) {
        return new RecordSupportFactory(recordConfig, parseFactory);
    }


}
