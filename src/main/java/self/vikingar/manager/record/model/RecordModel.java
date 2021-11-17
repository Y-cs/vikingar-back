package self.vikingar.manager.record.model;

import lombok.Data;
import self.vikingar.manager.record.enums.RecordEnum;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/15 10:33
 * @Description:
 **/
@Data
public class RecordModel {

    private String record;

    private RecordEnum recordType;

}
