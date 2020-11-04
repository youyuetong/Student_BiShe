package edu.job.service;

import edu.job.base.BaseService;
import edu.job.dto.SearchVo;
import edu.job.entity.EnterpriseInfo;
import edu.job.entity.User;
import edu.job.enumeration.UserEnum;
import edu.job.mapper.EnterpriseInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * 企业service
 */
@Service
public class EnterpriseInfoService extends BaseService<EnterpriseInfo> {

    @Autowired
    private EnterpriseInfoMapper enterpriseInfoMapper;


    public EnterpriseInfo selectByUserId(String userId) {
        return enterpriseInfoMapper.selectByUserId(userId);
    }

    public Map<String, Object> doquery(SearchVo searchVo, User user) {

        if(user.getUserType().equals(UserEnum.USER_TYPE_ENTERPRISE.getCode())){
            EnterpriseInfo enterpriseInfo = enterpriseInfoMapper.selectByUserId(user.getId().toString());
            searchVo.addCondition("id", enterpriseInfo.getId());
        }


        Map<String,Object> resultMap = wrapPage(() -> enterpriseInfoMapper.doquery(searchVo.getCondition()),searchVo);
        return resultMap;
    }

    public Map<String,Object> audit(String id, String statusDesc, User user) {
        EnterpriseInfo enterpriseInfo = new EnterpriseInfo();
        enterpriseInfo.setId(Long.parseLong(id));
        enterpriseInfo.setStatus(statusDesc);
        enterpriseInfo.setAuditBy(user.getLoginName());
        enterpriseInfo.setAuditTime(new Date());

        int count = enterpriseInfoMapper.updateByPrimaryKeySelective(enterpriseInfo);

        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("result", count > 0);
        return resultMap;
    }
}
