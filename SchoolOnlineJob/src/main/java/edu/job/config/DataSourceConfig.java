package edu.job.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import edu.job.config.database.DynamicDataSource;
import edu.job.config.database.EnumDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {

    private static final Logger LOGGER= LoggerFactory.getLogger(DataSourceConfig.class);


    /**
     * 假设:读写数据源
     * @return
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource-write")
    public DataSource writeDataSource(){
        return DataSourceBuilder.create().type(DruidDataSource.class).build();
    }

    /**
     * 假设:只读数据源
     * @return
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource-read")
    public DataSource readDataSource(){
        return DataSourceBuilder.create().type(DruidDataSource.class).build();
    }

    /**
     * 注意:
     * 当存在多个数据源时,请添加到<pre>dataSourceMap</pre>;
     * 另外,枚举{@link EnumDataSource} 也需要添加相关枚举项.
     */
    @Bean
    @Primary
    public DynamicDataSource dynamicDataSource(){
        Map<Object,Object> dataSourceMap=new HashMap<>();

        dataSourceMap.put(EnumDataSource.writeDataSource,writeDataSource());
        dataSourceMap.put(EnumDataSource.readDataSource,readDataSource());

        DynamicDataSource dynamicDataSource=new DynamicDataSource();
        dynamicDataSource.setTargetDataSources(dataSourceMap);

        dynamicDataSource.setDefaultTargetDataSource(readDataSource());

        return dynamicDataSource;

    }


    //配置Druid的监控
    /**
     * 1、配置一个管理后台的Servlet
     * @return
     */
    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        Map<String,String> initParams = new HashMap<>();

        initParams.put("loginUsername","admin");
        initParams.put("loginPassword","123456");

        //默认允许所有访问
        initParams.put("allow","");

        bean.setInitParameters(initParams);
        return bean;
    }


    /**
     * 2、配置一个web监控的filter
     * @return
     */
    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());

        Map<String,String> initParams = new HashMap<>();
        initParams.put("exclusions","*.js,*.css,/druid/*");

        bean.setInitParameters(initParams);
        bean.setUrlPatterns(Arrays.asList("/*"));

        return  bean;
    }

}
