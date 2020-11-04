package edu.job.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * 
 * @author null
 * @date 2019-05-16 20:38:30
 */
@Table(name = "job_hunter")
public class JobHunter {
    @Id
    @NotNull
    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long id;

    @Column(name = "user_id")
    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long userId;

    /**
     * 姓名
     */
    @Size(min = 0, max = 20 , message = "长度必须在{min}和{max}之间")
    private String name;

    /**
     * 性别
     * */
    private String gender;


    /**
     * 手机号
     */
    @Size(min = 0, max = 20 , message = "长度必须在{min}和{max}之间")
    private String mobile;

    /**
     * 生日
     */
    @Column(name = "birth_date")
    private Date birthDate;

    /**
     * 工作年限
     */
    @Column(name = "work_year")
    @Max(value=2147483647,message="最大值不能高于{value}")
    @Min(value=-2147483648,message="最小值不能低于{value}")
    private Integer workYear;


    /**
     * 学历
     */
    @Size(min = 0, max = 20 , message = "长度必须在{min}和{max}之间")
    private String education;


    /**
     * 大学名称
     */
    @Column(name = "college_name")
    @Size(min = 0, max = 20 , message = "长度必须在{min}和{max}之间")
    private String collegeName;

    /**
     * 上家公司名称
     */
    @Column(name = "last_company")
    @Size(min = 0, max = 20 , message = "长度必须在{min}和{max}之间")
    private String lastCompany;

    /**
     * 自我介绍
     */
    @Column(name = "self_introduction")
    @Size(min = 0, max = 300 , message = "长度必须在{min}和{max}之间")
    private String selfIntroduction;

    /**
     * 一句话描述自己
     */
    @Size(min = 0, max = 50 , message = "长度必须在{min}和{max}之间")
    private String remark;

    /**
     * 是否可以被查看
     */
    @Column(name = "can_search")
    @Size(min = 0, max = 1 , message = "长度必须在{min}和{max}之间")
    private String canSearch;

    /**
     * 面试倾向
     */
    @Size(min = 0, max = 50 , message = "长度必须在{min}和{max}之间")
    private String tag;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "create_by")
    @Size(min = 0, max = 50 , message = "长度必须在{min}和{max}之间")
    private String createBy;

    @Column(name = "email")
    @Size(min = 0, max = 20 , message = "长度必须在{min}和{max}之间")
    private String email;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "wechat_id")
    private String wechatId;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return user_id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取姓名
     *
     * @return name - 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置姓名
     *
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取手机号
     *
     * @return mobile - 手机号
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置手机号
     *
     * @param mobile 手机号
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * 获取生日
     *
     * @return birth_date - 生日
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * 设置生日
     *
     * @param birthDate 生日
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * 获取工作年限
     *
     * @return work_year - 工作年限
     */
    public Integer getWorkYear() {
        return workYear;
    }

    /**
     * 设置工作年限
     *
     * @param workYear 工作年限
     */
    public void setWorkYear(Integer workYear) {
        this.workYear = workYear;
    }

    /**
     * 获取大学名称
     *
     * @return college_name - 大学名称
     */
    public String getCollegeName() {
        return collegeName;
    }

    /**
     * 设置大学名称
     *
     * @param collegeName 大学名称
     */
    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName == null ? null : collegeName.trim();
    }

    /**
     * 获取上家公司名称
     *
     * @return last_company - 上家公司名称
     */
    public String getLastCompany() {
        return lastCompany;
    }

    /**
     * 设置上家公司名称
     *
     * @param lastCompany 上家公司名称
     */
    public void setLastCompany(String lastCompany) {
        this.lastCompany = lastCompany == null ? null : lastCompany.trim();
    }

    /**
     * 获取自我介绍
     *
     * @return self_introduction - 自我介绍
     */
    public String getSelfIntroduction() {
        return selfIntroduction;
    }

    /**
     * 设置自我介绍
     *
     * @param selfIntroduction 自我介绍
     */
    public void setSelfIntroduction(String selfIntroduction) {
        this.selfIntroduction = selfIntroduction == null ? null : selfIntroduction.trim();
    }

    /**
     * 获取一句话描述自己
     *
     * @return remark - 一句话描述自己
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置一句话描述自己
     *
     * @param remark 一句话描述自己
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 获取是否可以被查看
     *
     * @return can_search - 是否可以被查看
     */
    public String getCanSearch() {
        return canSearch;
    }

    /**
     * 设置是否可以被查看
     *
     * @param canSearch 是否可以被查看
     */
    public void setCanSearch(String canSearch) {
        this.canSearch = canSearch == null ? null : canSearch.trim();
    }

    /**
     * 获取面试倾向
     *
     * @return tag - 面试倾向
     */
    public String getTag() {
        return tag;
    }

    /**
     * 设置面试倾向
     *
     * @param tag 面试倾向
     */
    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return create_by
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * @param createBy
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", name=").append(name);
        sb.append(", mobile=").append(mobile);
        sb.append(", birthDate=").append(birthDate);
        sb.append(", workYear=").append(workYear);
        sb.append(", collegeName=").append(collegeName);
        sb.append(", lastCompany=").append(lastCompany);
        sb.append(", selfIntroduction=").append(selfIntroduction);
        sb.append(", remark=").append(remark);
        sb.append(", canSearch=").append(canSearch);
        sb.append(", tag=").append(tag);
        sb.append(", createTime=").append(createTime);
        sb.append(", createBy=").append(createBy);
        sb.append("]");
        return sb.toString();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getWechatId() {
        return wechatId;
    }

    public void setWechatId(String wechatId) {
        this.wechatId = wechatId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}