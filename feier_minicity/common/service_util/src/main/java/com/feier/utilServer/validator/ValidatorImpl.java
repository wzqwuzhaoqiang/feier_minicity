package com.feier.utilServer.validator;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

@Component
public class ValidatorImpl implements InitializingBean {


    private Validator validator;

    //实现校验方法并返回校验结果
    public ValidationResult validate(Object bean){
        ValidationResult result = new ValidationResult();
        Set<ConstraintViolation<Object>> validateSet = validator.validate(bean);
        if (validateSet.size()>0){
            result.setHasErrors(true);
            validateSet.forEach(val->{
                String message = val.getMessage();
                String propertyName = val.getPropertyPath().toString();
                result.getErrorMsgMap().put(propertyName, message);
            });

        }
        return result;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //将hibernate validator通过工厂方式实例化
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();

    }
}
