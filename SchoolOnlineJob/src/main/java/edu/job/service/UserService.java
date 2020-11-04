package edu.job.service;

import com.alibaba.fastjson.JSONObject;
import edu.job.base.BaseService;
import edu.job.dto.job.JobHunterDTO;
import edu.job.dto.user.UserDTO;
import edu.job.entity.EnterpriseInfo;
import edu.job.entity.HunterExperience;
import edu.job.entity.JobHunter;
import edu.job.entity.User;
import edu.job.enumeration.UserEnum;
import edu.job.mapper.UserMapper;
import edu.job.utils.IDGenerator;
import edu.job.utils.Md5Util;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * 用户Service
 */
@Service
public class UserService extends BaseService<User> {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private EnterpriseInfoService enterpriseInfoService;

    @Autowired
    private JobHunterService jobHunterService;

    @Autowired
    private HunterExperienceService hunterExperienceService;

    public UserDTO checkValid(String loginName, String password) {
        User user = userMapper.selectByLoginName(loginName);
        if(user != null){
            if(user.getPassword().equals(Md5Util.md5(password))){
                return convertDTO(user);
            }
        }
        return null;
    }

    private UserDTO convertDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setLoginName(user.getLoginName());
        userDTO.setPassword(user.getPassword());
        userDTO.setUserType(user.getUserType());
        userDTO.setUserTypeDesc(UserEnum.getUserType(user.getUserType()));

        if(UserEnum.USER_TYPE_ENTERPRISE.getCode().equals(user.getUserType())){
            EnterpriseInfo enterpriseInfo = enterpriseInfoService.selectByUserId(user.getId().toString());
            userDTO.setCompanyId(enterpriseInfo.getId().toString());
        }
        return userDTO;
    }

    //注册
    public Map<String, Object> register(UserDTO user) {
        Map<String,Object> result = new HashMap<>();
        User userOld = userMapper.selectByLoginName(user.getLoginName());

        if(user.getId() == null && userOld == null){
            User saveUser = new User();
            saveUser.setId(IDGenerator.generateId());
            saveUser.setLoginName(user.getLoginName());
            saveUser.setUserType(user.getUserType());
            saveUser.setCreateTime(new Date());
            saveUser.setPassword(Md5Util.md5(user.getPassword()));
            int count = userMapper.insert(saveUser);

            if(UserEnum.USER_TYPE_ADMIN.getCode().equals(user.getUserType())){ //管理员

            }else if(UserEnum.USER_TYPE_ENTERPRISE.getCode().equals(user.getUserType())){ //企业用户
                EnterpriseInfo enterpriseInfo = new EnterpriseInfo();
                enterpriseInfo.setId(IDGenerator.generateId());
                enterpriseInfo.setName(user.getEnterpriseName());
                enterpriseInfo.setUserId(saveUser.getId());
                enterpriseInfo.setCreateBy(user.getLoginName());
                enterpriseInfo.setCreateTime(new Date());
                enterpriseInfo.setMobile(user.getMobile());
                enterpriseInfo.setStatus("待审核");
                enterpriseInfoService.insert(enterpriseInfo);
            }else if(UserEnum.USER_TYPE_JOB_HUNTER.getCode().equals(user.getUserType())){ //求职者
                JobHunter jobHunter = new JobHunter();
                jobHunter.setId(IDGenerator.generateId());
                jobHunter.setUserId(saveUser.getId());
                jobHunter.setName(user.getName());
                jobHunter.setEmail(user.getEmail());
                jobHunter.setGender(user.getGender());
                jobHunter.setCreateBy(user.getLoginName());
                jobHunter.setCreateTime(new Date());
                jobHunter.setCanSearch("N");
                jobHunter.setEducation(user.getEducation());
                jobHunter.setMobile(user.getMobile());
                jobHunter.setCollegeName(user.getCollegeName());
                jobHunterService.insert(jobHunter);
            }
            result.put("result", count > 0);
            result.put("message", "注册成功");
            return result;
        }
        result.put("result", false);
        result.put("message", "用户名已存在");
        return result;
    }

    public Map<String, Object> changePassword(UserDTO user) {
        Map<String,Object> result = new HashMap<>();
        User userOld = userMapper.selectByLoginName(user.getLoginName());
        if(user != null){
            if(user.getPassword().equals(Md5Util.md5(user.getOldPassword()))){
                userOld.setPassword(Md5Util.md5(user.getNewPassword()
                ));
                userMapper.updateByPrimaryKeySelective(userOld);
                result.put("flag", true);
                result.put("message", "修改成功");
            } else {
                result.put("flag", false);
                result.put("message", "旧密码不正确");
            }

        }else{
            result.put("flag", false);
            result.put("message", "用户不存在");
        }
        return result;
    }

    public Map<String, Object> updateUserInfo(JSONObject jsonObject) {
        Map<String,Object> result = new HashMap<>();

        UserDTO user = (UserDTO) jsonObject.get("basic");

        if(UserEnum.USER_TYPE_ADMIN.getCode().equals(user.getUserType())){ //管理员

        }else if(UserEnum.USER_TYPE_ENTERPRISE.getCode().equals(user.getUserType())){ //企业用户
            JSONObject detail = (JSONObject) jsonObject.get("detail");
            EnterpriseInfo enterpriseInfo = detail.toJavaObject(EnterpriseInfo.class);
            enterpriseInfo.setStatus("待审核");
            enterpriseInfoService.updateSelective(enterpriseInfo);
        }else if(UserEnum.USER_TYPE_JOB_HUNTER.getCode().equals(user.getUserType())){ //求职者
            JSONObject detail = (JSONObject) jsonObject.get("detail");
            JobHunter jobHunter = detail.toJavaObject(JobHunter.class);

            List<HunterExperience> projectExList = detail.getJSONArray("projectExList").toJavaList(HunterExperience.class);
            List<HunterExperience> workExList = detail.getJSONArray("workExList").toJavaList(HunterExperience.class);
            List<HunterExperience> eductionExList = detail.getJSONArray("educationExList").toJavaList(HunterExperience.class);

            List<HunterExperience> experienceList = new ArrayList<>();
            experienceList.addAll(projectExList);
            experienceList.addAll(workExList);
            experienceList.addAll(eductionExList);

            hunterExperienceService.deleteByHunterId(jobHunter.getId());

            experienceList.stream().forEach(item->{
                item.setId(IDGenerator.generateId());
                item.setHunterId(jobHunter.getId());
                hunterExperienceService.insert(item);
            });

            jobHunterService.updateSelective(jobHunter);
        }
        result.put("flag", true);
        result.put("message","保存成功");
        return result;
    }

    public Map<String, Object> viewUserInfo(String userId) {
        Map<String,Object> result = new HashMap<>();

        User user = userMapper.selectByPrimaryKey(Long.parseLong(userId));
        UserDTO userDTO =  convertDTO(user);
        result.put("basic", userDTO);
        if(UserEnum.USER_TYPE_ADMIN.getCode().equals(user.getUserType())){ //管理员
            result.put("detail", null);
        }else if(UserEnum.USER_TYPE_ENTERPRISE.getCode().equals(user.getUserType())){ //企业用户
            EnterpriseInfo enterpriseInfo = enterpriseInfoService.selectByUserId(userId);
            result.put("detail", enterpriseInfo);
        }else if(UserEnum.USER_TYPE_JOB_HUNTER.getCode().equals(user.getUserType())){ //求职者
            JobHunter jobHunter = jobHunterService.selectByUserId(userId);
            JobHunterDTO jobHunterDTO = new JobHunterDTO();
            BeanUtils.copyProperties(jobHunter, jobHunterDTO);
            jobHunterDTO.setGenderDesc(jobHunter.getGender().equals("1") ? "男" : jobHunter.getGender().equals("0") ? "女" : "");
            result.put("detail", jobHunterDTO);

            String hunterId = jobHunter.getId().toString();
            List<HunterExperience> projectExList = hunterExperienceService.getHunterExperienceByHunterIdAndType(Long.parseLong(hunterId), "XM");
            List<HunterExperience> workExList = hunterExperienceService.getHunterExperienceByHunterIdAndType(Long.parseLong(hunterId), "GZ");
            List<HunterExperience> educationExList = hunterExperienceService.getHunterExperienceByHunterIdAndType(Long.parseLong(hunterId), "JY");

            result.put("projectExList", projectExList);
            result.put("workExList", workExList);
            result.put("educationExList", educationExList);
        }
        return result;
    }

    public Map<String, Object> changeSetting(String userId, String canSearch) {
        Map<String,Object> result = new HashMap<>();
        User user = userMapper.selectByPrimaryKey(Long.parseLong(userId));
        if(UserEnum.USER_TYPE_JOB_HUNTER.getCode().equals(user.getUserType())){ //求职者
            JobHunter jobHunter = jobHunterService.selectByUserId(userId);
            jobHunter.setCanSearch(canSearch);
            int count = jobHunterService.updateSelective(jobHunter);
            result.put("result", count > 0);
            result.put("message", "修改成功");
        }else{
            result.put("result", false);
            result.put("message", "角色不正确不能修改");
        }
        return result;
    }

    public Map<String, Object> validatePassword(User user) {
        Map<String,Object> result = new HashMap<>();
        User userOld = userMapper.selectByLoginName(user.getLoginName());

        if(userOld != null){
            if(Md5Util.md5(user.getPassword()).equals(userOld.getPassword())){
                result.put("result", true);
                result.put("message", "原密码正确");
                return result;
            }else{
                result.put("result", false);
                result.put("message", "原密码错误");
                return result;
            }
        }
        result.put("result", false);
        result.put("message", "用户名已存在");
        return result;
    }

    public Map<String,Object> updateWechatId(JSONObject jsonObject, User user) {
        String wechatId = jsonObject.getString("wechatId");

        if(user.getUserType().equals(UserEnum.USER_TYPE_ENTERPRISE.getCode())){
            EnterpriseInfo enterpriseInfo = enterpriseInfoService.selectByUserId(user.getId().toString());
            enterpriseInfo.setWechatId(wechatId);
            int count = enterpriseInfoService.updateSelective(enterpriseInfo);
        }else if(user.getUserType().equals(UserEnum.USER_TYPE_JOB_HUNTER.getCode())){
            JobHunter jobHunter = jobHunterService.selectByUserId(user.getId().toString());
            jobHunter.setWechatId(wechatId);
            int count = jobHunterService.updateSelective(jobHunter);
        }



        Map<String,Object> result = new HashMap<>();
        result.put("flag", true);
        result.put("message","修改成功");

        return result;
    }

    public Map<String,Object> updateEmail(JSONObject jsonObject, User user) {
        String email = jsonObject.getString("email");
        JobHunter jobHunter = jobHunterService.selectByUserId(user.getId().toString());
        jobHunter.setEmail(email);
        int count = jobHunterService.updateSelective(jobHunter);

        Map<String,Object> result = new HashMap<>();
        result.put("flag", true);
        result.put("message","修改成功");

        return result;
    }
}
