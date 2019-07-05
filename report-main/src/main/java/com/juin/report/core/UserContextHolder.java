package com.juin.report.core;

public class UserContextHolder {

    public static ThreadLocal<UserContext> userContextThreadLocal = new ThreadLocal<>();

}
