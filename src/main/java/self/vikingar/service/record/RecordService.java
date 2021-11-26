package self.vikingar.service.record;

import org.springframework.stereotype.Service;
import self.vikingar.manager.record.config.ParameterKey;
import self.vikingar.manager.record.config.RecordPersistence;
import self.vikingar.manager.record.context.PersistenceContext;
import self.vikingar.mapper.record.LogRecordMapper;
import self.vikingar.model.domain.LogRecordDo;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Optional;

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
    public void save(PersistenceContext persistenceContext) {
        LogRecordDo logRecordDo = new LogRecordDo();
        logRecordDo.setBusinessCode(persistenceContext.getBusinessCode());
        logRecordDo.setResultStatus(persistenceContext.getThrowable() == null);
        logRecordDo.setMessage(persistenceContext.getMessage());
        Map<String, Object> parameter = persistenceContext.getParameter();
        Object method = parameter.get(ParameterKey.METHOD.getKey());
        String methodStr = "";
        if (method instanceof Method) {
            methodStr = ((Method) method).getName();
        }
        logRecordDo.setMethod(String.format("%s:%s",
                Optional.ofNullable(parameter.get(ParameterKey.CLASS.getKey())).orElse(""), methodStr));
        logRecordDo.setException(persistenceContext.getThrowable() == null ? "" : persistenceContext.getThrowable().getMessage());
        logRecordDo.setExtend(persistenceContext.getExtend());
        logRecordDo.isInsert();
        logRecordMapper.insert(logRecordDo);
    }
}
