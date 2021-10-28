package self.vikingar.manager.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/10/22 10:31
 * @Description:
 **/
public interface IoSupport {

    /**
     * 初始化
     */
    void init();

    /**
     * 关闭
     */
    void close();

    /**
     * 清理
     */
    void clear();

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
     * @param cover
     * @return
     * @throws IOException
     */
    String saveFile(String name, InputStream inputStream, long size, boolean cover) throws IOException;



}
