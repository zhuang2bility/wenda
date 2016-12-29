package com.zhuang.controller;

import com.zhuang.service.QAservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by szz on 16-9-21.
 */
@Controller
public class SettingController {
    @Autowired
    QAservice qAservice;
    @RequestMapping(path = {"/setting"},method = {RequestMethod.GET})
    @ResponseBody
    public String setting(HttpSession httpSession) {
        return "Setting OK"+qAservice.getMessage(1);
    }
}
