package self.vikingar.manager.record.persistence;

import self.vikingar.manager.record.ano.LogRecord;
import self.vikingar.manager.record.model.LogMessage;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/15 10:29
 * @Description:
 **/
public interface RecordPersistence {

    /**
     * 保存
     * @param successLogMessage
     * @param recordMaster
     * @return
     */
    boolean save(LogMessage successLogMessage, LogRecord recordMaster);

}
