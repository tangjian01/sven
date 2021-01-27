package sven.service.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @author ：897863952@qq.com
 * @date ：Created in 2021/1/27 11:44
 * @description：
 * @version:
 * @see
 */
public class BaseServiceImpl <M extends BaseMapper<T>, T> extends ServiceImpl<M, T>  implements IBaseService<T> {
}
