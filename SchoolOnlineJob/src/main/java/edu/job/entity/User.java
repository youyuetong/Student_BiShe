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
 * 
 * @author null
 * @date 2019-05-16 20:34:47
 */
@Table(name = "user")
public class User {
    @Id
    @NotNull
    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long id;

    /**
     * 登录名
     */
    @Column(name = "login_name")
    @Size(min = 0, max = 20 , message = "长度必须在{min}和{max}之间")
    private String loginName;

    /**
     * 用户类型
     */
    @Column(name = "user_type")
    @Size(min = 0, max = 5 , message = "长度必须在{min}和{max}之间")
    private String userType;

    /**
     * 密码
     */
    @Size(min = 0, max = 36 , message = "长度必须在{min}和{max}之间")
    private String password;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 是否可用
     */
    @Column(name = "active_flg")
    @Size(min = 0, max = 1 , message = "长度必须在{min}和{max}之间")
    private String activeFlg;

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
     * 获取登录名
     *
     * @return login_name - 登录名
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * 设置登录名
     *
     * @param loginName 登录名
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    /**
     * 获取用户类型
     *
     * @return user_type - 用户类型
     */
    public String getUserType() {
        return userType;
    }

    /**
     * 设置用户类型
     *
     * @param userType 用户类型
     */
    public void setUserType(String userType) {
        this.userType = userType == null ? null : userType.trim();
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
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
     * 获取是否可用
     *
     * @return active_flg - 是否可用
     */
    public String getActiveFlg() {
        return activeFlg;
    }

    /**
     * 设置是否可用
     *
     * @param activeFlg 是否可用
     */
    public void setActiveFlg(String activeFlg) {
        this.activeFlg = activeFlg == null ? null : activeFlg.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", loginName=").append(loginName);
        sb.append(", userType=").append(userType);
        sb.append(", password=").append(password);
        sb.append(", createTime=").append(createTime);
        sb.append(", activeFlg=").append(activeFlg);
        sb.append("]");
        return sb.toString();
    }
}