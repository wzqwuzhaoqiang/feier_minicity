package com.feier.productServer.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 商品信息表
 * </p>
 *
 * @author wzq
 * @since 2021-03-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ProductInfo对象", description="商品信息表")
public class ProductInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品ID")
    @TableId(value = "product_id", type = IdType.AUTO)
    private Integer productId;

    @ApiModelProperty(value = "商品编码")
    private String productCore;

    @ApiModelProperty(value = "商品名称")
    private String productName;

    @ApiModelProperty(value = "一级分类ID")
    private Integer oneCategoryId;

    @ApiModelProperty(value = "二级分类ID")
    private Integer twoCategoryId;

    @ApiModelProperty(value = "三级分类ID")
    private Integer threeCategoryId;

    @ApiModelProperty(value = "商品销售价格")
    private BigDecimal price;

    @ApiModelProperty(value = "上下架状态：0下架1上架")
    private Integer publishStatus;

    @ApiModelProperty(value = "审核状态：0未审核，1已审核")
    private Integer auditStatus;

    @TableLogic()
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "删除状态：0未删除，1已删除")
    private Integer isdelete;

    @Version
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "乐观锁")
    private Long version;

    @ApiModelProperty(value = "商品重量")
    private Float weight;

    @ApiModelProperty(value = "商品长度")
    private Float length;

    @ApiModelProperty(value = "商品高度")
    private Float height;

    @ApiModelProperty(value = "商品宽度")
    private Float width;

    @ApiModelProperty(value = "生产日期")
    private Date productionDate;

    @ApiModelProperty(value = "商品有效期")
    private Integer shelfLife;

    @ApiModelProperty(value = "商品描述")
    private String descript;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private Date insertTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "最后修改时间")
    private Date modifiedTime;


}
