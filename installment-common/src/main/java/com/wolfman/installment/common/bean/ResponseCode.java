package com.wolfman.installment.common.bean;

import com.wolfman.installment.common.bean.annotation.ResponseMessage;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.Hashtable;
import java.util.Map;

/**
 * @ClassName ResponseCode
 * @Description TODU
 * @Author huhao
 * @Date Create in 2018/5/15 17:04
 * @Version 1.0
 */
public class ResponseCode {

    static Map<String, Object> messages;

    static {
        Field fields[] = ResponseCode.class.getFields();
        messages = new Hashtable<String, Object>();
        for (Field field : fields) {
            ResponseMessage responseMessage = field.getAnnotation(ResponseMessage.class);
            if (responseMessage != null && StringUtils.isNotBlank(responseMessage.value())) {
                try {
                    messages.put((String) field.get(field), responseMessage.value());
                } catch (IllegalAccessException e) {
                }
            }
        }
    }

    private static final String DEFAULT_MESSAGE = "Unknown error";

    /**
     * @Author huhao
     * @Description
     * @DATE 17:08 2018/5/15
     * @Param [code]
     * @return java.lang.String
    **/
    public static String getResponseMessage(String code) {
        String message = (String) messages.get(code);
        if (StringUtils.isBlank(message))
            message = DEFAULT_MESSAGE;
        return message;
    }

    /**
     * @Author huhao
     * @Description 成功方法
     * @DATE 17:09 2018/5/15
     * @Param [resultMap]
     * @return void
    **/
    public static void putSuccess(Map<String, Object> resultMap) {
        put(resultMap, SUCCESS);
    }

    /**
     * 填充相应代码信息
     *
     * @param resultMap
     * @param code
     */
    public static void put(Map<String, Object> resultMap, String code) {
        put(resultMap, code, getResponseMessage(code));
    }

    /**
     * 填充响应代码信息（使用指定的描述信息）
     *
     * @param resultMap
     * @param code
     * @param message
     */
    public static void put(Map<String, Object> resultMap, String code, String message) {
        resultMap.put(ParameterName.CODE, code);
        resultMap.put(ParameterName.MESSAGE, message);
    }

    /**
     * 判断返回代码是否为success
     *
     * @param code
     * @return
     */
    public static boolean isSuccess(String code) {
        return SUCCESS.equals(code);
    }

    /**
     * 判断返回代码是否为success
     *
     * @param result
     * @return
     */
    public static boolean isSuccess(Map<String, Object> result) {
        return result != null && isSuccess((String) result.get(ParameterName.CODE));
    }



    /**
     * 请在下方定义各 code 值 响应代码对应的 message 使用ResponseMessage注解标识
     */
    //0000 成功
    @ResponseMessage("请求成功！")
    public static final String SUCCESS = "0000";

    //0000 成功
    @ResponseMessage("请求失败，请稍后再试！")
    public static final String FAIL = "9998";

    //9999 未知错误
    @ResponseMessage("系统异常，请稍后再试！")
    public static final String UNKNOWN = "9999";

    //5555 未找到数据
    @ResponseMessage("未找到数据！")
    public static final String NO_DATA_FOUND = "5555";


    //0001-0099 参数相关

    @ResponseMessage("参数不能为空")
    public static final String PARAM_EMPTY = "0001";

    @ResponseMessage("无效的参数")
    public static final String PARAM_INVALID = "0002";

    @ResponseMessage("无效的手机号！")
    public static final String MOBILE_INVALID = "0003";


    //0500-0599 数据相关相关
    @ResponseMessage("手机号已存在！")
    public static final String MOBILE_EXIST = "0501";

    @ResponseMessage("密码不符规则！")
    public static final String PASSWORD_NOT_MATCH = "0502";

    @ResponseMessage("手机号与密码不匹配")
    public static final String PHONENUMBER_PASSWORD_NOT_MATCH = "0503";


}
