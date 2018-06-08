package com.wolfman.installment.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * @ClassName AccountUtil
 * @Description TODU
 * @Author huhao
 * @Date Create in 2018/5/16 9:38
 * @Version 1.0
 */
public class AccountUtil {

    public static boolean isPhoneNumber(String account) {
        return NumberUtils.isDigits(account) && StringUtils.startsWith(account, "1") && account.length() == 11;
    }

    public static boolean isEmployeeId(String account) {
        return NumberUtils.isDigits(account) && account.length() == 6;
    }

    public static boolean isEmail(String account) {
        //TODO  fix it
        return StringUtils.indexOf(account, "@") > 1
                && StringUtils.lastIndexOf(account, ".") > (StringUtils.indexOf(account, "@") + 1);
    }


    /**
     * @Author huhao
     * @Description
     * @DATE 10:07 2018/5/16
     * @Param [input]
     * @return boolean
    **/
    public static boolean checkPasswordRule(String password) {
        //字符串中需要包含大写字幕，小写字母，以及数字，长度大于8位
        return password.matches("^.*[A-Z]+.*$") && password.matches("^.*[0-9]+.*$")&& password.matches("^.*[a-z]+.*$") && password.length()>8;
    }

    public static void main(String[] args){
        System.out.println("rexCheckPassword is： "+ checkPasswordRule("a123456A"));
    }




}
