package com.tledu.cn.controller;

import com.tledu.cn.pojo.User;
import com.tledu.cn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Author:17
 * Date:2021-01-20 11:39
 * Description:<描述>
 */
@Controller
public class ExamController {
    @Autowired
    private UserService userService;


    //用户注册
    @RequestMapping("/userRegister")
    @ResponseBody
    public Map<String,Integer> userRegister(@RequestBody User user){
        int i = userService.registerUser(user);
        Map<String,Integer> param = new HashMap<String, Integer>();
        param.put("mark",i);
        return param;
    }

    //用户登录
    @RequestMapping("/userLogin")
    @ResponseBody
    public Map<String,String> userLogin(HttpServletRequest request,@RequestBody User user){
        int result = 0;
        Map<String,String> param = new HashMap<String, String>();
        result = userService.userLogin(request, user);
        if (result==1){
            param.put("mark","true");
        }else {
            param.put("mark","false");
        }
        return param;
    }

    //验证登录状态信息
    @RequestMapping("/getUserInfo")
    @ResponseBody
    public Map<String,Object> getUserInfo(HttpServletRequest request){
        int status = (Integer) request.getSession().getAttribute("status");
        User user = (User) request.getSession().getAttribute("Info");
        Map<String,Object> param = new HashMap<String ,Object>();
        if (status==1){
            param.put("status",status);
            param.put("Info",user);
        }else {
            param.put("status",0);
        }
        return param;
    }

    //更改密码
    @RequestMapping("/changePwd")
    @ResponseBody
    public Map<String,String> changePwd(@RequestBody User user){
        Map<String,String> param = new HashMap<String, String>();
        int i = userService.changePwd(user);
        if (i==1){
            param.put("mark","true");
        }else {
            param.put("mark","false");
        }

        return param;
    }

    //上传头像
    @RequestMapping("/uploadImage")
    @ResponseBody
    public Map<String,Boolean> uploadImage(HttpServletRequest request){
//        测试代码
//        User user = new User();
//        user.setU_id("10efe075-a94e-4d98-9ef3-28e39713954b");
//        user.setIs_delete(0);
//        request.getSession().setAttribute("Info",user);
        Map<String, Boolean> result = userService.uploadImage(request);
        System.out.println(result);
        return result;
    }



}
