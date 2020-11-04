package edu.job.mapper;

import edu.job.base.BaseMapper;
import edu.job.entity.EnterpriseInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface EnterpriseInfoMapper extends BaseMapper<EnterpriseInfo> {
    EnterpriseInfo selectByUserId(@Param(value = "userId") String userId);

    List<EnterpriseInfo> doquery(Map<String, Object> condition);
}