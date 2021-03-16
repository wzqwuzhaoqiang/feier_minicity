package com.feier.userServer.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
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
@ApiModel(value="UserPassword对象", description="")
public class UserPassword implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String encrptPassword;

    private Integer userId;


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
