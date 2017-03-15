package press.wein.home.redis;

import redis.clients.jedis.ShardedJedis;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author oukailiang
 * @create 2017-02-23 下午8:35
 */

public interface RedisClient {
    /**
     * 向缓存中设置字符串内容
     *
     * @param key
     * @param value
     * @param expiredMin 过期时间，单位分钟
     * @return
     */
    boolean set(String key, String value, int expiredMin);

    /**
     * 向缓存中设置对象
     *
     * @param key
     * @param value
     * @param expiredMin 过期时间，单位分钟
     * @return
     */
    boolean set(String key, Object value, int expiredMin);

    boolean delete(String key);

    String get(String key);

    <T> T get(String key, Class<T> clazz);

    /**
     * 根据key 获取对象 需要捕获异常
     *
     * @param key
     * @param clazz
     * @return
     * @throws Exception
     */
    <T> T getHasException(String key, Class<T> clazz) throws Exception;

    <T> List<T> getList(String key, Class<T> clazz);

    /**
     * 自增，第一次自增时，设置超期时间(使用默认自增值)
     *
     * @param key
     * @return
     */
    Long incr(String key, int expiredMin);

    /**
     * 自增byFloat
     *
     * @param key
     * @param integer
     * @return
     */
    Double incrByFloat(String key, double integer);

    /**
     * 自增byFloat
     *
     * @param key
     * @param value
     * @return
     */
    Double incrByFloat(String key, double value, int expiredMin);

    Long incrBy(String key, long integer);

    /**
     * @param key
     * @param field
     * @param value
     * @param expiredMin 过期时间，单位分钟，大于0才会设置
     * @return
     */
    boolean hset(String key, String field, String value, int expiredMin);

    /**
     * @param key
     * @param hash
     * @param expiredMin 过期时间,单位分钟，大于0才会设置
     * @return
     */
    boolean hmset(String key, Map<String, String> hash, int expiredMin);

    String hget(String key, String field);

    List<String> hmget(String key, String... fields);

    <T> List<T> getMap(String key, Class<T> clazz);

    Map<String, String> hgetAll(String key);

    boolean hincrBy(String key, String field, long value, int expiredMin);

    boolean hincrByFloat(String key, String field, double value, int expiredMin);

    boolean hdel(String key, String... fields);

    boolean hexists(String key, String field);

    Long sadd(String key, String... members);

    Set<String> smembers(String key);

    /**
     * @param key
     * @param min
     * @return
     */
    Long expire(String key, int min);

    /**
     * @param key
     * @return 秒
     */
    Long ttl(String key);

    ShardedJedis getClient();
}
