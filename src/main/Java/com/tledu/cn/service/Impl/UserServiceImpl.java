package com.tledu.cn.service.Impl;

import com.tledu.cn.dao.UserDao;
import com.tledu.cn.pojo.User;
import com.tledu.cn.service.UserService;
import com.tledu.cn.util.TimeUtil;
import com.tledu.cn.util.UpLoadUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
            user.setImage("../image/init/init.jpg");
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
            request.getSession().setAttribute("Info",getUser);
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


    //上传头像
    @Override
    public Map<String, Boolean> uploadImage(HttpServletRequest request) {
        Map<String,Boolean> result = new HashMap<String, Boolean>();
        String savePath = request.getServletContext().getRealPath("/image");
        System.out.println(request.getSession().getAttribute("Info"));
        System.out.println(savePath);
        String tempPath = request.getServletContext().getRealPath("/tempFile");

        File tmpFile = new File(tempPath);
        if (!tmpFile.exists()) {
            tmpFile.exists();
        }

        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(1024*100);
        factory.setRepository(tmpFile);

        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setProgressListener(new ProgressListener() {
            @Override
            public void update(long haveSize, long totalSize, int i) {
                System.out.println("文件大小为："+totalSize+"，当前已处理 "+haveSize);
            }
        });

        upload.setHeaderEncoding("UTF-8");
        if (!ServletFileUpload.isMultipartContent(request)){
            result.put("mark",false);
            return result;
        }

        upload.setFileSizeMax(1024*1024);
        upload.setSizeMax(1024*1024*10);
        try {
            List<FileItem> list = upload.parseRequest(request);
//            System.out.println("----------------------------"+list.size());
            for (FileItem item : list){
                if (item.isFormField()){
                    System.out.println("上传文本");
                }else {
                    String fileName = item.getName();
                    System.out.println("文件名称"+fileName);
                    if (fileName==null || fileName.trim().equals("")){
                        continue;
                    }
                    fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
                    String fileSuffixName = fileName.substring(fileName.lastIndexOf(".") + 1);
                    System.out.println("上传的文件后缀:"+fileSuffixName);
                    InputStream in = item.getInputStream();
                    String saveFileName = UpLoadUtil.makeFileName(fileName);
                    String realSavePath = UpLoadUtil.makePath(savePath);
                    FileOutputStream out = new FileOutputStream(realSavePath + "\\" + saveFileName);

                    //数据库存储路径
                    User user = (User) request.getSession().getAttribute("Info");
                    if (user!=null){
//                        E:\20stu_File\ideaProject\exam_qb_ssm\src\main\webapp\image\20210120/561ec0ce-d98d-4049-9ac6-ed1e6dc9f516_testtttttt.jpg
                        String dataImgFile = realSavePath.substring(realSavePath.lastIndexOf("\\") + 1);
                        user.setImage("../image/"+dataImgFile+"/"+saveFileName);
//                        System.out.println("-------------------"+user.getImage());
                        int i = userDao.upLoadImage(user);
                        result.put("mark",true);
                    }


                    byte[] buffer = new byte[1024];
                    int len = 0;
                    while ((len=in.read(buffer))!=-1){

                        out.write(buffer,0,len);
                    }
                    out.flush();
                    out.close();
                    in.close();
                }
            }
        } catch (FileUploadException | IOException e) {
            e.printStackTrace();
        }


        return result;
    }


}
