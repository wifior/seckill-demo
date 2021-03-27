package com.sectest.seckilldemo.mapper;

import com.sectest.seckilldemo.entity.Goods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sectest.seckilldemo.vo.GoodsVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Think
 * @since 2021-03-27
 */
public interface GoodsMapper extends BaseMapper<Goods> {


    List<GoodsVo> findGoodsVo();

    GoodsVo findGoodsVoByGoodsId(Long goodsId);
}
