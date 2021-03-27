package com.sectest.seckilldemo.service.impl;

import com.sectest.seckilldemo.common.RespEnum;
import com.sectest.seckilldemo.common.RespResult;
import com.sectest.seckilldemo.entity.User;
import com.sectest.seckilldemo.exception.GlobalException;
import com.sectest.seckilldemo.mapper.UserMapper;
import com.sectest.seckilldemo.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sectest.seckilldemo.utils.CookieUtil;
import com.sectest.seckilldemo.utils.MD5Util;
import com.sectest.seckilldemo.utils.UUIDUtil;
import com.sectest.seckilldemo.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Think
 * @since 2021-03-25
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public RespResult doLogin(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response) {
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();
       /* if(StringUtils.isEmpty(mobile)||StringUtils.isEmpty(password)){
            return RespResult.error(RespEnum.LOGIN_ERROR);
        }
        if(!ValidatorUtil.isMobile(mobile)){
            return RespResult.error(RespEnum.MOBILE_ERROR);
        }*/
        User user = userMapper.selectById(mobile);
        if(null==user){
           // return RespResult.error(RespEnum.LOGIN_ERROR);
            throw new GlobalException(RespEnum.LOGIN_ERROR);
        }
        if(!MD5Util.formPassToDBPass(password,user.getSlat()).equals(user.getPassword())){
            throw new GlobalException(RespEnum.LOGIN_ERROR);
        }

        String ticket = UUIDUtil.uuid();
        //request.getSession().setAttribute(ticket,user);

        //换成存入redis
        redisTemplate.opsForValue().set("user:"+ticket,user);
        CookieUtil.setCookie(request,response,"userTicket",ticket);
        return RespResult.success();
    }

    @Override
    public User getUserByCookie(String userTicket,HttpServletRequest request, HttpServletResponse response){
        if(!StringUtils.hasLength(userTicket)){
            return null;
        }
        User  user = (User) redisTemplate.opsForValue().get("user:"+userTicket);
        if(user != null){
            CookieUtil.setCookie(request,response,"userTicket",userTicket);
        }
        return user;
    }
}
