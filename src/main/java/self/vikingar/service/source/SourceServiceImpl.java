package self.vikingar.service.source;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import self.vikingar.manager.io.IoObjectPool;
import self.vikingar.manager.io.IoSupport;
import self.vikingar.mapper.source.FileSourceMapper;
import self.vikingar.model.domain.FileSourceDo;
import self.vikingar.util.FileHashUtil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/10/28 13:58
 * @Description:
 **/
@Service
@Slf4j
public class SourceServiceImpl implements SourceService {

    private final FileSourceMapper fileSourceMapper;

    private final IoObjectPool ioObjectPool;

    public SourceServiceImpl(FileSourceMapper fileSourceMapper) {
        this.fileSourceMapper = fileSourceMapper;
        ioObjectPool = new IoObjectPool();
    }

    @Override
    public long saveFile(String folder, String fileName, InputStream inputStream, long size, boolean cover) throws IOException {
        //处理分流
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        inputStream.transferTo(out);
        String name = this.getName(fileName, out.toByteArray(), size);
        Long id = fileSourceMapper.checkFile(name);
        if (id == null) {
            //数据库不存在这个文件->保存文件
            //获取资源
            IoSupport ioSupport = ioObjectPool.getObject();
            String filePath = "";
            try {
                ioSupport.setFolderName(folder);
                //保存文件
                filePath = ioSupport.saveFile(name, new ByteArrayInputStream(out.toByteArray()), size, cover);
            } finally {
                //归还资源
                ioObjectPool.returnObject(ioSupport);
            }
            //保存到数据库
            String[] fileNameSplit = fileName.split("\\.");
            FileSourceDo fileSourceDo = new FileSourceDo();
            fileSourceMapper.insert(fileSourceDo
                    .setFileName(name).setFilePath(filePath)
                    .setFileType(fileNameSplit.length > 1 ? fileNameSplit[1] : "")
                    .setFileSize(size));
            id = fileSourceDo.getId();
        }
        return id;
    }

    public String getName(String fileName, byte[] bytes, long size) throws IOException {
        StringBuilder result = new StringBuilder(FileHashUtil.MD5.checksum(bytes) + FileHashUtil.SHA1.checksum(bytes) + size);
        String[] splitName = fileName.split("\\.");
        //这里 不加文件名的选择是因为文件名不同但文件内容相同应该算作同一个文件
        if (splitName.length > 1) {
            for (int i = 1; i < splitName.length; i++) {
                result.append(".").append(splitName[i]);
            }
        }
        return result.toString();
    }

}
