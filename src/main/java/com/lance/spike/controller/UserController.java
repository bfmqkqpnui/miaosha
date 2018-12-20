package com.lance.spike.controller;

import com.lance.spike.common.commonenum.BusinessErrorEnum;
import com.lance.spike.common.utils.CommonUtils;
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
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.JedisPool;

import java.util.Map;

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

