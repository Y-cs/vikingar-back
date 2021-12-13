package self.vikingar.manager.io.impl;

import lombok.extern.slf4j.Slf4j;
import self.vikingar.config.AsyncConfig;
import self.vikingar.config.exception.CommonException;
import self.vikingar.manager.io.AbstractIoHandler;
import self.vikingar.manager.io.addition.IoDefaultPathSupport;
import self.vikingar.manager.io.config.IoConfig;
import self.vikingar.manager.io.config.IoLocalConfig;
import self.vikingar.manager.io.model.PreUseCharIo;
import self.vikingar.util.PathUtil;

import javax.annotation.Nonnull;
import java.io.*;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/10/22 10:32
 * @Description:
 **/
@Slf4j
public class IoHandlerByLocalImpl extends AbstractIoHandler implements IoDefaultPathSupport {

    public static final int MAX_READ_SIZE = 1024;
    private IoLocalConfig localConfig;

    private String path;

    public IoHandlerByLocalImpl(IoConfig ioConfig) {
        super(ioConfig);
        if (ioConfig instanceof IoLocalConfig) {
            this.localConfig = (IoLocalConfig) ioConfig;
        }
        doInit();
    }

    private void doInit() {
        //默认在source目录下
        if (localConfig != null) {
            this.path = localConfig.getFileBase();
        }
        if (this.path == null || this.path.length() == 0) {
            this.path = this.defaultPath();
        }
        this.path = PathUtil.inspect(this.path);
    }

    @Override
    public void clear() {
        //当资源归还的时候应该清理成初始状态
        doInit();
    }

    @Override
    public void setFolderName(@Nonnull String name) {
        this.init();
        //处理间隔符问题
        this.path = this.path + PathUtil.inspect(name);
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
                byte[] bytes = new byte[MAX_READ_SIZE];
                while (inputStream.read(bytes) != -1) {
                    outputStream.write(bytes);
                }
                outputStream.flush();
            }
        }
        return file.getCanonicalPath();
    }

    @Override
    public InputStream readFile(String path) throws IOException {
        File file = new File(path);
        if (file.isFile() && file.exists()) {
            return new FileInputStream(file);
        } else {
            throw new RuntimeException("文件不存在或错误");
        }
    }

    @Override
    public PreUseCharIo getWriter(String name) {
        PreUseCharIo preUseCharIo = new PreUseCharIo();
        File file = new File(this.path + name);
        preUseCharIo.setFile(file);
        //管道-输出流
        PipedWriter pipedWriter = new PipedWriter();
        preUseCharIo.setWriter(pipedWriter);
        //管道-输入流
        try {
            PipedReader pipedReader = new PipedReader(pipedWriter);
            preUseCharIo.setReader(pipedReader);
            //异步读取管道输入流
            AsyncConfig.sync(() -> {
                char[] chars = new char[MAX_READ_SIZE];
                int index = 0;
                try (FileWriter fileWriter = new FileWriter(file)) {
                    while ((index = pipedReader.read(chars)) != -1) {
                        fileWriter.write(chars, 0, index);
                        System.out.println(chars);
                    }
                    fileWriter.flush();
                } catch (IOException e) {
                    log.error("pre use char io transfer write error", e);
                } finally {
                    try {
                        pipedReader.close();
                    } catch (IOException e) {
                        log.error("pre use char io pipedReader close error", e);
                    }
                }
            });
        } catch (IOException e) {
            log.error("pre use char io create error", e);
        }
        return preUseCharIo;
    }

}
