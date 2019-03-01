package com.lance.spike.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName AlipayConfig
 * @Description TODO 支付宝基础配置类
 * @Author 陆逸飞
 * @Date 2019-02-27 16:17
 * @Version v1.0
 */
public class AlipayConfig {
    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String APP_ID = "2016091000482028";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String MERCHANT_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC3vBJZWEdUco822cAvmiuZvuNdQifDLbxZ2+6jgGCYLTWkYa5OrSnisSrv9R0yMr0GfXp3jfi7zOTi/jL3Iwk83r0xWGUSMcYKZvQBfn41htQRQZ9IlgB3faW3WDLnDavEleNwLuug/11w/E+PHMK+7nJjAUWu4xXIcuZHHJBnpjMkJ3qceeomplTPygrmEsT+I586c0n2eXH091meH3QS2cANaWGmQoKqtUuzD7F61Os03M2+SmDzieu7EYhtlg80jzXjFvoasI1UcAoQMgJIcbo0pyUDQ/ArRmpiPRJyd7+UgqjkAGPMFWfV0jWTwc428Iq6qgcBWrIKDmmtd0vbAgMBAAECggEACWW9KoSxYUhautt4UPb5s+tnhaWUkw8LyyzMj+BJEH9/duDoIW4J7mZRY+SNkbu2n1ZbpTHFvxVDv7hwsuM/3bUmyBdVVBDnqDCL2me13NcAkcFhfDzv2Sy8BRylvdudJXjlIR/FPX7SMK03BILcExfvEdFG1yMWQiJeVNTJLgPj86gRRbwAWXhPsaovoF3CwFhGT1/1ZYiX/BVOUNBQiSkKSB6JBNAuwLPmtU2nFNjGUm/aG6z3ib3iqJUaQxo8yh0lSiW78byt6dUXLUXy4leBHpIleGVHWH1fxoC4SrwYcEfILNf83MYzodvU8URTGyGks9W0jS8NxkSyu+J7wQKBgQDjlLB4bVvCx5F418oaghPhxD2+mY2ifvlcdCAKpf5pZY9OiSYzmCGR3pGJdgYarkas+AUXJ3IChTJZ9TB17MMwCa22lGbf8BFi5oBOpLQ72NxYfVFJ+6Ikm6kRRZqG8KYS+a6zYefwLXZZ+Mxp7Siz4YLtClnuam45Hwy8pEV6fQKBgQDOrbTzsD4SLicZhtM9LdFv5VoQ7C3/BzvYDFnf6i5hUr7goE4XMXlCmdY1nxJrOoryOyYurr52W5k4E33gYycx7aKcsDapyW20A2WS6i7yZ3VYss8htY0KqYa4vUjSuk4STdPsmelGp41OAvG5iwHbccgNsL1uhFGrOQXgUEfXNwKBgFjYZmBNSSJsaLVFgQgCOiLMStKK/ckvSsg2KYAet4iL9ntRfZFs1cYtnqGMauull7/U/5+/bXngZ/zIhMe0y6qITMHXg6XvAcqbdAG/uQEYM0V0fvSkAPPLtSAiDSaa8iq8QOCU20hfZzHOuHat+FLpOgSyItO5xxgTsBRBCUK9AoGBAKaovkTPNo5UdyUHamln+/3eFQ2WVdTTkXc7JnIAFdxW7CKYQcBEHcMN6AUfO6wJ7ic8eSKluGIapOfqF9QMQHvrYvg0xC+PhQ3mu06H1smTkbWvooVNL+mMIF3yDLoYj4IFDkI5LjnSPIjOqPt0U1AQZUBc57QpsO2AMvPZ6MRrAoGAJ5mnFVTng8cpO1779pc/P282KlomJ/eOVWSUaMxByw1guoashSkOd2BSE0Z9xMZqW9J2T2MMnWyXv0ubTKz/WmiCCLdjVuMuooUOEIMy+1K56E47XUUl2tZewbX1oZIkjROkOsEk0djbRh1UzdtvtRTqn2U+Rh04e3zQG691cg0=";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAyrCb/E84SZePt87PZhpdY6H2OlyHIUTlClAneKk/tn6LfhaAMLYE8xHDi4ZdwcYi8kYhZ0dscz4FwVAdEgO3nN56U5lpC88SJYV7+fQn0n2hj6Dlp4z7pKUSCljt3YZDNYRh3NHviDDR3QTSadOqbRxoCxQ3PRCzGMN6sojWWVKmCxXkgyMNvSkFgWerPsv7imkr2a8gzOaYyDcbjco1P7W2H7IBtIuk5JF9DMkXp0cseuGFsVhXxUfQHiOgTJl9MB/XV+/V2S94Q8sCFzj5OvhAqkJAPQWMwuECfhqEl8LE9QRvjbxkne9VWeM3Ce3bzHO4cg8OAECYGptMWfz9wQIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String NOTIFY_URL = "http://工程公网访问地址/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String RETURN_URL = "http://工程公网访问地址/alipay.trade.page.pay-JAVA-UTF-8/return_url.jsp";

    // 签名方式
    public static String SIGN_TYPE = "RSA2";

    // 字符编码格式
    public static String CHARSET = "utf-8";

    // 支付宝网关
    public static String GATEWAYURL = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String LOG_PATH = "C:\\";
}

