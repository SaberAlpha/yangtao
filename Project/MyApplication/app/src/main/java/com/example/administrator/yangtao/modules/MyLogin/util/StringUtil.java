package com.example.administrator.yangtao.modules.MyLogin.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/11/24 0024.
 */
public class StringUtil {

    // 判断是否是手机号码
    public static boolean isMobileNum(String mobiles) {

        Pattern p = Pattern.compile("^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\\d{8})$");

        Matcher m = p.matcher(mobiles);

        return m.matches();
    }
}
