package self.vikingar.model.base;

import com.github.pagehelper.PageInfo;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/10/27 15:31
 * @Description:
 **/
@Getter
@EqualsAndHashCode(callSuper = true)
public class PageResult<T> extends ApiResult<List<T>>{

    /**
     * 当前页
     */
    private long pageIndex;
    /**
     * 总共多少页
     */
    private long pageTotal;

    /**
     * 总共多少条
     */
    private long total;

    /**
     * 每页多少条
     */
    private long pageSize;

    public PageResult(List<T> t) {
        super(t);
    }

    public static <T> PageResult<T> paging(List<T> data) {
        PageInfo<T> pageInfo = new PageInfo<>(data);
        PageResult<T> pageResult = new PageResult<>(data);
        pageResult.pageIndex = pageInfo.getPageNum();
        pageResult.pageSize = pageInfo.getPageSize();
        pageResult.pageTotal = pageInfo.getPages();
        pageResult.total = pageInfo.getTotal();
        return pageResult;
    }

}
