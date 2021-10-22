package self.vikingar.manager.io;

import lombok.SneakyThrows;
import org.springframework.util.ResourceUtils;
import self.vikingar.model.domain.FileSourceDo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Optional;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/10/22 10:32
 * @Description:
 **/
public class IoSupportByLocalImpl implements IoSupport {

    private static final String SEPARATOR = "/";
    private File folder;
    private String path;

    @SneakyThrows
    public IoSupportByLocalImpl() {
        folder = new File(ResourceUtils.getURL("classpath:").getPath());
    }

    @Override
    public void setFolderName(String name) {
        name = name.replace("\\", "/");
        if (!name.startsWith(SEPARATOR)) {
            name = "/" + name;
        }
        if (!name.endsWith(SEPARATOR)) {
            name += "/";
        }
        if (folder != null && folder.isDirectory() && folder.listFiles() != null) {
            for (File file : Objects.requireNonNull(folder.listFiles())) {
                if (file.getName().equals(name) && file.isDirectory()) {
                    folder = file;
                    return;
                }
            }
        }
        folder = new File(Optional.of(Objects.requireNonNull(folder).getPath()).orElse("") + name);
        path = name;
    }

    @Override
    public boolean saveFile(String name, InputStream inputStream, long size) throws IOException {
        File file = new File(Optional.ofNullable(folder).orElse(new File("/")).getPath() + name);
        FileOutputStream outputStream = new FileOutputStream(file);
        byte[] bytes = new byte[10240];
        while (inputStream.read(bytes) != -1) {
            outputStream.write(bytes);
        }
        outputStream.flush();
        outputStream.close();
        String[] fileNameSplit = name.split("\\.");
        saveInfo(new FileSourceDo()
                .setFileName(name).setFilePath(this.path + name)
                .setFileType(fileNameSplit.length > 1 ? fileNameSplit[1] : "")
                .setFileSize(size));
        return true;
    }
}
