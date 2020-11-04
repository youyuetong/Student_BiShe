package edu.job.mapper;

import edu.job.base.BaseMapper;
import edu.job.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {
    User selectByLoginName(@Param(value = "loginName") String loginName);
}