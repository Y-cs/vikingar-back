package self.vikingar.manager.record.config;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import self.vikingar.manager.record.persistence.DefaultRecordPersistence;
import self.vikingar.manager.record.persistence.RecordPersistence;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/16 14:51
 * @Description:
 **/
@Getter
@Setter
public class LogRecordConfig {

    /**
     * 记录的解析方案
     */
    private Parse parse=new Parse();

    private Long defaultOperator = -1L;

    /**
     * 传递给Spel解析器的RootObject
     */
    private SpelRootObject spelRootObject = new DefaultSpelRootObject();
    /**
     * 持久化操作对象
     */
    private RecordPersistence recordPersistence = new DefaultRecordPersistence();

    public static class Parse {
        private String start;
        private String end;

        public String getStart() {
            return StringUtils.isBlank(start) ? "`" : start;
        }

        public String getEnd() {
            return StringUtils.isBlank(end) ? "`" : end;
        }
    }
}
