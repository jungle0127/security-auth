package com.ps.security.auth.core.validate.impl;

import com.ps.security.auth.core.exception.ValidateCodeException;
import com.ps.security.auth.core.validate.IValidateCodeGenerator;
import com.ps.security.auth.core.validate.IValidateCodeProcessor;
import com.ps.security.auth.core.validate.code.ValidateCode;
import com.ps.security.auth.core.validate.code.ValidateCodeType;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Map;

/**
 * 抽象图像验证码处理器
 *
 * @param <C>
 */
public abstract class AbstractValidateCodeProcessor<C extends ValidateCode> implements IValidateCodeProcessor {
    /**
     * Collection for all the IValidateCodeGenerator
     *
     * bean name as the key, instance as the value
     * Spring的依赖搜索技巧：根据请求URL的不同搜索接口的不同实现
     */
    @Autowired
    private Map<String,IValidateCodeGenerator> validateCodeGeneratorMap;

    @Override
    public void create(ServletWebRequest request) {
        C validateCode = generate(request);
        save(request,validateCode);
        send(request,validateCode);

    }
    /**
     * 生成校验码
     *
     * @param request
     * @return
     */
    private C generate(ServletWebRequest request) {
        String type = getValidateCodeType(request).toString().toLowerCase();
        String generatorName = type + IValidateCodeGenerator.class.getSimpleName();
        IValidateCodeGenerator validateCodeGenerator = validateCodeGeneratorMap.get(generatorName);
        if(validateCodeGenerator == null){
            throw new ValidateCodeException(String.format("Validate code generator:%s does not exist."));
        }
        return (C) validateCodeGenerator.generate(request);
    }
    /**
     * 根据请求的url获取校验码的类型
     *
     * @param request
     * @return
     */
    private ValidateCodeType getValidateCodeType(ServletWebRequest request) {
        String type = StringUtils.substringBefore(getClass().getSimpleName(),"CodeProcessor");
        return ValidateCodeType.valueOf(type.toUpperCase());
    }


}
