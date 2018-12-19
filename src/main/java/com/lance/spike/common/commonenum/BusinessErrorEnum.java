package com.lance.spike.common.commonenum;

import com.lance.spike.error.ICommonError;

/**
 * @ClassName CommonErrorEnum
 * @Description TODO
 * @Author 陆逸飞
 * @Date 2018-12-19 16:36
 * @Version v1.0
 */
public enum BusinessErrorEnum implements ICommonError{
    // 通用错误
    DEFAULT_ERROR("000005", "系统错误，请稍后再试。"),
    UNKNOW_ERROR("000001", "未知错误"),
    // 10000开头 为用户信息相关错误
    USER_NOT_EXIST("100001", "用户不存在"),
    ;

    private BusinessErrorEnum(String resCode, String msg) {
        this.resCode = resCode;
        this.msg = msg;
    }

    private String resCode;
    private String msg;

    @Override
    public String getResCode() {
        return this.resCode;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }

    @Override
    public ICommonError setMsg(String msg) {
        this.msg = msg;
        return this;
    }
}
