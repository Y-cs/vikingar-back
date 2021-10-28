package self.vikingar.mapper.source;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import self.vikingar.model.domain.FileSourceDo;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/10/22 13:35
 * @Description:
 **/
@Repository
public interface FileSourceMapper extends BaseMapper<FileSourceDo> {
    /**
     * 检查名称
     * @param name
     * @return
     */
    Long checkFile(@Param("name") String name);
}
