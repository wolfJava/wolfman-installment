package com.wolfman.installment.common.bean.annotation;

import java.lang.annotation.*;

/**
 * 响应代码注释信息
 * <p>
 * Created by qianz on 2017/5/23.
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ResponseMessage {
    /**
     * 注释信息
     *
     * @return
     */
    String value();

}
