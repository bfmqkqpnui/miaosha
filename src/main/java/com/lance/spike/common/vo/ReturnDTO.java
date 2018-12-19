package com.lance.spike.common.vo;

import java.io.Serializable;

/**
 * @ClassName ReturnDTO
 * @Description TODO
 * @Author 陆逸飞
 * @Date 2018-12-19 10:03
 * @Version v1.0
 */
public class ReturnDTO implements Serializable {
    // 返回码
    private String resCode;
    // 如果成功的话,返回的对象
    private Object obj;
    // 错误返回时的错误描述;
    private String msg;

    public String getResCode() {
        return resCode;
    }

    public void setResCode(String resCode) {
        this.resCode = resCode;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

