package com.lance.spike.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName CommonError
 * @Description TODO 错误类型
 * @Author 陆逸飞
 * @Date 2018-12-19 16:32
 * @Version v1.0
 */
public interface ICommonError {
    String getResCode();
    String getMsg();
    ICommonError setMsg(String msg);
}

