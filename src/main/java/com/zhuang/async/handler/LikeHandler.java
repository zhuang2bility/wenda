package com.zhuang.async.handler;

import com.zhuang.async.EventHandler;
import com.zhuang.async.EventModel;
import com.zhuang.async.EventType;
import com.zhuang.model.Message;
import com.zhuang.model.User;
import com.zhuang.service.MessageService;
import com.zhuang.service.UserService;
import com.zhuang.util.WendaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by shizhuangzhuang on 2016/12/7.
 */
@Component
public class LikeHandler implements EventHandler{
    @Autowired
    MessageService messageService;

    @Autowired
    UserService userService;


    @Override
    public void doHandle(EventModel model) {
        Message message=new Message();
        message.setFromId(WendaUtil.SYSTEM_USERID);
        message.setToId(model.getEntityOwnerId());
        message.setCreatedDate(new Date());
        User user=userService.getUser(model.getActorId());
        message.setContent("用户"+user.getName()+"赞了你的评论，http://127.0.0.1:8080/question/"
        +model.getExt("questionId"));
        messageService.addMessage(message);
    }

    @Override
    public List<EventType> getSupportEventTypes() {
        return Arrays.asList(EventType.LIKE);
    }
}
