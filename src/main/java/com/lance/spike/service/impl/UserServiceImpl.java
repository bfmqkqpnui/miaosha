package com.lance.spike.service.impl;

import com.lance.spike.dao.UserDOMapper;
import com.lance.spike.dao.UserPassWordDOMapper;
import com.lance.spike.dataobject.UserDO;
import com.lance.spike.dataobject.UserPassWordDO;
import com.lance.spike.model.UserModel;
import com.lance.spike.service.UserDOService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author 陆逸飞
 * @Date 2018-12-19 11:08
 * @Version v1.0
 */
@Service
public class UserServiceImpl implements UserDOService {
    // 日志
    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDOMapper userDOMapper;

    @Autowired
    private UserPassWordDOMapper userPassWordDOMapper;

    @Override
    public void delete(int id) {

    }

    @Override
    public void insert(UserDO user) {

    }

    @Override
    public int update(UserDO user) {
        return 0;
    }

    @Override
    public UserModel selectById(String id) {
        long primaryKey = Long.parseLong(id);
        UserDO userDO = userDOMapper.selectByPrimaryKey(primaryKey);
        if (null == userDO) {
            return null;
        }
        UserPassWordDO userPassWordDO = userPassWordDOMapper.selectByUserId(userDO.getId());
        return convertByDataObject(userDO, userPassWordDO);
    }

    @Override
    public List<UserDO> selectAll(int pageNum, int pageSize) {
        return null;
    }

    private UserModel convertByDataObject(UserDO userDO, UserPassWordDO userPassWordDO) {
        if (null == userDO) {
            return null;
        }
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDO, userModel);
        if (null != userPassWordDO) {
            userModel.setEncrptPwd(userPassWordDO.getEncrptPwd());
        }
        return userModel;
    }
}

