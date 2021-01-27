package sven.service.impl;

import sven.common.model.User;
import sven.ws.dao.mapper.UserDao;
import sven.service.iser.IUserService;
import sven.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tj
 * @since 2021-01-27
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserDao, User> implements IUserService {

}
