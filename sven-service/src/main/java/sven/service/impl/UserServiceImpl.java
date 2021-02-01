package sven.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import sven.common.model.User;
import sven.ws.dao.mapper.UserDao;
import sven.service.iser.IUserService;
import sven.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author tj
 * @since 2021-01-27
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserDao, User> implements IUserService {

    @Override
    public User getByUserName(String loginAccount) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(User::getName,loginAccount);
        return this.getOne(wrapper);
    }
}
