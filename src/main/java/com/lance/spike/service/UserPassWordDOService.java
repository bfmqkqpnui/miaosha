package com.lance.spike.service;

import com.lance.spike.dataobject.UserDO;
import com.lance.spike.dataobject.UserPassWordDO;

import java.util.List;

/**
 * @ClassName service
 * @Description TODO
 * @Author 陆逸飞
 * @Date 2018-12-19 11:02
 * @Version v1.0
 */
public interface UserPassWordDOService {
    /**
     * 删除
     */
    void delete(long id);

    /**
     * 增加
     */
    void insert(UserPassWordDO user);

    /**
     * 更新
     */
    int update(UserPassWordDO user);

    /**
     * 查询单个
     */
    UserPassWordDO selectById(long id);

    /**
     * 查询全部列表
     */
    List<UserPassWordDO> selectAll(int pageNum, int pageSize);
}
