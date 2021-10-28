package self.vikingar.model.base;

import lombok.Data;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/10/27 15:59
 * @Description:
 **/
@Data
public class PagingMetadata {

    private int pageIndex = 1;

    private int pageSize = 10;

}
