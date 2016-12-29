package com.zhuang.async;

import java.util.List;

/**
 * Created by shizhuangzhuang on 2016/12/7.
 */
public interface EventHandler {
    void doHandle(EventModel model);

    List<EventType> getSupportEventTypes();
}
