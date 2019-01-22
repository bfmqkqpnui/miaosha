package com.lance.spike.controller;

import com.lance.spike.common.utils.CommonUtils;
import com.lance.spike.common.vo.ReturnDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.awt.SunHints;

import java.util.Map;

/**
 * @ClassName LoginController
 * @Description TODO 登录相关
 * @Author 陆逸飞
 * @Date 2019-01-18 16:02
 * @Version v1.0
 */
@RestController
@RequestMapping(value = "/login")
public class LoginController {
    // 日志
    private static Logger logger = LoggerFactory.getLogger(LoginController.class);
}

