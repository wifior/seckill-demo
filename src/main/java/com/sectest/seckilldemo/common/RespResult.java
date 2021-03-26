package com.sectest.seckilldemo.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespResult {

    private long code;
    private String message;

    private Object obj;

    public static RespResult success(){
        return new RespResult(RespEnum.SUCCESS.getCode(),RespEnum.SUCCESS.getMessage(),null);
    }

    public static RespResult success(Object obj){
        return new RespResult(RespEnum.SUCCESS.getCode(),RespResult.success().getMessage(),obj);
    }

    public static RespResult error(RespEnum respEnum){
        return new RespResult(respEnum.getCode(),respEnum.getMessage(),null);
    }


}
