package self.vikingar.manager.io;

import self.vikingar.manager.io.config.IoTypeEnum;
import self.vikingar.manager.io.pool.PoolObject;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/10/22 10:31
 * @Description:
 **/
public interface IoHandler extends PoolObject {

    /**
     * 设置文件夹名
     *
     * @param name
     */
    void setFolderName(String name);

    /**
     * 保存文件
     *
     * @param name
     * @param inputStream
     * @param size
     * @param cover
     * @return
     * @throws IOException
     */
    String saveFile(String name, InputStream inputStream, long size, boolean cover) throws IOException;

    /**
     * 读取流
     *
     * @param path
     * @return
     * @throws IOException io错误
     */
    InputStream readFile(String path) throws IOException;

    /**
     * 用于获取子类的类型枚举,这个用于标注实现的类型本地还是云还是巴拉巴拉的
     * 由{@link AbstractIoHandler}实现
     * @return
     */
    IoTypeEnum getFileSource();
}
