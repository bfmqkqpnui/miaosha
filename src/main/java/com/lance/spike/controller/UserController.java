package com.lance.spike.controller;

import com.lance.spike.common.commonenum.BusinessErrorEnum;
import com.lance.spike.common.utils.CommonUtils;
import com.lance.spike.common.vo.ReturnDTO;
import com.lance.spike.exception.BusinessException;
import com.lance.spike.model.UserModel;
import com.lance.spike.service.UserDOService;
import com.lance.spike.viewVO.UserVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        dto.setResCode("00100005");
        dto.setMsg("系统异常，请稍候再试。");
        if (CommonUtils.isExist(requestParams)) {
            String userId = requestParams.get("id") == null ? "" : requestParams.get("id").toString();
            if (StringUtils.isNotBlank(userId)) {
                UserModel userModel = userDOService.selectById(userId);
                if (null == userModel) {
                    throw new BusinessException(BusinessErrorEnum.USER_NOT_EXIST);
                }
                UserVO userVO = convertByModel(userModel);
                if (null != userVO) {
                    dto.setResCode("00100000");
                    dto.setObj(userVO);
                    dto.setMsg("");
                }
            }
        }
        logger.info("完成获取会员信息，总耗时[" + (System.currentTimeMillis() - beginTime) + "]毫秒");
        return dto;
    }

    private UserVO convertByModel(UserModel userModel) {
        if (null == userModel) {
            return null;
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userModel, userVO);
        return userVO;
    }
}

