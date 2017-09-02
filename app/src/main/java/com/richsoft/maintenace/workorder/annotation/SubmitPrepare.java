package com.richsoft.maintenace.workorder.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**验收需要提交的字段
 * @author zhoul
 * @version 创建时间:2016年3月3日 上午10:52:53
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SubmitPrepare {
}
