package com.tledu.cn.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Author:17
 * Date:2021-01-20 13:10
 * Description:<描述>
 */
public class TimeUtil {
    public static String createTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(new Date());
        return format;
    }
}
