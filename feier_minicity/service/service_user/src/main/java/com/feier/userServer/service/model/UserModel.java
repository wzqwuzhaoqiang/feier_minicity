package com.feier.userServer.service.model;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UserModel {

    private Integer id;

    @NotBlank(message = "名字不能为空")
    private String name;
    @NotNull(message ="性别不能为空")
    private Integer gender;
    @NotNull(message ="年龄不能不填写")
    @Min(value = 0, message = "年龄必须大于0")
    @Max(value = 150, message = "年龄必须小于150")
    private Integer age;

    private String telphone;

    private String registerMode;

    private String thirdPartyId;

    private String encrptPassword;
}
