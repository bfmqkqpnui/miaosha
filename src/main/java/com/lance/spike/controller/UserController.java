package com.lance.spike.controller;

import com.lance.spike.common.commonenum.BusinessErrorEnum;
import com.lance.spike.common.utils.CommonUtils;
import com.lance.spike.common.utils.VerifyUtil;
import com.lance.spike.common.vo.ReturnDTO;
import com.lance.spike.exception.BusinessException;
import com.lance.spike.model.UserModel;
import com.lance.spike.service.UserDOService;
import com.lance.spike.tools.RedisManager;
import com.lance.spike.viewVO.UserVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


/**
 * @ClassName UserController
 * @Description TODO 会员控制层
 * @Author 陆逸飞
 * @Date 2018-12-19 14:39
 * @Version v1.0
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {
    // 日志
    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserDOService userDOService;

    @Autowired
    private RedisManager redisManager;

    /*
    * @Title: querySMS
    * @Description 获取手机短信验证码
    * @Author 陆逸飞
    * @Date 2018-12-20 9:57
    * @Param [requestParams]
    * @Return com.lance.spike.common.vo.ReturnDTO
    */
    @PostMapping("/querySMS")
    public ReturnDTO querySMS(@RequestBody Map<String, Object> requestParams) throws BusinessException {
        logger.info("获取手机短信验证码");
        long beginTime = System.currentTimeMillis();
        ReturnDTO dto = new ReturnDTO();
        if (CommonUtils.isExist(requestParams)) {
            String mobile = requestParams.get("phone") == null ? "" : requestParams.get("phone").toString();
            if (StringUtils.isNotBlank(mobile) && CommonUtils.isMobile(mobile)) {
                String sms = CommonUtils.createSMSCodeSecondMethod(7);
                logger.info("手机短信验证码：", sms);
                dto.setResCode("00100000");
                redisManager.setValue(mobile, sms, 2);
                dto.setObj(sms);
            } else {
                throw new BusinessException(BusinessErrorEnum.NOT_PHONENUM);
            }
        } else {
            throw new BusinessException(BusinessErrorEnum.UNKNOW_ERROR);
        }
        logger.info("完成获取手机短信验证码，总耗时[" + (System.currentTimeMillis() - beginTime) + "]毫秒");
        return dto;
    }

    /*
    * @Title: checkSMS
    * @Description 校验短信验证码
    * @Author 陆逸飞
    * @Date 2018-12-20 14:32
    * @Param [requestParams]
    * @Return com.lance.spike.common.vo.ReturnDTO
    */
    @PostMapping("/checkSMS")
    public ReturnDTO checkSMS(@RequestBody Map<String, Object> requestParams) throws BusinessException {
        logger.info("校验短信验证码");
        long beginTime = System.currentTimeMillis();
        ReturnDTO dto = new ReturnDTO();
        if (CommonUtils.isExist(requestParams)) {
            String mobile = requestParams.get("phone") == null ? "" : requestParams.get("phone").toString();
            String sms = requestParams.get("sms") == null ? "" : requestParams.get("sms").toString();
            if (StringUtils.isNotBlank(mobile) && CommonUtils.isMobile(mobile)) {
                if (checkSMS(mobile, sms)) {
                    dto.setResCode("00100000");
                } else {
                    throw new BusinessException(BusinessErrorEnum.UNEQUAL_SMS);
                }
            } else {
                throw new BusinessException(BusinessErrorEnum.NOT_PHONENUM);
            }
        } else {
            throw new BusinessException(BusinessErrorEnum.UNKNOW_ERROR);
        }
        logger.info("完成校验短信验证码，总耗时[" + (System.currentTimeMillis() - beginTime) + "]毫秒");
        return dto;
    }

    @GetMapping("/getVerifyCode")
    public ReturnDTO createVerifyCode(HttpServletResponse response) throws BusinessException {
        logger.info("获取图形验证码");
        long beginTime = System.currentTimeMillis();
        ReturnDTO dto = new ReturnDTO();
        String verifyCode = CommonUtils.createVerifyCode(4);
        if (StringUtils.isNotBlank(verifyCode)) {
            logger.info("图形验证码为：" + verifyCode);
            // 将生成的随机验证码存放到redis中
            String uuId = UUID.randomUUID().toString();
            try {
                redisManager.setValue(uuId, verifyCode, 20);
            } catch (Exception e) {
                logger.info("随机图形验证码存储失败：", e);
            }
            ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
            try {
                // 设置相应类型,告诉浏览器输出的内容为图片
                response.setContentType("image/jpeg");
                //设置响应头信息，告诉浏览器不要缓存此内容
                response.setHeader("Pragma", "No-cache");
                response.setHeader("Cache-Control", "no-cache");
                response.setDateHeader("Expire", 0);
                //输出验证码图片方法
                BufferedImage image = VerifyUtil.getRandcode(verifyCode, 100, 46, 36);
                ImageIO.write(image, "JPEG", jpegOutputStream);
                logger.info("完成图形验证码，总耗时[" + (System.currentTimeMillis() - beginTime) + "]毫秒");
                String baseUrl = Base64.getEncoder().encodeToString(jpegOutputStream.toByteArray());
                dto.setResCode("00100000");
                Map<String, Object> result = new HashMap<String, Object>();
                result.put("verifyCodeKey", uuId);
                result.put("imgBasic64", baseUrl);
                dto.setObj(result);
            } catch (Exception e) {
                logger.error("生成验证码图片失败>>>> ", e);
            }
        } else {
            throw new BusinessException(BusinessErrorEnum.DEFAULT_ERROR);
        }
        return dto;
    }

    /*
    * @Title: checkVerifyCode
    * @Description 校验图片验证码
    * @Author 陆逸飞
    * @Date 2018-12-28 17:10
    * @Param [requestParams]
    * @Return com.lance.spike.common.vo.ReturnDTO
    */
    @PostMapping(value = "/checkVerifyCode")
    public ReturnDTO checkVerifyCode(@RequestBody Map<String, Object> requestParams) throws BusinessException {
        logger.info("开始校验图片验证码");
        long beginTime = System.currentTimeMillis();
        if (CommonUtils.isExist(requestParams)) {
            String uuid = requestParams.get("id") == null ? "" : requestParams.get("id").toString();
            String verifyCode = requestParams.get("verifyCode") == null ? "" : requestParams.get("verifyCode").toString();
            if (StringUtils.isBlank(uuid) || StringUtils.isBlank(verifyCode)) {
                throw new BusinessException(BusinessErrorEnum.UNEQUAL_VERIFYCODE);
            }
            try {
                if (checkSMS(uuid, verifyCode)) {
                    ReturnDTO dto = new ReturnDTO();
                    dto.setResCode("00100000");
                    return dto;
                }
            } catch (Exception e) {
                throw new BusinessException(BusinessErrorEnum.UNEQUAL_VERIFYCODE);
            }
        }
        logger.info("完成校验图片验证码，总耗时[" + (System.currentTimeMillis() - beginTime) + "]");
        return null;
    }

    // 通过读取redis校验短信验证码是否正确
    private boolean checkSMS(String mobile, String sms) {
        boolean smsIsOk = false;
        if (StringUtils.isNotBlank(mobile) && StringUtils.isNotBlank(sms)) {
            String originSms = redisManager.getValue(mobile);
            if (StringUtils.isNotBlank(originSms)) {
                if (StringUtils.equalsIgnoreCase(originSms, sms)) {
                    smsIsOk = true;
                }
            }
        }
        return smsIsOk;
    }

    /*
    * @Title: getUser
    * @Description 获取会员信息
    * @Author 陆逸飞
    * @Date 2018-12-19 14:47
    * @Param [requestParams]
    * @Return com.lance.spike.common.vo.ReturnDTO
    */
    @PostMapping("/getUser")
    public ReturnDTO getUser(@RequestBody Map<String, Object> requestParams) throws BusinessException {
        logger.info("获取会员信息");
        long beginTime = System.currentTimeMillis();
        ReturnDTO dto = new ReturnDTO();
        if (CommonUtils.isExist(requestParams)) {
            String userId = requestParams.get("id") == null ? "" : requestParams.get("id").toString();
            if (StringUtils.isNotBlank(userId)) {
                UserModel userModel = userDOService.selectById(userId);
                if (null == userModel) {
                    throw new BusinessException(BusinessErrorEnum.USER_NOT_EXIST);
                }
                UserVO userVO = convertByModel(userModel);
                if (null == userVO) {
                    throw new BusinessException(BusinessErrorEnum.DEFAULT_ERROR);
                }
                dto.setResCode("00100000");
                dto.setObj(userVO);
            }
        }
        logger.info("完成获取会员信息，总耗时[" + (System.currentTimeMillis() - beginTime) + "]毫秒");
        return dto;
    }

    /*
    * @Title: login
    * @Description 用户登录
    * @Author 陆逸飞
    * @Date 2019-01-18 16:29
    * @Param [requestParams]
    * @Return com.lance.spike.common.vo.ReturnDTO
    */
    @PostMapping(value = "/login")
    public ReturnDTO login(@RequestBody Map<String, Object> requestParams) throws BusinessException {
        logger.info("登录");
        long beginTime = System.currentTimeMillis();
        ReturnDTO dto = new ReturnDTO();
        if (CommonUtils.isExist(requestParams)) {
            String name = requestParams.get("name") == null ? "" : requestParams.get("name").toString();
            String pwd = requestParams.get("pwd") == null ? "" : requestParams.get("pwd").toString();
            String identifyCode = requestParams.get("identifyCode") == null ? "" : requestParams.get("identifyCode").toString();
            String identifyKey = requestParams.get("identifyKey") == null ? "" : requestParams.get("identifyKey").toString();
            // 先校验图形验证码是否正确
            if (checkLoginParams(identifyCode, identifyKey, name, pwd)) {
                String redisCode = redisManager.getValue(identifyKey);
                if (StringUtils.isNotBlank(redisCode)) {
                    if (!StringUtils.equalsIgnoreCase(redisCode, identifyCode)) {
                        throw new BusinessException(BusinessErrorEnum.UNEQUAL_VERIFYCODE);
                    }
                    // 连接数据库查询登录是否可行
                } else {
                    throw new BusinessException(BusinessErrorEnum.DEFAULT_ERROR);
                }
            }
        }
        logger.info("完成 登录，总耗时[" + (System.currentTimeMillis() - beginTime) + "]毫秒");
        return dto;
    }

    /*
    * @Title: checkLoginParams
    * @Description 非空校验
    * @Author 陆逸飞
    * @Date 2019-01-22 20:11
    * @Param [identifyCode, identifyKey, name, pwd]
    * @Return java.lang.Boolean
    */
    private Boolean checkLoginParams(String identifyCode, String identifyKey, String name, String pwd) throws BusinessException {
        Boolean flag = true;
        if (StringUtils.isBlank(identifyCode) || StringUtils.isBlank(identifyKey)) {
            throw new BusinessException(BusinessErrorEnum.VERIFYCODE_NOT_EXIST);
        }
        if (StringUtils.isBlank(name)) {
            throw new BusinessException(BusinessErrorEnum.NAME_NOT_EXIST);
        }
        if (StringUtils.isBlank(pwd)) {
            throw new BusinessException(BusinessErrorEnum.PASSWORD_NOT_EXIST);
        }
        return flag;
    }

    /*
    * @Title: convertByModel
    * @Description 组装视图层数据
    * @Author 陆逸飞
    * @Date 2018-12-20 9:55
    * @Param [userModel]
    * @Return com.lance.spike.viewVO.UserVO
    */
    private UserVO convertByModel(UserModel userModel) {
        if (null == userModel) {
            return null;
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userModel, userVO);
        return userVO;
    }
}

