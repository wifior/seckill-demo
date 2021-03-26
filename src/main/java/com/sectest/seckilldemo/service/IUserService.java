package com.sectest.seckilldemo.service;

import com.sectest.seckilldemo.common.RespResult;
import com.sectest.seckilldemo.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sectest.seckilldemo.vo.LoginVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Think
 * @since 2021-03-25
 */
public interface IUserService extends IService<User> {

    RespResult doLogin(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response);
}
