package com.zhuang.service;

import org.springframework.stereotype.Service;

/**
 * Created by szz on 16-9-21.
 */
@Service
public class QAservice {
    public String getMessage(int userId){
        return "Hello Message"+String.valueOf(userId);
    }
}
