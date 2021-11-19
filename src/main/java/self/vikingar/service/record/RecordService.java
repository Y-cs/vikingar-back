package self.vikingar.service.record;

import org.springframework.stereotype.Service;
import self.vikingar.manager.record.ano.LogRecord;
import self.vikingar.manager.record.model.LogMessage;
import self.vikingar.manager.record.persistence.RecordPersistence;
import self.vikingar.mapper.record.LogRecordMapper;
import self.vikingar.model.domain.LogRecordDo;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/19 18:10
 * @Description:
 **/
@Service
public class RecordService implements RecordPersistence {

    private final LogRecordMapper logRecordMapper;

    public RecordService(LogRecordMapper logRecordMapper) {
        this.logRecordMapper = logRecordMapper;
    }

    @Override
    public boolean save(LogMessage logMessage, LogRecord recordMaster) {
        LogRecordDo logRecordDo = new LogRecordDo();
        logRecordDo.setBusinessCode(recordMaster.businessCode());
        logRecordDo.setResultStatus(logMessage.isOperationResults());
        logRecordDo.setMessage(logMessage.getPullOffMessage());
        logRecordDo.setMethod(logMessage.getMethod() == null ? "" :
                logMessage.getMethod().getDeclaringClass().getName() + "." + logMessage.getMethod().getName());
        logRecordDo.setException(logMessage.getException() == null ? "" :
                logMessage.getException().getMessage());
        logRecordDo.isInsert();
        logRecordMapper.insert(logRecordDo);
        return true;
    }
}
