package com.tledu.cn.pojo;

/**
 * Author:17
 * Date:2021-01-20 11:33
 * Description:<描述>
 */
public class User {
    private String u_id;
    private String acc;
    private String pwd;
    private String phone;
    private String email;
    private String image;
    private int is_allow;
    private String create_time;
    private int is_delete;

    public User() {
    }

    public User(String u_id, String acc, String pwd, String phone, String email, String image, int is_allow, String create_time, int is_delete) {
        this.u_id = u_id;
        this.acc = acc;
        this.pwd = pwd;
        this.phone = phone;
        this.email = email;
        this.image = image;
        this.is_allow = is_allow;
        this.create_time = create_time;
        this.is_delete = is_delete;
    }

    @Override
    public String toString() {
        return "User{" +
                "u_id='" + u_id + '\'' +
                ", acc='" + acc + '\'' +
                ", pwd='" + pwd + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", image='" + image + '\'' +
                ", is_allow=" + is_allow +
                ", create_time='" + create_time + '\'' +
                ", is_delete=" + is_delete +
                '}';
    }

    public String getU_id() {
        return u_id;
    }

    public void setU_id(String u_id) {
        this.u_id = u_id;
    }

    public String getAcc() {
        return acc;
    }

    public void setAcc(String acc) {
        this.acc = acc;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getIs_allow() {
        return is_allow;
    }

    public void setIs_allow(int is_allow) {
        this.is_allow = is_allow;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public int getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(int is_delete) {
        this.is_delete = is_delete;
    }
}
