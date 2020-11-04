package edu.job.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * 企业公司
 * @author null
 * @date 2019-05-16 20:38:59
 */
@Table(name = "enterprise_info")
public class EnterpriseInfo {
    @Id
    @NotNull
    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long id;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long userId;

    /**
     * 公司名称
     */
    @Size(min = 0, max = 50 , message = "长度必须在{min}和{max}之间")
    private String name;

    /**
     * 公司电话
     */
    @Size(min = 0, max = 50 , message = "长度必须在{min}和{max}之间")
    private String mobile;

    /**
     * 公司成立时间
     */
    @Column(name = "birth_date")
    private Date birthDate;

    /**
     * 公司地址
     */
    @Size(min = 0, max = 50 , message = "长度必须在{min}和{max}之间")
    private String address;

    /**
     * 介绍
     */
    @Size(min = 0, max = 300 , message = "长度必须在{min}和{max}之间")
    private String introduction;

    /**
     * 城市
     */
    @Size(min = 0, max = 20 , message = "长度必须在{min}和{max}之间")
    private String city;

    /**
     * 地区
     */
    @Size(min = 0, max = 20 , message = "长度必须在{min}和{max}之间")
    private String region;

    /**
     * logo地址
     */
    @Column(name = "logo_url")
    @Size(min = 0, max = 50 , message = "长度必须在{min}和{max}之间")
    private String logoUrl;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "create_by")
    @Size(min = 0, max = 50 , message = "长度必须在{min}和{max}之间")
    private String createBy;

    @Column(name = "wechat_id")
    @Size(min = 0, max = 50 , message = "长度必须在{min}和{max}之间")
    private String wechatId;

    @Column(name = "status")
    private String status;


    @Column(name = "audit_time")
    private Date auditTime;

    @Column(name = "audit_by")
    @Size(min = 0, max = 50 , message = "长度必须在{min}和{max}之间")
    private String auditBy;

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
     * 获取用户ID
     *
     * @return user_id - 用户ID
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置用户ID
     *
     * @param userId 用户ID
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取公司名称
     *
     * @return name - 公司名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置公司名称
     *
     * @param name 公司名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取公司电话
     *
     * @return mobile - 公司电话
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置公司电话
     *
     * @param mobile 公司电话
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * 获取公司成立时间
     *
     * @return birth_date - 公司成立时间
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * 设置公司成立时间
     *
     * @param birthDate 公司成立时间
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * 获取公司地址
     *
     * @return address - 公司地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置公司地址
     *
     * @param address 公司地址
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * 获取介绍
     *
     * @return introduction - 介绍
     */
    public String getIntroduction() {
        return introduction;
    }

    /**
     * 设置介绍
     *
     * @param introduction 介绍
     */
    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }

    /**
     * 获取城市
     *
     * @return city - 城市
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置城市
     *
     * @param city 城市
     */
    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    /**
     * 获取地区
     *
     * @return region - 地区
     */
    public String getRegion() {
        return region;
    }

    /**
     * 设置地区
     *
     * @param region 地区
     */
    public void setRegion(String region) {
        this.region = region == null ? null : region.trim();
    }

    /**
     * 获取logo地址
     *
     * @return logo_url - logo地址
     */
    public String getLogoUrl() {
        return logoUrl;
    }

    /**
     * 设置logo地址
     *
     * @param logoUrl logo地址
     */
    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl == null ? null : logoUrl.trim();
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        sb.append(", address=").append(address);
        sb.append(", introduction=").append(introduction);
        sb.append(", city=").append(city);
        sb.append(", region=").append(region);
        sb.append(", logoUrl=").append(logoUrl);
        sb.append(", createTime=").append(createTime);
        sb.append(", createBy=").append(createBy);
        sb.append("]");
        return sb.toString();
    }

    public String getWechatId() {
        return wechatId;
    }

    public void setWechatId(String wechatId) {
        this.wechatId = wechatId;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public String getAuditBy() {
        return auditBy;
    }

    public void setAuditBy(String auditBy) {
        this.auditBy = auditBy;
    }
}