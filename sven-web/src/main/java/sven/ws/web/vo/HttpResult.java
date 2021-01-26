package sven.ws.web.vo;

/**
 * @author ：897863952@qq.com
 * @date ：Created in 2021/1/21 16:06
 * @description：
 * @version:
 * @see
 */
public class HttpResult<T> {

    private final static String OK = "0000";
    private final static String NO = "9999";
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String code;
    private String msg;
    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


    /// <summary>
    /// 无参构造函数
    /// </summary>
    public HttpResult() {
        this(OK, null, null);
    }

    /// <summary>
    /// 有参构造函数
    /// </summary>
    /// <param name="code">返回状态</param>
    public HttpResult(String code) {
        this(code, null, null);
    }


    /// <summary>
    /// 有参构造函数
    /// </summary>
    /// <param name="code">返回状态</param>
    /// <param name="msg">返回信息</param>
    public HttpResult(String code, String msg) {
        this(code, msg, null);
    }


    /// <summary>
    /// 有参构造函数
    /// </summary>
    /// <param name="code">返回状态</param>
    /// <param name="val">记录</param>
    public HttpResult(String code, T val) {
        this(code, null, val);
    }


    /// <summary>
    /// 有参构造函数
    /// </summary>
    /// <param name="code">返回状态</param>
    /// <param name="msg">返回信息</param>
    /// <param name="val">记录</param>
    public HttpResult(String code, String msg, T val) {
        this.code = code;
        this.msg = msg;
        this.data = val;
    }

    /// <summary>
    /// OK
    /// </summary>
    /// <returns></returns>
    public static <T> HttpResult<T> ok() {
        return ok(null);
    }

    /// <summary>
    /// OK
    /// </summary>
    /// <param name="data">返回值</param>
    /// <returns></returns>
    public static <T> HttpResult<T> ok(T value) {
        return ok(value, null);
    }

    /// <summary>
    /// OK
    /// </summary>
    /// <param name="msg">提示信息</param>
    /// <param name="data">返回值</param>
    /// <returns></returns>
    public static <T> HttpResult<T> ok(T value, String message) {
        return new HttpResult<>(OK, message, value);
    }

    /// <summary>
    /// NO
    /// </summary>
    /// <returns></returns>
    public static <T> HttpResult<T> no() {
        return no(null);
    }

    /// <summary>
    /// NO
    /// </summary>
    /// <param name="msg">提示信息</param>
    /// <returns></returns>
    public static <T> HttpResult<T> no(String message) {
        return no(message, null);
    }

    /// <summary>
    /// NO
    /// </summary>
    /// <param name="msg">提示信息</param>
    /// <param name="data">返回值</param>
    /// <returns></returns>
    public static <T> HttpResult<T> no(String message, T value) {
        return new HttpResult<>(NO, message, value);
    }

    /// <summary>
    /// ERROR
    /// </summary>
    /// <param name="msg">提示信息</param>
    /// <returns></returns>
    public static <T> HttpResult<T> error(String message) {
        return error(message, null);
    }

    /// <summary>
    /// ERROR
    /// </summary>
    /// <param name="msg">提示信息</param>
    /// <param name="data">返回值</param>
    /// <returns></returns>
    public static <T> HttpResult<T> error(String message, T value) {
        return new HttpResult<>(NO, message, value);
    }
}
