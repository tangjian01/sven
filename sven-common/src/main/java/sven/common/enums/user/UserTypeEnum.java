package sven.common.enums.user;

import sven.common.enums.IBaseEnum;

/**
 * @author ：897863952@qq.com
 * @date ：Created in 2021/1/27 13:45
 * @description：
 * @version:
 * @see
 */
public enum UserTypeEnum implements IBaseEnum {
    man("nan",1),
    woman("nv",2)
    ;

    UserTypeEnum(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    private String name ;
    private Integer value;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}
