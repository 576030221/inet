package io.renren.common.utils;

import org.apache.commons.lang.StringUtils;

import java.util.regex.Pattern;

/**
 * 各种数据的校验工具
 *
 *      判断多个字符串参数是否存在空值            StringsIsBlank
 *      判断字符串是否满足正则表达式              StringPattern
 */
public class CheckUtil {

    /**
     * 判断多个字符串参数是否存在空值
     * @param strings
     * @return
     */
    public static boolean StringsIsBlank(String... strings){
        for (String s : strings) {
            if(StringUtils.isBlank(s)){
                return true;
            }
        }
        return false;
    }

    /**
     * 判断字符串是否满足正则表达式
     * @param pattern 正则表达式
     * @param string 字符串
     * @return
     */
    public static boolean StringPattern(String pattern,String string){
        return Pattern.compile(pattern).matcher(string).matches();
    }
}
