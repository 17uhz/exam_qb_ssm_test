package com.tledu.cn.dao;

import com.tledu.cn.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Author:17
 * Date:2021-01-20 11:13
 * Description:<描述>
 */
@Mapper
public interface UserDao {
    int registerUser(User user);
    List<User> selectUser(User user);  //查看 acc 是否存在，防止acc 重复
    User userLogin(User user);
    List<User> selectUserAccPhone(User user); //查看是否存在 acc ，phone 一致的用户，一致，后续用于更改密码
    int changePwd(User user);
    int upLoadImage(User user);
}


