package self.vikingar.manager.io;

import lombok.extern.slf4j.Slf4j;
import self.vikingar.config.SpringApplication;
import self.vikingar.config.configuration.ApplicationConfig;
import self.vikingar.config.exception.CommonException;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/10/22 10:32
 * @Description:
 **/
@Slf4j
public class IoSupportByLocalImpl implements IoSupport, IoDefaultPathSupport {

    private static final String SEPARATOR = "/";
    private String path;

    @Override
    public void init() {
        //默认在source目录下
        path = SpringApplication.getContext().getBean(ApplicationConfig.class).getFileBase();
        if (path == null || path.length() == 0) {
            path = this.defaultPath();
        }
        path = this.inspect(path);
    }

    @Override
    public void close() {

    }

    @Override
    public void clear() {
    }

    @Override
    public void setFolderName(@Nonnull String name) {
        this.init();
        //处理间隔符问题
        path = path + this.inspect(name);
        File file = new File(this.path);
        if (!file.exists()) {
            boolean mkdirs = file.mkdirs();
            if (!mkdirs) {
                throw CommonException.newException("路径创建失败");
            }
        }
    }

    @Override
    public String saveFile(String name, InputStream inputStream, long size, boolean cover) throws IOException {
        File file = new File(this.path + name);
        /**
         * 覆盖  ||  不存在  ||  是文件夹
         */
        if (cover || !file.exists() || file.isDirectory()) {
            //输出文件
            try (FileOutputStream outputStream = new FileOutputStream(file)) {
                byte[] bytes = new byte[10240];
                while (inputStream.read(bytes) != -1) {
                    outputStream.write(bytes);
                }
                outputStream.flush();
            }
        }
        return this.path + name;
    }

    private String inspect(@Nonnull String name) {
        name = name.replace("\\", "/");
        if (!name.endsWith(SEPARATOR)) {
            name += "/";
        }
        return name;
    }
}
