package com.lance.spike.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 常用工具类
 */
public class CommonUtils {

    /**
     * 数组是否存在,长度大于0
     *
     * @param obj
     * @return
     */
    public static boolean isExist(Object obj) {
        boolean flag = false;
        if (null != obj) {
            if (obj instanceof List) {
                List list = (List) obj;
                if (null != list && list.size() > 0) {
                    flag = true;
                }
            } else if (obj instanceof Map) {
                Map map = (Map) obj;
                if (null != map && map.size() > 0) {
                    flag = true;
                }
            }
        }
        return flag;
    }

    /**
     * @param bytes
     * @param <T>
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static <T> T byteToObject(byte bytes[]) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        return (T) ois.readObject();
    }

    /**
     * 校验是否为手机号
     *
     * @param mobile
     * @return
     */
    public static boolean isMobile(String mobile) {
        Pattern p = Pattern.compile("^1\\d{10}$");
        Matcher m = p.matcher(mobile);
        return m.matches();
    }

    /*
    * @Title: createSMSCode
    * @Description 生成短信验证码
    * @Author 陆逸飞
    * @Date 2018-12-20 10:01
    * @Param []
    * @Return java.lang.String
    */
    public static String createSMSCodeFirstMethod(int length) {
        String code = null;
        String baseCodeStr = "0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(baseCodeStr.charAt(random.nextInt(baseCodeStr.length())));
        }
        if (sb.length() == length) {
            code = sb.toString();
        }
        return code;
    }

    /*
    * @Title: createSMSCodeSecondMethod
    * @Description 生成短信验证码
    * @Author 陆逸飞
    * @Date 2018-12-20 10:17
    * @Param []
    * @Return java.lang.String
    */
    public static String createSMSCodeSecondMethod(int length) {
        String code = null;
        Random random = new Random();
        StringBuilder max = new StringBuilder();
        StringBuilder min = new StringBuilder();
        for (int i = 0; i < length; i++) {
            max.append("9");
            min.append("0");
        }
        if (min.length() > 0) {
            min = min.replace(0,1,"1");
        }
        int randomInt = random.nextInt(Integer.parseInt(max.toString()));
        code = String.valueOf(randomInt);
        if (code.length() < length) {
            randomInt = Integer.parseInt(min.toString()) + Integer.parseInt(code);
            code = String.valueOf(randomInt);
        }
        return code;
    }

    /*
    * @Title: createVerifyCode
    * @Description 创建图片验证码
    * @Author 陆逸飞
    * @Date 2018-12-28 16:55
    * @Param [length]
    * @Return java.lang.String
    */
    public static String createVerifyCode(int length) {
        String code = null;
        String baseCodeStr = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(baseCodeStr.charAt(random.nextInt(baseCodeStr.length())));
        }
        if (sb.length() == length) {
            code = sb.toString();
        }
        return code;
    }

    /*
    * @Title: getUrlByParamWithGetMethod
    * @Description 拼接get请求参数
    * @Author 陆逸飞
    * @Date 2018-05-04 15:54
    * @Param [params, url]
    * @Return java.lang.String
    */
    public static String getUrlByParamWithGetMethod(Map<String, ? extends Object> params, String url) {
        String path = null;
        if (CommonUtils.isExist(params) && StringUtils.isNotBlank(url)) {
            StringBuilder requestPath = new StringBuilder(url);
            for (String key : params.keySet()) {
                if (requestPath.indexOf("?") > 0) {
                    requestPath.append("&").append(key).append("=").append(params.get(key));
                } else {
                    requestPath.append("?").append(key).append("=").append(params.get(key));
                }
            }
            if (null != requestPath && requestPath.length() > 0) {
                path = requestPath.toString();
            }
        }
        return path;
    }
}
