package sven.service.iser;

import sven.common.model.User;
import sven.service.base.IBaseService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author tj
 * @since 2021-01-27
 */
public interface IUserService extends IBaseService<User> {
    User getByUserName(String loginAccount);
}
