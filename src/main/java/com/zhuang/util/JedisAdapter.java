package com.zhuang.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhuang.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * Created by shizhuangzhuang on 2016/12/6.
 */
@Service
public class JedisAdapter implements InitializingBean{

    private JedisPool pool;
    private static final Logger logger= LoggerFactory.getLogger(JedisAdapter.class);

    //TEST
    public static void print(int index, Object obj) {
        System.out.println(String.format("%d, %s", index, obj.toString()));
    }
    public static void main(String[] argv){
        Jedis jedis=new Jedis("redis://localhost:6379/9");
        jedis.flushDB();
        jedis.set("hello","world");

        String listName="list";
        jedis.del(listName);
        for(int i=0;i<10;++i){
            jedis.lpush(listName,"a"+String.valueOf(i));
        }
        print(1,jedis.lrange(listName,0,12));

        //hash

        String userKey="zmk";
        jedis.hset(userKey,"name","zmk");
        jedis.hset(userKey,"age","23");
        jedis.hset(userKey,"phone","13026150439");
        print(2,jedis.hget(userKey,"name"));
        print(3,jedis.hgetAll(userKey));

        User user = new User();
        user.setName("xx");
        user.setPassword("ppp");
        user.setHeadUrl("a.png");
        user.setSalt("salt");
        user.setId(1);
        print(4, JSONObject.toJSONString(user));
        jedis.set("user1", JSONObject.toJSONString(user));

        String value = jedis.get("user1");
        User user2 = JSON.parseObject(value, User.class);
        print(5, user2);

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        pool = new JedisPool("redis://localhost:6379/10");
    }
    public long sadd(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.sadd(key, value);
        } catch (Exception e) {
            logger.error("发生异常" + e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return 0;
    }
    public long srem(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.srem(key, value);
        } catch (Exception e) {
            logger.error("发生异常" + e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return 0;
    }

    public long scard(String key) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.scard(key);
        } catch (Exception e) {
            logger.error("发生异常" + e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return 0;
    }

    public boolean sismember(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.sismember(key, value);
        } catch (Exception e) {
            logger.error("发生异常" + e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return false;
    }

    public List<String> brpop(int timeout, String key) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.brpop(timeout, key);
        } catch (Exception e) {
            logger.error("发生异常" + e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return null;
    }
    public long lpush(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.lpush(key, value);
        } catch (Exception e) {
            logger.error("发生异常" + e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return 0;
    }
    public List<String> lrange(String key, int start, int end) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.lrange(key, start, end);
        } catch (Exception e) {
            logger.error("发生异常" + e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return null;
    }

    public long zadd(String key, double score, String value) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.zadd(key, score, value);
        } catch (Exception e) {
            logger.error("发生异常" + e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return 0;
    }

    public long zrem(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.zrem(key, value);
        } catch (Exception e) {
            logger.error("发生异常" + e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return 0;
    }

    public Jedis getJedis() {
        return pool.getResource();
    }

    public Transaction multi(Jedis jedis) {
        try {
            return jedis.multi();
        } catch (Exception e) {
            logger.error("发生异常" + e.getMessage());
        } finally {
        }
        return null;
    }

    public List<Object> exec(Transaction tx, Jedis jedis) {
        try {
            return tx.exec();
        } catch (Exception e) {
            logger.error("发生异常" + e.getMessage());
            tx.discard();
        } finally {
            if (tx != null) {
                try {
                    tx.close();
                } catch (IOException ioe) {
                    // ..
                }
            }

            if (jedis != null) {
                jedis.close();
            }
        }
        return null;
    }

    public Set<String> zrange(String key, int start, int end) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.zrange(key, start, end);
        } catch (Exception e) {
            logger.error("发生异常" + e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return null;
    }

    public Set<String> zrevrange(String key, int start, int end) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.zrevrange(key, start, end);
        } catch (Exception e) {
            logger.error("发生异常" + e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return null;
    }

    public long zcard(String key) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.zcard(key);
        } catch (Exception e) {
            logger.error("发生异常" + e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return 0;
    }

    public Double zscore(String key, String member) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.zscore(key, member);
        } catch (Exception e) {
            logger.error("发生异常" + e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return null;
    }
}
