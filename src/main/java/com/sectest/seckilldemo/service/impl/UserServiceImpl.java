package com.sectest.seckilldemo.service.impl;

import com.sectest.seckilldemo.entity.User;
import com.sectest.seckilldemo.mapper.UserMapper;
import com.sectest.seckilldemo.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
