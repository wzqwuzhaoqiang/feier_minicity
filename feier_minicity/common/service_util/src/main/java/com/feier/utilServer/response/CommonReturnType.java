package com.feier.utilServer.response;

import lombok.Data;

@Data
public class CommonReturnType {

    //表明请求的返回处理结果："success" 或者 "fail"
    private String status;

    //若status=success,则data内返回前端需要的json数据
    //若status=fail，则data内使用通用的错误码格式
    private Object data;

    //定义一个通用的创建方法
    public static CommonReturnType create(Object result){
        return CommonReturnType.create(result,"success");
    }

    private static CommonReturnType create(Object result,String success) {
        CommonReturnType type = new CommonReturnType();
        type.setStatus(success);
        type.setData(result);
        return type;
    }

}
