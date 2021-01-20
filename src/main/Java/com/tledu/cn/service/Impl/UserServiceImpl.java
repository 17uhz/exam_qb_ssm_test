package com.tledu.cn.service.Impl;

import com.tledu.cn.dao.UserDao;
import com.tledu.cn.pojo.User;
import com.tledu.cn.service.UserService;
import com.tledu.cn.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

/**
 * Author:17
 * Date:2021-01-20 13:06
 * Description:<描述>
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    //用户注册
    @Override
    public int registerUser(User user) {
        int result = 0;
        if (user==null){
            return result;
        }
        List<User> users = userDao.selectUser(user);
        if (users.size()==0){
            user.setU_id(UUID.randomUUID().toString());
            user.setIs_delete(0);
            user.setCreate_time(TimeUtil.createTime());
            user.setIs_allow(1);
            user.setImage("http://localhost:8080/exam_qb_ssm/image/init/init.jpg");
            result = userDao.registerUser(user);
        }
        return result;
    }

    //用户登录
    @Override
    public int userLogin(HttpServletRequest request,User user) {
        int result = 0;
        if (user==null || user.getAcc().equals("") || user.getPwd().equals("")){
            return result;
        }
        User getUser = userDao.userLogin(user);
        if (getUser != null){
            request.getSession().setAttribute("status",1);
            request.getSession().setAttribute("info",getUser);
            result = 1;
        }
        return result;
    }

    //更改密码
    @Override
    public int changePwd(User user) {
        int result = 0;
        List<User> users = userDao.selectUserAccPhone(user);
        if (users.size()==1){
            result = userDao.changePwd(user);
        }
        return result;
    }


}
