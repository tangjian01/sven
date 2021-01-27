package sven.common.enums;

import com.baomidou.mybatisplus.annotation.IEnum;

/**
 * @author ：897863952@qq.com
 * @date ：Created in 2021/1/27 12:14
 * @description：
 * @version:
 * @see
 */
public interface IBaseEnum extends IEnum<Integer> {
    default String getCode() {
        return "";
    }

    default String getName() {
        return "";
    }

    @Override
    default Integer getValue() {
        return null;
    }

    default int getOrder() {
        return 0;
    }

    static <E extends Enum<E> & IBaseEnum> E getByValue(Class<E> enumClass, Integer value) {
        if (value == null) return null;
        E[] enumConstants = enumClass.getEnumConstants();
        for (E item : enumConstants) {
            if (value.equals(item.getValue())) {
                return item;
            }
        }
        return null;
    }

    static <E extends Enum<E> & IBaseEnum> E getByCode(Class<E> enumClass, String code) {
        if (code == null || code.trim() == "") return null;
        E[] enumConstants = enumClass.getEnumConstants();
        for (E item : enumConstants) {
            if (code.equals(item.getCode())) {
                return item;
            }
        }
        return null;
    }
}
