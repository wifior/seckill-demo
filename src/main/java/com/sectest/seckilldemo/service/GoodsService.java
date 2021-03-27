package com.sectest.seckilldemo.service;

import com.sectest.seckilldemo.entity.Goods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sectest.seckilldemo.vo.GoodsVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Think
 * @since 2021-03-27
 */
public interface GoodsService extends IService<Goods> {

    List<GoodsVo> findGoodsVo();

    GoodsVo findGoodsVoByGoodsId(Long goodsId);
}
