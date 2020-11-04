package ${basePackage}.controller;

import ${basePackage}.base.BaseController;
import ${basePackage}.common.viewobject.UniversalResult;
import ${basePackage}.service.${modelNameUpperCamel}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


/**
 * ${remark}的Controller
 * @author caoxingxing
 * @date ${date}
 */
@RestController
@RequestMapping("/${modelNameLowerCamel}")
public class ${modelNameUpperCamel}Controller extends BaseController {

    @Autowired
    private ${modelNameUpperCamel}Service ${modelNameLowerCamel}Service;


    @PostMapping("/doquery")
    public ResponseEntity<Object> doquery(@RequestBody String json, HttpServletRequest request) {


    }

    @GetMapping("")
    public ResponseEntity<Object> get${modelNameUpperCamel}List() {

    }

    @PutMapping()
    public ResponseEntity<Object> put${modelNameUpperCamel}() {


    }

    @DeleteMapping()
    public ResponseEntity<Object> delete${modelNameUpperCamel}(@RequestBody @Valid Object id) {


    }
}
