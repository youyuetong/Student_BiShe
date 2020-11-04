package edu.job.service;

import edu.job.base.BaseService;
import edu.job.dto.SearchVo;
import edu.job.dto.job.ExplainMeetingDTO;
import edu.job.entity.EnterpriseInfo;
import edu.job.entity.ExplainMeeting;
import edu.job.entity.User;
import edu.job.enumeration.UserEnum;
import edu.job.mapper.ExplainMeetingMapper;
import edu.job.utils.DateUtil;
import edu.job.utils.IDGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * 宣讲会service
 */
@Service
public class ExplainMeetingService  extends BaseService<ExplainMeeting> {

    @Autowired
    private ExplainMeetingMapper explainMeetingMapper;

    @Autowired
    private EnterpriseInfoService enterpriseInfoService;

    public Map<String,Object> doquery(SearchVo searchVo, User user) {
        if(user.getUserType().equals(UserEnum.USER_TYPE_ENTERPRISE.getCode())){ //企业
            EnterpriseInfo enterpriseInfo = enterpriseInfoService.selectByUserId(user.getId().toString());
            searchVo.addCondition("enterpriseId", enterpriseInfo.getId());
        }else if(user.getUserType().equals(UserEnum.USER_TYPE_JOB_HUNTER.getCode())){ //求职者
            searchVo.addCondition("startDate", DateUtil.dateToString(new Date(), DateUtil.DEF_DATETIME_FORMAT));
            searchVo.addCondition("enterpriseStatus", "审核通过");

        }

        Map<String,Object> resultMap = wrapPage(() -> explainMeetingMapper.doquery(searchVo.getCondition()),searchVo);

        List<ExplainMeeting> explainMeetingList = (List<ExplainMeeting>) resultMap.get("queryresult");

        List<ExplainMeetingDTO> explainMeetingDTOList = new ArrayList<>();
        Map<String,EnterpriseInfo> cacheMap = new HashMap<>();
        explainMeetingList.stream().forEach(item->{
            ExplainMeetingDTO dto = new ExplainMeetingDTO();
            BeanUtils.copyProperties(item, dto);
            if(cacheMap.containsKey(item.getEnterpriseId().toString())){
                dto.setEnterpriseName(cacheMap.get(item.getEnterpriseId().toString()).getName());
            }else{
                EnterpriseInfo enterpriseInfo = enterpriseInfoService.selectByPrimaryKey(item.getEnterpriseId());
                cacheMap.put(item.getEnterpriseId().toString(), enterpriseInfo);
                dto.setEnterpriseName(enterpriseInfo.getName());
            }
            explainMeetingDTOList.add(dto);
        });
        resultMap.put("queryresult", explainMeetingDTOList);
        return resultMap;
    }

    public Map<String,Object> save(ExplainMeeting explainMeeting, User user) {
        int count = 0;
        if(explainMeeting.getId() == null){
            explainMeeting.setId(IDGenerator.generateId());

            EnterpriseInfo enterpriseInfo = enterpriseInfoService.selectByUserId(user.getId().toString());
            explainMeeting.setEnterpriseId(enterpriseInfo.getId());

            explainMeeting.setCreateTime(new Date());
            explainMeeting.setCreateUser(user.getLoginName());
            explainMeeting.setPublishDate(new Date());
            count = explainMeetingMapper.insert(explainMeeting);
        }else{
            explainMeeting.setModifyTime(new Date());
            explainMeeting.setModifyUser(user.getLoginName());
            explainMeeting.setPublishDate(new Date());
            count = explainMeetingMapper.updateByPrimaryKeySelective(explainMeeting);
        }

        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("result", count > 0);
        resultMap.put("message", "保存成功");

        return resultMap;
    }


    public Map<String,Object> delete(String id, User user) {
        int count = 0;
        Map<String,Object> resultMap = new HashMap<>();
        if(user.getUserType().equals(UserEnum.USER_TYPE_ENTERPRISE.getCode())){
            EnterpriseInfo enterpriseInfo = enterpriseInfoService.selectByUserId(user.getId().toString());
            ExplainMeeting explainMeeting = explainMeetingMapper.selectByPrimaryKey(id);

            if(enterpriseInfo == null || explainMeeting == null){
                resultMap.put("result", false);
                resultMap.put("message", "信息为空");
            }else{
                if(enterpriseInfo.getId().toString().equals(explainMeeting.getEnterpriseId().toString())){
                    count = explainMeetingMapper.deleteByPrimaryKey(id);
                    resultMap.put("result", count > 0);
                    resultMap.put("message", "删除成功");
                }else{
                    resultMap.put("result", false);
                    resultMap.put("message", "非当前企业不能删除");
                }
            }

        }else{
            resultMap.put("result", false);
            resultMap.put("message", "非企业账号不能删除");
        }
        return resultMap;
    }

    public Map<String,Object> view(String id) {
        ExplainMeeting explainMeeting = explainMeetingMapper.selectByPrimaryKey(id);
        EnterpriseInfo enterpriseInfo = enterpriseInfoService.selectByPrimaryKey(explainMeeting.getEnterpriseId());

        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("data", explainMeeting);
        resultMap.put("enterpriseInfo", enterpriseInfo);

        return resultMap;
    }
}
