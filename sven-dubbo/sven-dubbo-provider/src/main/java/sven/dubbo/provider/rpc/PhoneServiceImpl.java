package sven.dubbo.provider.rpc;

import com.alibaba.dubbo.config.annotation.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sven.common.model.User;
import sven.dubbo.api.IPhoneService;
import sven.service.iser.IUserService;

/**
 * @author ：897863952@qq.com
 * @date ：Created in 2021/2/8 14:30
 * @description：
 * @version:
 * @see
 */
@Service(interfaceClass = IPhoneService.class)
@Component
@Slf4j
public class PhoneServiceImpl implements IPhoneService {

    @Autowired
    private IUserService userService;
    @Override
    public User getUser(Long id) {
        if(id == null) return null;
        User user = userService.getById(id);
        return user;
    }
}
