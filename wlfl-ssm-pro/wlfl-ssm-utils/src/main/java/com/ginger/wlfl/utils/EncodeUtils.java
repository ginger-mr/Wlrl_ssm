package com.ginger.wlfl.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 加密工具类
 */
public class EncodeUtils {

    //创建BCryptPasswordEncoder加密类对象
    private static BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();

    /**
     * 获取加密后字符串
     * @param password
     * @return
     */
    public static String getEncodeString(String password) {
        return bcpe.encode(password);
    }

    /**
     * 测试
     * @param args
     */
    public static void main(String[] args) {
        String encodeString = getEncodeString("yingliuzhizhu");
        //可以看出每一次加密的结果都是不一样的
        //$2a$10$Am6NhFtRraDb696Yybe1zOnWa5dvoK5U655gwvrCP5gJQCcUAKdI.
        //$2a$10$qqLUAJpwWPiiiDJ/9FWRQuSOLkM3jlbx/Dn6enCsmR9dgqYse5Et2
        System.out.println(encodeString);
    }
}
