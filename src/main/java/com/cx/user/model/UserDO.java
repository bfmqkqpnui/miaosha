package com.cx.user.model;

public class UserDO {
    private Long id;

    private String name;

    private String telphone;

    private Byte gender;

    private Byte channel;

    private String thirdPartyId;

    private Integer age;

    private Byte registType;

    public UserDO(Long id, String name, String telphone, Byte gender, Byte channel, String thirdPartyId, Integer age, Byte registType) {
        this.id = id;
        this.name = name;
        this.telphone = telphone;
        this.gender = gender;
        this.channel = channel;
        this.thirdPartyId = thirdPartyId;
        this.age = age;
        this.registType = registType;
    }

    public UserDO() {
        super();
    }

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
        this.name = name == null ? null : name.trim();
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone == null ? null : telphone.trim();
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
        this.thirdPartyId = thirdPartyId == null ? null : thirdPartyId.trim();
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
}