package com.lance.spike.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;
import java.util.Map;
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
