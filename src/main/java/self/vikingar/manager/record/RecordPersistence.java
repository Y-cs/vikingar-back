package self.vikingar.manager.record;

import self.vikingar.manager.record.model.RecordModel;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/15 10:29
 * @Description:
 **/
public interface RecordPersistence {

    /**
     * 保存
     * @param recordModel
     * @return
     */
    boolean save(RecordModel recordModel);

}
