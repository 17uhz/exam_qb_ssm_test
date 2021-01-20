package com.tledu.cn.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Author:17
 * Date:2021-01-20 17:11
 * Description:<描述>
 */
public class UpLoadUtil {

    public static String makeFileName(String filename){
        return UUID.randomUUID().toString()+"_"+filename;
    }
    public static String makePath(String savePath){
        SimpleDateFormat simpleDateFormat = new  SimpleDateFormat("yyyyMMdd");
        String datePath = simpleDateFormat.format(new Date());
        String dir = savePath + "\\" + datePath;
        File file = new File(dir);
        if (!file.exists()){
            file.mkdirs();
        }
        return dir;
    }
}
