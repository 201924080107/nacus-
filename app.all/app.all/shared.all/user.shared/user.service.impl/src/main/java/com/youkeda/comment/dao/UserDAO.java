package com.youkeda.comment.dao;

import com.youkeda.comment.dataobject.UserDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author 姬军伟
 * @date 2022/3/25 - 19:59
 */
@Mapper
public interface UserDAO {
    public List<UserDO> findAll();

    public int delete(@Param("id") long id);

    public UserDO findByUserName(@Param("userName") String userName);

    public int add(UserDO userDO);

    public int update(UserDO userDO);

    public List<UserDO> search(@Param("keyWord") String keyWord,
                               @Param("startTime") LocalDateTime startTime,
                               @Param("endTime") LocalDateTime endTime);

    public List<UserDO> findByIds(@Param("ids") List<Long> ids);
}
