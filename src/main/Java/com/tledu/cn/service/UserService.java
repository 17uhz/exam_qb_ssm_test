package com.tledu.cn.service;

import com.tledu.cn.pojo.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Author:17
 * Date:2021-01-20 13:04
 * Description:<描述>
 */
public interface UserService {
    int registerUser(User user);

    int userLogin(HttpServletRequest request,User user);
    int changePwd(User user);

    Map<String,Boolean> uploadImage(HttpServletRequest request);

}
