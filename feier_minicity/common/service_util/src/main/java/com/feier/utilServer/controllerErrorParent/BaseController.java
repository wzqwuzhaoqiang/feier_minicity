package com.feier.utilServer.controllerErrorParent;

import com.feier.utilServer.error.BusinessException;
import com.feier.utilServer.error.EmBusinessError;
import com.feier.utilServer.response.CommonReturnType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class BaseController {

    public static String CONTENT_TYPE_FORMED="application/x-www-form-urlencoded";

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public Object handlerException(HttpServletRequest request, Exception ex){
        Map<String,Object> map = new HashMap<>();
        if (ex instanceof BusinessException){
            BusinessException businessException = (BusinessException)ex;
            map.put("errCode", businessException.getErrCode());
            map.put("errMsg", businessException.getErrMsg());
        }else {
            map.put("errCode", EmBusinessError.UNKNOW_ERROR.getErrCode());
            map.put("errMsg",EmBusinessError.UNKNOW_ERROR.getErrMsg() );
            ex.printStackTrace();
        }
        return CommonReturnType.create(map);
    }
}
