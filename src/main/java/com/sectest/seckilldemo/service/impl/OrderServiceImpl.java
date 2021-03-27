package com.sectest.seckilldemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sectest.seckilldemo.entity.Order;
import com.sectest.seckilldemo.entity.SeckillGoods;
import com.sectest.seckilldemo.entity.SeckillOrder;
import com.sectest.seckilldemo.entity.User;
import com.sectest.seckilldemo.mapper.OrderMapper;
import com.sectest.seckilldemo.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sectest.seckilldemo.service.SeckillGoodsService;
import com.sectest.seckilldemo.service.SeckillOrderService;
import com.sectest.seckilldemo.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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

    @Autowired
    private SeckillGoodsService seckillGoodsService;

    @Autowired
    private SeckillOrderService seckillOrderService;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Order seckill(User user, GoodsVo goodsVo) {
        SeckillGoods seckillGoods = seckillGoodsService.getOne(new QueryWrapper<SeckillGoods>().eq("goods_id",goodsVo.getId()));
        seckillGoods.setStockCount(seckillGoods.getStockCount()-1);
        seckillGoodsService.updateById(seckillGoods);
        Order order = new Order();
        order.setUserId(user.getId());
        order.setGoodsId(goodsVo.getId());
        order.setDeliveryAddrId(0L);
        order.setGoodsName(goodsVo.getGoodsName());
        order.setGoodsCount(1);
        order.setGoodsPrice(seckillGoods.getSeckillPrice());
        order.setOrderChannel(1);
        order.setStatus(0);
        order.setCreateDate(new Date());
        orderMapper.insert(order);

        //生成秒杀订单
        SeckillOrder seckillOrder = new SeckillOrder();
        seckillOrder.setUserId(user.getId());
        seckillOrder.setOrderId(order.getId());
        seckillOrder.setGoodsId(goodsVo.getId());
        seckillOrderService.save(seckillOrder);
        return null;
    }
}
