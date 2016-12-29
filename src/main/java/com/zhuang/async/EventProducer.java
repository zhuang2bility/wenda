package com.zhuang.async;

import com.alibaba.fastjson.JSONObject;
import com.zhuang.util.JedisAdapter;
import com.zhuang.util.RedisKeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by shizhuangzhuang on 2016/12/7.
 */
@Service
public class EventProducer {
    @Autowired
    JedisAdapter jedisAdapter;

    public boolean fireEvent(EventModel eventModel){
        try{
            String json= JSONObject.toJSONString(eventModel);
            String key= RedisKeyUtil.getEventQueueKey();
            jedisAdapter.lpush(key,json);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
