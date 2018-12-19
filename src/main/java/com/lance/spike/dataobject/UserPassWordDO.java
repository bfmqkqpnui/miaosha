package com.lance.spike.dataobject;

public class UserPassWordDO {
    private Long id;

    private String encrptPwd;

    private String userId;

    public UserPassWordDO(Long id, String encrptPwd, String userId) {
        this.id = id;
        this.encrptPwd = encrptPwd;
        this.userId = userId;
    }

    public UserPassWordDO() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEncrptPwd() {
        return encrptPwd;
    }

    public void setEncrptPwd(String encrptPwd) {
        this.encrptPwd = encrptPwd == null ? null : encrptPwd.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }
}