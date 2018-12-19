package com.lance.spike.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * @ClassName UserModel
 * @Description TODO 业务层-会员信息模型
 * @Author 陆逸飞
 * @Date 2018-12-19 14:53
 * @Version v1.0
 */
public class UserModel implements Serializable {
    private Long id;
    private String name;
    private String telphone;
    private Byte gender;
    private Byte channel;
    private String thirdPartyId;
    private Integer age;
    private Byte registType;
    private String encrptPwd;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public Byte getGender() {
        return gender;
    }

    public void setGender(Byte gender) {
        this.gender = gender;
    }

    public Byte getChannel() {
        return channel;
    }

    public void setChannel(Byte channel) {
        this.channel = channel;
    }

    public String getThirdPartyId() {
        return thirdPartyId;
    }

    public void setThirdPartyId(String thirdPartyId) {
        this.thirdPartyId = thirdPartyId;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Byte getRegistType() {
        return registType;
    }

    public void setRegistType(Byte registType) {
        this.registType = registType;
    }

    public String getEncrptPwd() {
        return encrptPwd;
    }

    public void setEncrptPwd(String encrptPwd) {
        this.encrptPwd = encrptPwd;
    }
}

