package com.lance.spike.handler;

import com.lance.spike.common.commonenum.BusinessErrorEnum;
import com.lance.spike.common.vo.ReturnDTO;
import com.lance.spike.controller.SpikeController;
import com.lance.spike.error.ICommonError;
import com.lance.spike.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @ClassName CommonExceptionHandler
 * @Description TODO
 * @Author 陆逸飞
 * @Date 2018-12-19 17:27
 * @Version v1.0
 */
@ControllerAdvice
public class CommonExceptionHandler {
    // 日志
    private static Logger logger = LoggerFactory.getLogger(SpikeController.class);

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ReturnDTO handler(Exception ex) {
        logger.info("捕获异常");
        ReturnDTO dto = new ReturnDTO();
        if (ex instanceof BusinessException) {
            BusinessException businessException = (BusinessException) ex;
            dto.setResCode(businessException.getResCode());
            dto.setMsg(businessException.getMsg());
        } else {
            dto.setResCode(BusinessErrorEnum.UNKNOW_ERROR.getResCode());
            dto.setMsg(BusinessErrorEnum.UNKNOW_ERROR.getMsg());
        }
        return dto;
    }
}

