package com.lance.spike.viewVO;

/**
 * @ClassName UserVO
 * @Description TODO 视图层-返回前端的视图对象
 * @Author 陆逸飞
 * @Date 2018-12-19 15:42
 * @Version v1.0
 */
public class UserVO {
    private String name;
    private String telphone;
    private Byte gender;
    private Integer age;

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}

