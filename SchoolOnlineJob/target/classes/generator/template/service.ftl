package ${basePackage}.service;

import ${basePackage}.entity.${modelNameUpperCamel};
import BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import ${basePackage}.mappers.${modelNameUpperCamel}Mapper;
import org.springframework.stereotype.Service;


/**
 * ${remark}的Service
 * @author caoxingxing
 * @date ${date}
 */
@Service
public class ${modelNameUpperCamel}Service extends BaseService<${modelNameUpperCamel}> {

    @Autowired
    private ${modelNameUpperCamel}Mapper ${modelNameLowerCamel}Mapper;


}
