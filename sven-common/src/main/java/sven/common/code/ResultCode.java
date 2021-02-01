package sven.common.code;

import lombok.Getter;
import lombok.Setter;

/**
 * @author ：897863952@qq.com
 * @date ：Created in 2021/2/1 15:02
 * @description：
 * @version:
 * @see
 */
@Setter
@Getter
public class ResultCode {
    public static String LOGIN_SUCCESS = "200";
    public static String LOGOUT_SUCCESS = "200";
    public static String LOGIN_FAIL = "201";
    public static String NO_AUTH = "403";
}
