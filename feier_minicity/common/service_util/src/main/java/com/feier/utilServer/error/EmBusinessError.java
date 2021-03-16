package com.feier.utilServer.error;

public enum EmBusinessError implements CommonError {
    //10000开头为用户信息相关的错误定义
    USER_NOT_EXIST(10001,"用户不存在"),

    //定义通用类型错误20000开头
    PARAMETER_VALIDATION_ERROR(20001,"参数不合法"),
    UNKNOW_ERROR(20002,"未知错误"),

    EXPIER_OTP_CODE(20003,"验证码过期"),
    EXPIER_OTP_ERROR(20004,"验证码错误"),
    USER_LOGIN_FAIL(20005,"用户手机号或密码不正确，登入失败")


    ;

    private EmBusinessError(int errCode,String errMsg){
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    private int errCode;
    private String errMsg;

    @Override
    public int getErrCode() {
        return this.errCode;
    }

    @Override
    public String getErrMsg() {
        return this.errMsg;
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }
}
