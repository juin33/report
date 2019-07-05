package com.juin.report.core;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author kejun
 * @date 2019/7/4 下午4:38
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({})
public @interface SqlSignature {
    String field();
    String tableName();

}
