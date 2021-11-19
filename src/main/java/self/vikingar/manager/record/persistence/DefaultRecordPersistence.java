package self.vikingar.manager.record.persistence;

import self.vikingar.manager.record.ano.LogRecord;
import self.vikingar.manager.record.model.LogMessage;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/18 17:12
 * @Description:
 **/
public class DefaultRecordPersistence implements RecordPersistence {
    @Override
    public boolean save(LogMessage successLogMessage, LogRecord recordMaster) {
        return true;
    }
}
