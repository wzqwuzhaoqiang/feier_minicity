package com.feier.userServer.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author wzq
 * @since 2021-03-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="UserInfo对象", description="")
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    @ApiModelProperty(value = "1代表男性，2代表女性")
    private Integer gender;

    private Integer age;

    @ApiModelProperty(value = "手机号")
    private String telphone;

    @ApiModelProperty(value = "手机，微信，支付宝")
    private String registerMode;

    @ApiModelProperty(value = "第三方id")
    private String thirdPartyId;


    @TableField(fill = FieldFill.INSERT)
    private Date insertTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date modifiedTime;

    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer isdelete;

    @Version
    @TableField(fill = FieldFill.INSERT)
    private Long version;


}
