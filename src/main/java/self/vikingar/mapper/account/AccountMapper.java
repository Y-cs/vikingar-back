package self.vikingar.mapper.account;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import self.vikingar.model.domain.AccountDo;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/9/30 16:24
 * @Description:
 **/
@Repository
public interface AccountMapper extends BaseMapper<AccountDo> {


}
