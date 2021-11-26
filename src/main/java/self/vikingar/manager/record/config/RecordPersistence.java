package self.vikingar.manager.record.config;


import self.vikingar.manager.record.context.PersistenceContext;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/23 14:38
 * @Description:
 **/
public interface RecordPersistence {

    /**
     * 保存
     *
     * @param persistenceContext
     */
    public void save(PersistenceContext persistenceContext);

}
