package press.wein.home.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * 前端返回实体包装类
 *
 * @author wangmingxiang
 * @since 2015-12-04
 */
public class ResponseUtils {

    private ResponseUtils() {
        super();
    }

    /**
     * 成功
     *
     * @return 返回实体类
     */
    public static ResponseEntity<Object> success() {
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    /**
     * 成功
     *
     * @param t 成功返回的数据
     * @return 返回实体类
     */
    public static <T> ResponseEntity<T> success(T t) {
        return new ResponseEntity<T>(t, HttpStatus.OK);
    }

    /**
     * 成功
     *
     * @param message 成功返回的数据
     * @return 返回实体类
     */
    public static ResponseEntity<Object> success(String message) {
        return new ResponseEntity<Object>(initMessage(message), HttpStatus.OK);
    }

    /**
     * 成功特殊返回
     *
     * @param key
     * @param value
     * @return
     */
    public static ResponseEntity<Object> success(String key, Object value) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(key, value);
        return new ResponseEntity<Object>(map, HttpStatus.OK);
    }

    /**
     * 业务异常失败
     *
     * @param message 失败返回的消息
     * @return 返回实体类
     */
    public static ResponseEntity<Object> fail(String message) {
        return new ResponseEntity<Object>(initMessage(message), HttpStatus.BAD_REQUEST);
    }

    /**
     * 系统异常失败
     *
     * @param message 失败返回消息
     * @return 返回实体类
     */
    public static ResponseEntity<Object> error(String message) {
        return new ResponseEntity<Object>(initMessage(message), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static ResponseEntity<Object> errorHasStatus(String message) {
        Map<String, Object> map = initMessage(message);
        map.put("status", "error");
        return new ResponseEntity<Object>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * 系统异常失败
     *
     * @return 返回实体类
     */
    public static ResponseEntity<Object> error() {
        Map<String, Object> map = initMessage("系统异常，请稍后再试");
        return new ResponseEntity<Object>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static ResponseEntity<Object> errorToJson(String json) {
        return new ResponseEntity<Object>(json, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static ResponseEntity<Object> successToJson(String json) {
        return new ResponseEntity<Object>(json, HttpStatus.OK);
    }

    /**
     * 权限不足
     *
     * @return 返回实体类
     */
    public static ResponseEntity<Object> forbidden() {
        Map<String, Object> map = initMessage("权限不足，请联系管理员");
        return new ResponseEntity<Object>(map, HttpStatus.FORBIDDEN);
    }

    /**
     * 权限不足
     *
     * @param message 权限不足返回消息
     * @return 返回实体类
     */
    public static ResponseEntity<Object> forbidden(String message) {
        return new ResponseEntity<Object>(initMessage(message), HttpStatus.FORBIDDEN);
    }

    /**
     * 未登录
     *
     * @return 返回实体类
     */
    public static ResponseEntity<Object> unauthorized() {
        Map<String, Object> map = initMessage("找不到登录记录，请重新登录");
        return new ResponseEntity<Object>(map, HttpStatus.UNAUTHORIZED);
    }

    /**
     * 未登录
     *
     * @param message 未登录返回消息
     * @return 返回实体类
     */
    public static ResponseEntity<Object> unauthorized(String message) {
        return new ResponseEntity<Object>(initMessage(message), HttpStatus.UNAUTHORIZED);
    }

    public static ResponseEntity<Object> not_found(String message) {
        Map<String, Object> map = initMessage(message);
        return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
    }

    private static Map<String, Object> initMessage(String message) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("message", message);
        return map;
    }

}