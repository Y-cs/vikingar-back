package self.vikingar.manager.record.config;

import lombok.extern.slf4j.Slf4j;
import self.vikingar.manager.record.context.PersistenceContext;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/23 15:19
 * @Description:
 **/
@Slf4j
public class DefaultRecordPersistence implements RecordPersistence {
    @Override
    public void save(PersistenceContext persistenceContext) {
        log.info(persistenceContext.toString());
    }
}
