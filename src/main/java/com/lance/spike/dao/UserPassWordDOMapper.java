package com.lance.spike.dao;

import com.lance.spike.dataobject.UserPassWordDO;

public interface UserPassWordDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserPassWordDO record);

    int insertSelective(UserPassWordDO record);

    UserPassWordDO selectByPrimaryKey(Long id);
    UserPassWordDO selectByUserId (Long id);

    int updateByPrimaryKeySelective(UserPassWordDO record);

    int updateByPrimaryKey(UserPassWordDO record);
}