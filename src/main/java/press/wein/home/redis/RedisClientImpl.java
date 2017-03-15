package press.wein.home.redis;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * redisclient实现
 *
 * @author oukailiang
 * @create 2017-02-23 下午8:38
 */
@Service(value = "redisClient")
public class RedisClientImpl implements RedisClient {
    private Logger LOG = LoggerFactory.getLogger(RedisClientImpl.class);

    @Autowired
    private ShardedJedisPool pool;

    @Override
    public ShardedJedis getClient() {
        if (pool == null) {
            return null;
        } else {
            return pool.getResource();
        }
    }

    @Override
    public boolean hset(String key, String field, String value, int expiredMin) {
        ShardedJedis client = null;
        try {
            client = pool.getResource();
            client.hset(key, field, value);
            if (expiredMin > 0) {
                client.expire(key, 60 * expiredMin);
            }
        } catch (Exception e) {
            LOG.error("redis hset error:", e);
            return false;
        } finally {
            if (null != client) {
                client.close();
            }
        }
        return false;
    }

    @Override
    public boolean hmset(String key, Map<String, String> hash, int expiredMin) {
        ShardedJedis client = null;
        try {
            client = pool.getResource();
            client.hmset(key, hash);
            if (expiredMin > 0) {
                client.expire(key, 60 * expiredMin);
            }
        } catch (Exception e) {
            LOG.error("redis hmset error:", e);
            return false;
        } finally {
            if (null != client) {
                client.close();
            }
        }
        return false;
    }

    @Override
    public String hget(String key, String field) {
        ShardedJedis client = null;
        try {
            client = pool.getResource();
            return client.hget(key, field);
        } catch (Exception e) {
            LOG.error("redis hget error:", e);
            return "";
        } finally {
            if (null != client) {
                client.close();
            }
        }
    }

    @Override
    public List<String> hmget(String key, String... fields) {
        ShardedJedis client = null;
        try {
            client = pool.getResource();
            return client.hmget(key, fields);
        } catch (Exception e) {
            LOG.error("redis hmget error:", e);
            return Collections.emptyList();
        } finally {
            if (null != client) {
                client.close();
            }
        }
    }

    @Override
    public Map<String, String> hgetAll(String key) {
        ShardedJedis client = null;
        try {
            client = pool.getResource();
            return client.hgetAll(key);
        } catch (Exception e) {
            LOG.error("redis hgetAll error:", e);
            return null;
        } finally {
            if (null != client) {
                client.close();
            }
        }
    }

    @Override
    public boolean hincrBy(String key, String field, long value, int expiredMin) {
        ShardedJedis client = null;
        try {
            client = pool.getResource();
            Long incrValue = client.hincrBy(key, field, value);
            if (incrValue.equals(value) && expiredMin > 0) {
                client.expire(key, 60 * expiredMin);
            }
        } catch (Exception e) {
            LOG.error("redis hincrBy error:", e);
            return false;
        } finally {
            if (null != client) {
                client.close();
            }
        }
        return false;
    }

    @Override
    public boolean hincrByFloat(String key, String field, double value, int expiredMin) {
        ShardedJedis client = null;
        try {
            client = pool.getResource();
            Double incrValue = client.hincrByFloat(key, field, value);
            if (incrValue.equals(value) && expiredMin > 0) {
                client.expire(key, 60 * expiredMin);
            }
        } catch (Exception e) {
            LOG.error("redis hincrByFloat error:", e);
            return false;
        } finally {
            if (null != client) {
                client.close();
            }
        }
        return false;
    }

    @Override
    public boolean hdel(String key, String... fields) {
        ShardedJedis client = null;
        try {
            client = pool.getResource();
            client.hdel(key, fields);
        } catch (Exception e) {
            LOG.error("redis hdel error:", e);
            return false;
        } finally {
            if (null != client) {
                client.close();
            }
        }
        return false;
    }

    @Override
    public boolean hexists(String key, String field) {
        ShardedJedis client = null;
        try {
            client = pool.getResource();
            return client.hexists(key, field);
        } catch (Exception e) {
            LOG.error("redis hexists error:", e);
            return false;
        } finally {
            if (null != client) {
                client.close();
            }
        }
    }

    /**
     * 向缓存中设置字符串内容
     *
     * @param key
     * @param value
     * @param expiredMin 过期时间，单位分钟
     * @return
     */
    @Override
    public boolean set(String key, String value, int expiredMin) {

        ShardedJedis client = null;
        try {
            client = pool.getResource();
            client.set(key, value);
            if (expiredMin > 0) {
                client.expire(key, 60 * expiredMin);
            }
        } catch (Exception e) {
            LOG.error("redis set value error:", e);
            return false;
        } finally {
            if (null != client) {
                client.close();
            }
        }
        return false;
    }

    /**
     * 向缓存中设置对象
     *
     * @param key
     * @param value
     * @param expiredMin 过期时间，单位分钟
     * @return
     */
    @Override
    public boolean set(String key, Object value, int expiredMin) {
        ShardedJedis client = null;
        try {
            client = pool.getResource();
            client.set(key, JSON.toJSONString(value));
            if (expiredMin > 0) {
                client.expire(key, 60 * expiredMin);
            }
        } catch (Exception e) {
            LOG.error("redis set object error:", e);
            return false;
        } finally {
            if (null != client) {
                client.close();
            }
        }
        return false;
    }

    /**
     * 删除缓存中得对象，根据key
     *
     * @param key
     * @return
     */
    @Override
    public boolean delete(String key) {

        ShardedJedis client = null;
        try {
            client = pool.getResource();
            client.del(key);
            return true;
        } catch (Exception e) {
            LOG.error("redis del error:", e);
            return false;
        } finally {
            if (null != client) {
                client.close();
            }
        }
    }

    /**
     * 根据key 获取内容
     *
     * @param key
     * @return
     */
    @Override
    public String get(String key) {
        ShardedJedis client = null;
        try {
            client = pool.getResource();
            String value = client.get(key);
            return value;
        } catch (Exception e) {
            LOG.error("redis get error:", e);
            return null;
        } finally {
            if (null != client) {
                client.close();
            }
        }
    }

    /**
     * 根据key 获取对象
     *
     * @param key
     * @return
     */
    @Override
    public <T> T get(String key, Class<T> clazz) {
        ShardedJedis client = null;

        try {
            client = pool.getResource();
            String value = client.get(key);
            return JSON.parseObject(value, clazz);
        } catch (Exception e) {
            LOG.error("redis get class error:", e);
            return null;
        } finally {
            if (null != client) {
                client.close();
            }
        }
    }

    @Override
    public <T> T getHasException(String key, Class<T> clazz) throws Exception {
        ShardedJedis client = null;
        try {
            client = pool.getResource();
            String value = client.get(key);
            return JSON.parseObject(value, clazz);
        } finally {
            if (null != client) {
                client.close();
            }
        }
    }

    @Override
    public <T> List<T> getList(String key, Class<T> clazz) {
        ShardedJedis client = null;

        try {
            client = pool.getResource();
            String value = client.get(key);
            return JSON.parseArray(value, clazz);
        } catch (Exception e) {
            LOG.error("redis getList error:", e);
            return Collections.emptyList();
        } finally {
            if (null != client) {
                client.close();
            }
        }
    }

    @Override
    public Long incr(String key, int expiredMin) {
        ShardedJedis client = null;
        try {
            client = pool.getResource();
            Long value = client.incr(key);
            if (null != value && value == 1 && expiredMin > 0) {
                client.expire(key, 60 * expiredMin);
            }
            return value;
        } catch (Exception e) {
            LOG.error("redis incr error:", e);
            return null;
        } finally {
            if (null != client) {
                client.close();
            }
        }

    }

    @Override
    public Double incrByFloat(String key, double integer) {
        ShardedJedis client = null;
        Double d = null;
        try {
            client = pool.getResource();
            d = client.incrByFloat(key, integer);
        } catch (Exception e) {
            LOG.error("redis incrByFloat error:", e);
        } finally {
            if (null != client) {
                client.close();
            }
        }
        return d;
    }

    @Override
    public Double incrByFloat(String key, double value, int expiredMin) {
        ShardedJedis client = null;
        Double d = null;
        try {
            client = pool.getResource();
            d = client.incrByFloat(key, value);
            if (d != null && d.equals(value) && expiredMin > 0) {
                client.expire(key, 60 * expiredMin);
            }
        } catch (Exception e) {
            LOG.error("redis incrByFloat value error:", e);
        } finally {
            if (null != client) {
                client.close();
            }
        }
        return d;
    }

    @Override
    public Long incrBy(String key, long integer) {
        ShardedJedis client = null;
        try {
            client = pool.getResource();
            Long value = client.incrBy(key, integer);
            return value;
        } catch (Exception e) {
            LOG.error("redis incrBy error:", e);
            return null;
        } finally {
            if (null != client) {
                client.close();
            }
        }
    }

    @Override
    public <T> List<T> getMap(String key, Class<T> clazz) {
        ShardedJedis client = null;

        try {
            client = pool.getResource();
            String value = client.get(key);
            return JSON.parseArray(value, clazz);
        } catch (Exception e) {
            LOG.error("redis getMap error:", e);
            return Collections.emptyList();
        } finally {
            if (null != client) {
                client.close();
            }
        }
    }

    @Override
    public Long sadd(String key, String... members) {
        ShardedJedis client = null;
        try {
            client = pool.getResource();
            Long value = client.sadd(key, members);
            return value;
        } catch (Exception e) {
            LOG.error("redis sadd error:", e);
            return null;
        } finally {
            if (null != client) {
                client.close();
            }
        }
    }

    @Override
    public Set<String> smembers(String key) {
        ShardedJedis client = null;
        try {
            client = pool.getResource();
            Set<String> value = client.smembers(key);
            return value;
        } catch (Exception e) {
            LOG.error("redis smembers error:", e);
            return Collections.emptySet();
        } finally {
            if (null != client) {
                client.close();
            }
        }
    }


    @Override
    public Long expire(String key, int min) {
        ShardedJedis client = null;
        Long value = 0L;
        try {
            client = pool.getResource();
            if (min > 0) {
                value = client.expire(key, 60 * min);
            }
            return value;
        } catch (Exception e) {
            LOG.error("redis expire error:", e);
            return null;
        } finally {
            if (null != client) {
                client.close();
            }
        }
    }

    @Override
    public Long ttl(String key) {
        ShardedJedis client = null;
        Long value;
        try {
            client = pool.getResource();
            value = client.ttl(key);
            if (value <= 0) {
                value = 0l;
            }
            return value;
        } catch (Exception e) {
            LOG.error("redis ttl error:", e);
            return null;
        } finally {
            if (null != client) {
                client.close();
            }
        }
    }
}
