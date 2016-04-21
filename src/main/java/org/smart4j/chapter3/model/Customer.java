package org.smart4j.chapter3.model;

/**
 * 客户实体类
 * Created by lin on 2016/4/19.
 */
public class Customer {
    /**
     * ID
     */
    private long id;

    /**
     * 客户姓名
     */
    private String name;

    /**
     * 联系人
     */
    private String contact;

    /**
     * 联系电话
     */
    private String telephone;

    /**
     * 邮箱地址
     */
    private String email;

    /**
     * 备注
     */
    private String remark;

    /**
     * 获取ID
     * @return id
     */
    public long getId() {
        return id;
    }

    /**
     * 设置ID
     * @param id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * 获取客户名称
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置客户名称
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取联系人
     * @return contact
     */
    public String getContact() {
        return contact;
    }

    /**
     * 设置联系人
     * @param contact
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     * 获取联系电话
     * @return telephone
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * 设置联系电话
     * @param telephone
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * 获取邮箱地址
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱地址
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
