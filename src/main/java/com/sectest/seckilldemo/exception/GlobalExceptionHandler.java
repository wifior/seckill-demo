package com.sectest.seckilldemo.exception;

import com.sectest.seckilldemo.common.RespEnum;
import com.sectest.seckilldemo.common.RespResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import org.springframework.validation.BindException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public RespResult ExceptionHandler(Exception e){
        if(e instanceof GlobalException){
            GlobalException ex = (GlobalException) e;
            return RespResult.error(ex.getRespEnum());
        }else if(e instanceof BindException){
            BindException ex = (BindException)e;
            RespResult respResult = RespResult.error(RespEnum.BIND_ERROR);
            respResult.setMessage("参数绑定异常："+ ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
            return respResult;
        }
        return RespResult.error(RespEnum.ERROR);
    }
}
