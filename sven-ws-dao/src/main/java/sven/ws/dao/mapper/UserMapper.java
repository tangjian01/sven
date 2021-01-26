package sven.ws.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import sven.ws.dao.model.User;

import java.util.List;

/**
 * @author ：897863952@qq.com
 * @date ：Created in 2021/1/26 19:19
 * @description：
 * @version:
 */
public interface UserMapper extends BaseMapper<User> {
    List<User> selectList();
}
