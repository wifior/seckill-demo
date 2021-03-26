package com.sectest.seckilldemo.vo;

import com.sectest.seckilldemo.validator.IsMobile;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
public class LoginVo {

    @NotNull
    @IsMobile
    public String mobile;

    @NotNull
    @Length(min = 32)
    public String password;
}
