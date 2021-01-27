package sven.common.model;

import com.baomidou.mybatisplus.annotation.TableName;
import sven.common.base.BaseModel;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import sven.common.enums.user.UserTypeEnum;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author tj
 * @since 2021-01-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sv_user")
public class User extends BaseModel {

    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    @TableField("name")
    private String name;

    /**
     * 年龄
     */
    @TableField("age")
    private Integer age;

    /**
     * 性别
     */
    @TableField("user_type")
    private UserTypeEnum userType;

}
