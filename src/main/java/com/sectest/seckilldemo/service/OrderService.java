package com.sectest.seckilldemo.service;

import com.sectest.seckilldemo.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sectest.seckilldemo.entity.User;
import com.sectest.seckilldemo.vo.GoodsVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Think
 * @since 2021-03-27
 */
public interface OrderService extends IService<Order> {

    Order seckill(User user, GoodsVo goodsVo);
}
