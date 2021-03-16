package com.feier.productServer.service.impl;

import com.feier.productServer.entity.ProductInfo;
import com.feier.productServer.mapper.ProductInfoMapper;
import com.feier.productServer.service.ProductInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品信息表 服务实现类
 * </p>
 *
 * @author wzq
 * @since 2021-03-14
 */
@Service
public class ProductInfoServiceImpl extends ServiceImpl<ProductInfoMapper, ProductInfo> implements ProductInfoService {

}
