package self.vikingar.manager.io;

import self.vikingar.config.AsyncConfig;
import self.vikingar.config.SpringApplication;
import self.vikingar.mapper.source.FileSourceMapper;
import self.vikingar.model.domain.FileSourceDo;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/10/22 10:31
 * @Description:
 **/
public interface IoSupport {

    /**
     * 设置文件夹名
     *
     * @param name
     */
    public void setFolderName(String name);

    /**
     * 保存文件
     *
     * @param name
     * @param inputStream
     * @param size
     * @return
     */
    public boolean saveFile(String name, InputStream inputStream, long size) throws IOException;

    /**
     * 保存信息到数据库
     *
     * @param fileSourceDo
     */
    default void saveInfo(FileSourceDo fileSourceDo) {
        AsyncConfig.sync(() -> {
            fileSourceDo.isInsert();
            SpringApplication.getContext().getBean(FileSourceMapper.class).insert(fileSourceDo);
        });
    }
}
