package sven.dubbo.consumer.refer;

import com.alibaba.dubbo.config.annotation.Reference;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import sven.dubbo.api.IPhoneService;

/**
 * @author ：897863952@qq.com
 * @date ：Created in 2021/2/8 13:57
 * @description：
 * @version:
 * @see
 */
@Service
@Primary
@Getter
@Setter
public class DubboService {
    @Reference
    private IPhoneService iPhoneService;
}
