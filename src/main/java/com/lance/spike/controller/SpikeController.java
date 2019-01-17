package com.lance.spike.controller;

import com.lance.spike.common.vo.ReturnDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName SpikeController
 * @Description TODO
 * @Author 陆逸飞
 * @Date 2018-12-19 10:01
 * @Version v1.0
 */
@RestController
@RequestMapping(value = "/spike")
public class SpikeController {
    // 日志
    private static Logger logger = LoggerFactory.getLogger(SpikeController.class);

    @GetMapping(value = "/home")
    public ReturnDTO spikeHome() {
        logger.info("秒杀首页");
        long beginTime = System.currentTimeMillis();
        ReturnDTO dto = new ReturnDTO();
        dto.setResCode("00100005");
        dto.setMsg("系统异常，请稍候再试。");
        logger.info("完成秒杀首页，总耗时[" + (System.currentTimeMillis() - beginTime) + "]毫秒");
        return dto;
    }
}

