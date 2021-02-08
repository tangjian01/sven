package sven.dubbo.api;

import sven.common.model.User;

/**
 * @author ：897863952@qq.com
 * @date ：Created in 2021/2/8 13:52
 * @description：
 * @version:
 */
public interface IPhoneService {
    User getUser(Long id);
}
