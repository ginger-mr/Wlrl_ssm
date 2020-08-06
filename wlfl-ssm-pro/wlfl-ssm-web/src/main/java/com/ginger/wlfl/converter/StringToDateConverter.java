package com.ginger.wlfl.converter;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringToDateConverter implements Converter<String, Date> {
    @Override
    public Date convert(String source) {
        if (source == null) {
            throw new RuntimeException("日期格式有误!");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        try {
            return sdf.parse(source);
        } catch (ParseException e) {
            throw  new RuntimeException("字符串转日期异常!");
        }
    }
}
