package self.vikingar.service.source;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import self.vikingar.config.constant.FilePathConstant;
import self.vikingar.config.exception.CommonException;
import self.vikingar.manager.io.IoHandler;
import self.vikingar.manager.io.model.PreUseCharIo;
import self.vikingar.manager.io.pool.IoHandlerPool;
import self.vikingar.mapper.source.FileSourceMapper;
import self.vikingar.model.domain.FileSourceDo;
import self.vikingar.model.dto.file.FileSourceInsideDto;
import self.vikingar.model.dto.source.SourcePreUseCharIo;
import self.vikingar.util.AssemblyFactory;
import self.vikingar.util.FileHashUtil;
import self.vikingar.util.PathUtil;

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

    private final IoHandlerPool ioHandlerPool;

    public SourceServiceImpl(FileSourceMapper fileSourceMapper, IoHandlerPool ioHandlerPool) {
        this.fileSourceMapper = fileSourceMapper;
        this.ioHandlerPool = ioHandlerPool;
    }

    @Override
    public FileSourceDo saveFile(FilePathConstant folder, String fileName, InputStream inputStream, long size, boolean cover) throws IOException {
        //处理分流
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        inputStream.transferTo(out);
        String name = this.getName(fileName, out.toByteArray(), size);
        FileSourceDo fileSourceDo = fileSourceMapper.selectOne(new LambdaQueryWrapper<FileSourceDo>()
                .eq(FileSourceDo::getFileName, name));
        if (fileSourceDo == null || fileSourceDo.getId() == null) {
            //数据库不存在这个文件->保存文件
            fileSourceDo = new FileSourceDo();
            //获取资源
            IoHandler ioSupport = ioHandlerPool.getHandler();
            String filePath = "";
            String folderPath = folder.getFolder();
            try {
                ioSupport.setFolderName(folderPath);
                //保存文件
                filePath = ioSupport.saveFile(name, new ByteArrayInputStream(out.toByteArray()), size, cover);
            } finally {
                //归还资源
                ioHandlerPool.returnHandler(ioSupport);
            }
            //保存到数据库
            String[] fileNameSplit = fileName.split("\\.");
            fileSourceMapper.insert(fileSourceDo
                    .setFileName(name)
                    .setFilePath(filePath)
                    .setSourcePath(PathUtil.inspect(folderPath) + name)
                    .setFileType(fileNameSplit.length > 1 ? fileNameSplit[1] : "")
                    .setFileSource(ioSupport.getFileSource())
                    .setFileSize(size));
        }
        fileSourceDo.checkIsSave();
        return fileSourceDo;
    }

    @Override
    public FileSourceInsideDto getFileSourceById(long sourceId) {
        FileSourceDo fileSourceDo = fileSourceMapper.selectById(sourceId);
        return AssemblyFactory.defaultAssembling(fileSourceDo, FileSourceInsideDto.class);
    }

    private String getName(String fileName, byte[] bytes, long size) throws IOException {
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

    @Override
    public InputStream readFile(String path) {
        try {
            IoHandler handler = ioHandlerPool.getHandler();
            return handler.readFile(path);
        } catch (IOException e) {
            throw CommonException.newException("文件读取错误", e);
        }
    }

    @SneakyThrows
    @Override
    public SourcePreUseCharIo getOutputWriter(FilePathConstant folder, String fileName) {
        /**
         * 这种都属于预售型的操作怎么混淆文件名呢= =
         */
        IoHandler handler = ioHandlerPool.getHandler();
        handler.setFolderName(folder.getFolder());
        PreUseCharIo preUseCharIo = handler.getWriter(fileName);
        SourcePreUseCharIo sourcePreUseCharIo = new SourcePreUseCharIo(preUseCharIo);
        String[] fileNameSplit = fileName.split("\\.");
        FileSourceDo fileSourceDo = new FileSourceDo();
        fileSourceMapper.insert(fileSourceDo
                .setFileName(fileName)
                .setFilePath(preUseCharIo.getFile().getCanonicalPath())
                .setSourcePath(PathUtil.inspect(folder.getFolder()) + fileName)
                .setFileType(fileNameSplit.length > 1 ? fileNameSplit[1] : "")
                .setFileSource(handler.getFileSource())
                .setFileSize(-1));
        sourcePreUseCharIo.setSourceId(fileSourceDo.getId());
        return sourcePreUseCharIo;
    }

}
