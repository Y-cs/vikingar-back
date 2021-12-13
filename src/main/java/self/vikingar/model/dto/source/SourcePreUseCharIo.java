package self.vikingar.model.dto.source;

import lombok.Data;
import self.vikingar.manager.io.model.PreUseCharIo;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/29 16:19
 * @Description:
 **/
@Data
public class SourcePreUseCharIo extends PreUseCharIo {

    public SourcePreUseCharIo(PreUseCharIo preUseCharIo) {
        super(preUseCharIo.getWriter(), preUseCharIo.getReader(), preUseCharIo.getFile());
    }

    private Long sourceId;

}
