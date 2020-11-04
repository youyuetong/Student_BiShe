package com.goldmantis.mybatisgenerator;

import com.google.common.base.CaseFormat;
import freemarker.template.TemplateExceptionHandler;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.config.JDBCConnectionConfiguration;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.generator.MapperPlugin;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 基于mybatis generator的扩展插件,去掉官方复杂容易的CRUD语句,替换成简化版本的xml文档.
 * 其中新增,编辑,删除,简单查询由通用mapper提供.
 * <strong>扩展插件提供了生成service层,controller层,model层及简单页面.</strong>
 *
 * @author chenjindong
 * @date 2019/1/26 11:07
 */
public class ExtendPlugin extends MapperPlugin {


    /**
     * 是否生成Swagger2的相关注解[需引入spring fox的坐标]
     */
    private static final boolean IS_GENERATE_SWAGGER2_ANNOTATION = false;

    /**
     * 是否生成JSR303验证的相关注解[项目建议引入hibernate-validator的坐标]
     */
    private static final boolean IS_GENERATE_JSR303_ANNOTATION = true;

    /**
     * 是否生成通用service的代码,具体代码自行配置generator/template/service.ftl代码
     */
    private static final boolean IS_GENERATE_SERVICE_LAYER = true;

    /**
     * 是否生成通用controller的代码,具体代码自行配置generator/template/controller.ftl代码
     */
    private static final boolean IS_GENERATE_CONTROLLER_LAYER = true;

    private SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    /**
     * 数据库实体类型生成器
     *
     * @param topLevelClass
     * @param introspectedTable
     * @return
     */
    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {

        if (true == IS_GENERATE_SWAGGER2_ANNOTATION) {
            topLevelClass.addImportedType("io.swagger.annotations.*");
            topLevelClass.addAnnotation("@ApiModel(\"" + introspectedTable.getFullyQualifiedTableNameAtRuntime() + "\")");
        }

        // 获取表注释
        String tableComment = getTableComment(introspectedTable);


        topLevelClass.addJavaDocLine("/**");
        topLevelClass.addJavaDocLine(" * " + tableComment);
        topLevelClass.addJavaDocLine(" * @author " + System.getenv().get("USER"));
        topLevelClass.addJavaDocLine(" * @date " + dateFormatter.format(new Date()));
        topLevelClass.addJavaDocLine(" */");

        // 生成其他代码框架
        generateOtherCode(introspectedTable.getFullyQualifiedTable().getIntrospectedTableName(), introspectedTable.getFullyQualifiedTable().getIntrospectedTableName(), tableComment);

        return super.modelBaseRecordClassGenerated(topLevelClass, introspectedTable);

    }

    /**
     * 当前表注释
     *
     * @return
     */
    private String getTableComment(IntrospectedTable introspectedTable) {

        JDBCConnectionConfiguration jdbcConnectionConfiguration =
                getContext().getJdbcConnectionConfiguration();

        Connection connection = getJDBCConnection(jdbcConnectionConfiguration.getDriverClass(), jdbcConnectionConfiguration.getConnectionURL(), jdbcConnectionConfiguration.getUserId(), jdbcConnectionConfiguration.getPassword());
        String remark = introspectedTable.getRemarks();
        Statement stmt = null;

        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(new StringBuilder().append("SHOW TABLE STATUS LIKE '").append(introspectedTable.getFullyQualifiedTable().getIntrospectedTableName()).append("'").toString());
            while (rs.next()) {
                remark = rs.getString("COMMENT");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return remark;
    }

    @Override
    public boolean modelFieldGenerated(Field field, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {

        if (true == IS_GENERATE_SWAGGER2_ANNOTATION) {
            if (introspectedColumn.getRemarks() != null && (!"".equals(introspectedColumn.getRemarks().trim()))) {
                field.addAnnotation("@ApiModelProperty(\"" + introspectedColumn.getRemarks().replace("\r\n", "") + "\")");
            }
        }

        if (false == introspectedColumn.isNullable()) {
            if (false == introspectedColumn.isAutoIncrement()) {
                topLevelClass.addImportedType("javax.validation.constraints.NotNull");
                field.addAnnotation("@NotNull");
            } else {
                //配置主键自增策略
                field.addAnnotation("@GeneratedValue(strategy = GenerationType.IDENTITY)");
            }
        }

        if (true == IS_GENERATE_JSR303_ANNOTATION) {
            //VARCHAR列,加入字符串长度限制注解
            if (true == introspectedColumn.isStringColumn()) {
                topLevelClass.addImportedType("javax.validation.constraints.Size");
                field.addAnnotation("@Size(min = 0, max = " + introspectedColumn.getLength() + " , message = \"长度必须在{min}和{max}之间\")");
            }

            //INT列,加入最大值,最小值注解.
            if (introspectedColumn.getJdbcType() == Types.INTEGER) {
                topLevelClass.addImportedType("javax.validation.constraints.Max");
                field.addAnnotation("@Max(value=2147483647,message=\"最大值不能高于{value}\")");
                topLevelClass.addImportedType("javax.validation.constraints.Min");
                field.addAnnotation("@Min(value=-2147483648,message=\"最小值不能低于{value}\")");
            }
        }

        return super.modelFieldGenerated(field, topLevelClass, introspectedColumn,
                introspectedTable, modelClassType);
    }


    /**
     * 获取数据库连接
     *
     * @param driver
     * @param url
     * @param userName
     * @param password
     * @return
     */
    private static Connection getJDBCConnection(String driver, String url, String userName, String password) {

        Connection conn = null;
        try {

            Class.forName(driver);
            conn = DriverManager.getConnection(url, userName, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public void generateOtherCode(String tableName, String modelName, String remark) {
        try {

            freemarker.template.Configuration cfg = getConfiguration();

            Map<String, Object> data = new HashMap<>();
            data.put("date", dateFormatter.format(new Date()));
            data.put("remark", remark);
            data.put("author", System.getenv().get("USER"));
            String modelNameUpperCamel = !StringUtils.isEmpty(modelName) ? tableNameConvertUpperCamel(tableName) : modelName;
            data.put("modelNameUpperCamel", modelNameUpperCamel);
            data.put("modelNameLowerCamel", tableNameConvertLowerCamel(tableName));
            data.put("basePackage", getProperties().getProperty("basePackage"));

            //是否生成service层代码
            if(true==IS_GENERATE_SERVICE_LAYER) {
                File file = new File("src/test/gen/java/com/goldmantis/service/" + modelNameUpperCamel + "Service.java");
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                cfg.getTemplate("service.ftl").process(data,
                        new FileWriter(file));
                System.out.println(modelNameUpperCamel + "Service.java 生成成功");
            }

            //是否生成controller层代码
            if(true==IS_GENERATE_CONTROLLER_LAYER) {
                File fileController = new File("src/test/gen/java/com/goldmantis/controller/" + modelNameUpperCamel + "Controller.java");
                if (!fileController.getParentFile().exists()) {
                    fileController.getParentFile().mkdirs();
                }
                cfg.getTemplate("controller.ftl").process(data,
                        new FileWriter(fileController));
                System.out.println(modelNameUpperCamel + "Controller.java 生成成功");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private freemarker.template.Configuration getConfiguration() throws IOException {
        freemarker.template.Configuration cfg = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_28);
        cfg.setDirectoryForTemplateLoading(new File(getProperties().getProperty("freeMarkerTemplatePath")));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
        return cfg;
    }

    private static String tableNameConvertLowerCamel(String tableName) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, tableName.toLowerCase());
    }

    private static String tableNameConvertUpperCamel(String tableName) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tableName.toLowerCase());

    }
}
