package sven.common.exception;

import lombok.Setter;

/**
 * @author ：897863952@qq.com
 * @date ：Created in 2021/2/1 15:15
 * @description：
 * @version:
 * @see
 */
@Setter
public class SvenException extends RuntimeException {

    public SvenException(String message) {
        super(message);
    }

    public SvenException(String message, Throwable cause) {
        super(message, cause);
    }
}
