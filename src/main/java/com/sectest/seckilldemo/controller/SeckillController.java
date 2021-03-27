package com.sectest.seckilldemo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sectest.seckilldemo.common.RespEnum;
import com.sectest.seckilldemo.entity.Order;
import com.sectest.seckilldemo.entity.SeckillOrder;
import com.sectest.seckilldemo.entity.User;
import com.sectest.seckilldemo.service.GoodsService;
import com.sectest.seckilldemo.service.OrderService;
import com.sectest.seckilldemo.service.SeckillOrderService;
import com.sectest.seckilldemo.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/seckill")
public class SeckillController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private SeckillOrderService seckillOrderService;
    /**
     * 秒杀
     * @param model
     * @param user
     * @param goodsId
     * @return
     */
    @RequestMapping("/doSeckill")
    public String doSeckill(Model model, User user,Long goodsId){
        if(user == null){
            return "login";
        }
        model.addAttribute("user",user);
        GoodsVo goodsVo = goodsService.findGoodsVoByGoodsId(goodsId);
        //判断库存
        if(goodsVo.getStockCount()<1){
            model.addAttribute("errmsg", RespEnum.EMPTY_STOCK.getMessage());
            return "secKillFail";
        }
        //判断是否重复抢购
        SeckillOrder seckillOrder = seckillOrderService.getOne(new QueryWrapper<SeckillOrder>().eq("user_id",user.getId()).eq("goods_id",goodsId));

        if(seckillOrder!=null){
            model.addAttribute("errmsg",RespEnum.REPEATE_ERROR.getMessage());
            return "secKillFail";
        }

        Order order = orderService.seckill(user,goodsVo);
        model.addAttribute("order",order);
        model.addAttribute("goods",goodsVo);
        return "orderDetail";
    }
}
