package self.vikingar.manager.record.config;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import self.vikingar.manager.record.RecordPersistence;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/16 14:51
 * @Description:
 **/
@Getter
@Setter
public class LogRecordConfig {

    private String resultParam = "result";
    private Parse parse;

    private SpelRootObject spelRootObject;
    private RecordPersistence recordPersistence;

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
