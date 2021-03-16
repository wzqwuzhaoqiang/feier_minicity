package com.feier.productServer.controller;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.feier.productServer.entity.ProductInfo;
import com.feier.productServer.service.ProductInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 商品信息表 前端控制器
 * </p>
 *
 * @author wzq
 * @since 2021-03-14
 */
@Api(description = "商品模块")
@CrossOrigin
@RestController
@RequestMapping("/productServer/product")
public class ProductInfoController {

    @Autowired
    private ProductInfoService productServer;
    @Autowired
    BaseMapper<ProductInfo> baseMapper;


    @ApiOperation(value = "添加商品")
    @PostMapping("addProduct")
    public Map<String,String> addProduct(@RequestBody(required = false) ProductInfo productInfo){
        System.out.println("进入添加操作");
        Map<String,String> map = new HashMap<>();
        boolean res = productServer.save(productInfo);
        if (res){
            map.put("res", "success");
        }else {
            map.put("res", "error");
        }

        return map;
    }

    @ApiOperation(value = "删除商品")
    @DeleteMapping("deleteProduct/{id}")
    public Map<String,String> deleteProduct(@PathVariable("id") int id){
        System.out.println("进入删除操作");
        Map<String,String> map = new HashMap<>();
        boolean res = productServer.removeById(id);
        if (res){
            map.put("res", "success");
        }else {
            map.put("res", "error");
        }

        return map;
    }

    @ApiOperation(value = "更新商品")
    @PutMapping("updateProduct")
    public Map<String,String> updateProduct(@RequestBody(required = false) ProductInfo productInfo){
        System.out.println("进入更新操作");

        ProductInfo productInfo1 = baseMapper.selectById(productInfo.getProductId());
        Map<String,String> map = new HashMap<>();
        productInfo1.setProductName(productInfo.getProductName());
        productInfo1.setProductCore(productInfo.getProductCore());
        //productInfo = productServer.getById(productInfo);
        int res = baseMapper.updateById(productInfo1);
        if (res>0){
            map.put("res", "success");
        }else {
            map.put("res", "error");
        }

        return map;
    }



}

