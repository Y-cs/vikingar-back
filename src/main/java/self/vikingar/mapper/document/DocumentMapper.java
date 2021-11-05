package self.vikingar.mapper.document;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import self.vikingar.model.domain.DocumentInfoDo;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/2 15:32
 * @Description:
 **/
@Repository
public interface DocumentMapper extends BaseMapper<DocumentInfoDo> {
}
