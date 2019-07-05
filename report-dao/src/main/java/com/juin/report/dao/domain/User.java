package com.juin.report.dao.domain;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.Id
     *
     * @mbggenerated Thu Jul 04 17:06:39 CST 2019
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.UserId
     *
     * @mbggenerated Thu Jul 04 17:06:39 CST 2019
     */
    private Integer userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.Name
     *
     * @mbggenerated Thu Jul 04 17:06:39 CST 2019
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.password
     *
     * @mbggenerated Thu Jul 04 17:06:39 CST 2019
     */
    private String password;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.Sex
     *
     * @mbggenerated Thu Jul 04 17:06:39 CST 2019
     */
    private String sex;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.Age
     *
     * @mbggenerated Thu Jul 04 17:06:39 CST 2019
     */
    private Integer age;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.Phone
     *
     * @mbggenerated Thu Jul 04 17:06:39 CST 2019
     */
    private String phone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.Createtime
     *
     * @mbggenerated Thu Jul 04 17:06:39 CST 2019
     */
    private Date createtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_user.LastUpdateDate
     *
     * @mbggenerated Thu Jul 04 17:06:39 CST 2019
     */
    private Date lastUpdateDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_user
     *
     * @mbggenerated Thu Jul 04 17:06:39 CST 2019
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.Id
     *
     * @return the value of t_user.Id
     *
     * @mbggenerated Thu Jul 04 17:06:39 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.Id
     *
     * @param id the value for t_user.Id
     *
     * @mbggenerated Thu Jul 04 17:06:39 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.UserId
     *
     * @return the value of t_user.UserId
     *
     * @mbggenerated Thu Jul 04 17:06:39 CST 2019
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.UserId
     *
     * @param userId the value for t_user.UserId
     *
     * @mbggenerated Thu Jul 04 17:06:39 CST 2019
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.Name
     *
     * @return the value of t_user.Name
     *
     * @mbggenerated Thu Jul 04 17:06:39 CST 2019
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.Name
     *
     * @param name the value for t_user.Name
     *
     * @mbggenerated Thu Jul 04 17:06:39 CST 2019
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.password
     *
     * @return the value of t_user.password
     *
     * @mbggenerated Thu Jul 04 17:06:39 CST 2019
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.password
     *
     * @param password the value for t_user.password
     *
     * @mbggenerated Thu Jul 04 17:06:39 CST 2019
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.Sex
     *
     * @return the value of t_user.Sex
     *
     * @mbggenerated Thu Jul 04 17:06:39 CST 2019
     */
    public String getSex() {
        return sex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.Sex
     *
     * @param sex the value for t_user.Sex
     *
     * @mbggenerated Thu Jul 04 17:06:39 CST 2019
     */
    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.Age
     *
     * @return the value of t_user.Age
     *
     * @mbggenerated Thu Jul 04 17:06:39 CST 2019
     */
    public Integer getAge() {
        return age;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.Age
     *
     * @param age the value for t_user.Age
     *
     * @mbggenerated Thu Jul 04 17:06:39 CST 2019
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.Phone
     *
     * @return the value of t_user.Phone
     *
     * @mbggenerated Thu Jul 04 17:06:39 CST 2019
     */
    public String getPhone() {
        return phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.Phone
     *
     * @param phone the value for t_user.Phone
     *
     * @mbggenerated Thu Jul 04 17:06:39 CST 2019
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.Createtime
     *
     * @return the value of t_user.Createtime
     *
     * @mbggenerated Thu Jul 04 17:06:39 CST 2019
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.Createtime
     *
     * @param createtime the value for t_user.Createtime
     *
     * @mbggenerated Thu Jul 04 17:06:39 CST 2019
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_user.LastUpdateDate
     *
     * @return the value of t_user.LastUpdateDate
     *
     * @mbggenerated Thu Jul 04 17:06:39 CST 2019
     */
    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_user.LastUpdateDate
     *
     * @param lastUpdateDate the value for t_user.LastUpdateDate
     *
     * @mbggenerated Thu Jul 04 17:06:39 CST 2019
     */
    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user
     *
     * @mbggenerated Thu Jul 04 17:06:39 CST 2019
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", name=").append(name);
        sb.append(", password=").append(password);
        sb.append(", sex=").append(sex);
        sb.append(", age=").append(age);
        sb.append(", phone=").append(phone);
        sb.append(", createtime=").append(createtime);
        sb.append(", lastUpdateDate=").append(lastUpdateDate);
        sb.append("]");
        return sb.toString();
    }
}