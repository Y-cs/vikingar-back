package self.vikingar.manager.record.factory;

import org.aspectj.lang.reflect.MethodSignature;
import self.vikingar.manager.record.ano.Record;
import self.vikingar.manager.record.config.RecordConfig;
import self.vikingar.manager.record.support.RecordSupport;

import java.util.List;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/22 15:41
 * @Description: 日志支持工厂
 **/
public class RecordSupportFactory {

    private final RecordConfig recordConfig;

    private final RecordParseFactory parseFactory;

    public RecordSupportFactory(RecordConfig recordConfig, RecordParseFactory parseFactory) {
        this.recordConfig = recordConfig;
        this.parseFactory = parseFactory;
    }

    /**
     * @param methodSignature 方法
     * @param args            参数
     * @param record          记录对象
     * @return 处理类
     */
    public RecordSupport create(MethodSignature methodSignature, List<Object> args, Record record) {
        RecordSupport recordSupport = new RecordSupport(recordConfig, parseFactory.getParse());
        recordSupport.initMethodAndArgs(methodSignature, args);
        recordSupport.setRecord(record);
        return recordSupport;
    }

}
