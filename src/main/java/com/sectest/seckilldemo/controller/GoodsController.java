package com.sectest.seckilldemo.controller;

import com.sectest.seckilldemo.entity.User;
import com.sectest.seckilldemo.service.GoodsService;
import com.sectest.seckilldemo.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;


@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/toList")
    public String toList(Model model, User user){
        model.addAttribute("user",user);
        model.addAttribute("goodsList",goodsService.findGoodsVo());
        return "goodsList";
    }

    @RequestMapping("/toDetail/{goodsId}")
    public String toDetail(Model model, User user, @PathVariable Long goodsId){
        model.addAttribute("user",user);
        GoodsVo goodsVo = goodsService.findGoodsVoByGoodsId(goodsId);
        Date startDate = goodsVo.getStartDate();
        Date endDate = goodsVo.getEndDate();
        Date nowDate = new Date();
        int seckillStatus = 0;
        int remainSeconds = 0;
        if(nowDate.before(startDate)){
            remainSeconds = ((int)((startDate.getTime()-nowDate.getTime())/1000));
        }else if(nowDate.after(endDate)){
            seckillStatus = 2;
            remainSeconds = -1;
        }else {
            seckillStatus = 1;
            remainSeconds= 0;
        }
        model.addAttribute("remainSeconds", remainSeconds);
        model.addAttribute("seckillStatus", seckillStatus);
        model.addAttribute("goods", goodsVo);
        return "goodsDetail";
    }


}
