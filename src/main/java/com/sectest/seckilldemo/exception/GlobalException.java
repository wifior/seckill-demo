package com.sectest.seckilldemo.exception;

import com.sectest.seckilldemo.common.RespEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GlobalException extends RuntimeException{

    private RespEnum respEnum;
}
