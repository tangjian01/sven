package sven.common.base;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;

/**
 * @author ：897863952@qq.com
 * @date ：Created in 2021/1/27 11:43
 * @description：
 * @version:
 * @see
 */
@Getter
@Setter
public class BaseModel implements IBaseModel {
    @TableId
    private Long id;
}
