package com.lance.spike.exception;

import com.lance.spike.error.ICommonError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName BusinessException
 * @Description TODO 统一异常处理
 * @Author 陆逸飞
 * @Date 2018-12-19 16:32
 * @Version v1.0
 */
public class BusinessException extends Exception implements ICommonError{

    private ICommonError commonError;

    // 直接接收
    public BusinessException(ICommonError commonError) {
        super();
        this.commonError = commonError;
    }

    public BusinessException(ICommonError commonError, String msg) {
        super();
        this.commonError = commonError;
        this.commonError.setMsg(msg);
    }

    @Override
    public String getResCode() {
        return this.commonError.getResCode();
    }

    @Override
    public String getMsg() {
        return this.commonError.getMsg();
    }

    @Override
    public ICommonError setMsg(String msg) {
        this.commonError.setMsg(msg);
        return this;
    }
}

