package com.lance.spike.service;

import com.lance.spike.dataobject.UserDO;
import com.lance.spike.model.UserModel;

import java.util.List;

/**
 * @ClassName service
 * @Description TODO
 * @Author 陆逸飞
 * @Date 2018-12-19 11:02
 * @Version v1.0
 */
public interface UserDOService {
    /**
     * 删除
     */
    void delete(int id);

    /**
     * 增加
     */
    void insert(UserDO user);

    /**
     * 更新
     */
    int update(UserDO user);

    /**
     * 查询单个
     */
    UserModel selectById(String id);

    /**
     * 查询全部列表
     */
    List<UserDO> selectAll(int pageNum, int pageSize);
}
