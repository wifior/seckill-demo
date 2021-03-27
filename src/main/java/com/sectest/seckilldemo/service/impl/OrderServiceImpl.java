package com.sectest.seckilldemo.service.impl;

import com.sectest.seckilldemo.entity.Order;
import com.sectest.seckilldemo.mapper.OrderMapper;
import com.sectest.seckilldemo.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Think
 * @since 2021-03-27
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

}
